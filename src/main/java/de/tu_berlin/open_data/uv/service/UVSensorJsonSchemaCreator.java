package de.tu_berlin.open_data.uv.service;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.tu_berlin.open_data.uv.model.UVSensor;

import java.time.format.DateTimeFormatter;

/**
 * Created by Oliver Bruski on 04.07.2017.
 * Class to create the JSON schema from a UVSensor Object.
 */
public class UVSensorJsonSchemaCreator{

    /**
     * Creates the JSON schema for the given UVSensor.
     * @param sensor The Sensor Object that should be parsed to JSON.
     * @return A JSON String with the sensor data.
     */
    public static String create(UVSensor sensor) {

        JsonNodeFactory nodeFactory = JsonNodeFactory.instance;

        ObjectNode mainObject = nodeFactory.objectNode();

        mainObject.put("source_id", "bsf_uvindex");
        mainObject.put("device", sensor.getSensorId());
        mainObject.put("timestamp", sensor.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z");


        ObjectNode firstLevelChild = nodeFactory.objectNode();

        firstLevelChild.put("lat", sensor.getCoordinates().latitude);
        firstLevelChild.put("lon", sensor.getCoordinates().longitude);

        mainObject.set("location", firstLevelChild);

        mainObject.put("license", "no information");

        firstLevelChild = nodeFactory.objectNode();

        ObjectNode secondLevelChild = nodeFactory.objectNode();
        secondLevelChild.put("sensor", sensor.getSensorType());
        secondLevelChild.put("observation_value", Double.isNaN(Double.parseDouble(sensor.getMeasurement())) ? 0.0 : Double.parseDouble(sensor.getMeasurement()));
        secondLevelChild.put("unit", sensor.getUnit());
        firstLevelChild.set("uv-radiation", secondLevelChild);


        mainObject.set("sensors", firstLevelChild);
        firstLevelChild = nodeFactory.objectNode();

        firstLevelChild.put("location", sensor.getLocation());
        mainObject.set("extra", firstLevelChild);

        return mainObject.toString();
    }

}
