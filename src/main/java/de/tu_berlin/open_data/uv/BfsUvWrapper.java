package de.tu_berlin.open_data.uv;

import de.tu_berlin.open_data.uv.model.Coordinates;
import de.tu_berlin.open_data.uv.model.UVSensor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Oliver Bruski on 04.07.2017.
 * Downloads the HTML file from BSF and parses it to get the measurement data for UV radiation.
 */
public class BfsUvWrapper {

    private static HashMap<String, Coordinates> stations = new HashMap<String, Coordinates>();


    /**
     * Instantiates a HashMap with the location names (key) and corresponding coordinates (value) of the BSF sensors.
     */
    static {
        stations.put("München", new Coordinates(48.220000,11.590000));
        stations.put("Zingst", new Coordinates(54.440000,12.720000));
        stations.put("Langen", new Coordinates(50.000000,8.650000));
        stations.put("Schauinsland", new Coordinates(47.910000,7.910000));
        stations.put("Lindenberg", new Coordinates(52.210000,14.120000));
        stations.put("Westerland/Sylt", new Coordinates(54.920000,8.310000));
        stations.put("Dortmund", new Coordinates(51.500000,7.420000));
        stations.put("Kulmbach", new Coordinates(50.110000,11.450000));
        // Rinteln not included on the Website
        stations.put("Rinteln", new Coordinates(52.170000,9.060000));
        stations.put("Norderney", new Coordinates(53.710000,7.210000));
        stations.put("Lüneburg", new Coordinates(53.230000, 10.400000));
    }

    /**
     * Gets an Array of UVSensor Object from the BFS URL and assigns the Object's fields (date, location, coordinates, measurement)
     */
    public static ArrayList<UVSensor> getSensorListFromUrl() {
        ArrayList<UVSensor> resultList = new ArrayList<UVSensor>();
        try {
            // getDate of Measurement
            Document doc = Jsoup.connect("http://www.bfs.de/DE/themen/opt/uv/uv-index/aktuell/aktuell_node.html").get();
            Elements dates = doc.select("div.uvindex-newsletter > h4");
            String date = dates.first().text();

            //get actual measurements
            Elements tables = doc.select("div.uvindex-newsletter > table");
            for (Element table : tables) {
                Elements tabRows = table.getElementsByTag("tr");
                for (Element row : tabRows) {
                    UVSensor sensor = new UVSensor();
                    Elements cells = row.getElementsByTag("td");
                    if(cells.size() != 0) {
                        sensor.setDate(parseDateToDateObject(date));
                        sensor.setLocation(cells.eq(0).text());
                        sensor.setMeasurement(cells.eq(1).text());
                        sensor.setUnit("UV-Index");
                        sensor.setCoordinates(stations.get(cells.eq(0).text()));
                        resultList.add(sensor);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * Gets a date String and converts it to a LocalDateTime compliant with the ISO-8601 Standard.
     * @param dateInput Date String of the Format "Day of Week(text), DD.MM.YYYY" to be converted.
     * @return The Date converted into a DateTime compliant with the ISO-8601 format.
     */
    private static LocalDate parseDateToDateObject(String dateInput){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("eeee, dd.MM.yyyy", Locale.GERMANY);
        LocalDate date = LocalDate.from(dtf.parse(dateInput));
//        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.MIDNIGHT);
        return date;
    }

}
