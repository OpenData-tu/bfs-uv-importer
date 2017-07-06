package de.tu_berlin.open_data.uv;

import de.tu_berlin.open_data.uv.http.BsfUvWrapper;
import de.tu_berlin.open_data.uv.model.UVSensor;
import de.tu_berlin.open_data.uv.service.UVSensorJsonSchemaCreator;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.ArrayList;
import java.util.concurrent.Future;


/**
 * Created by Oliver Bruski on 03.07.2017.
 * Main Class to start importing date from the BSF UV-Index website.
 */
public class BsfImporter {

    private static final String IMPORTER_ID = "BSF_UV";
    private static final String KAFKA_SERVER_ENV_VAR_KEY = "KAFKA_HOST";

    public static void main(String[] args) throws Exception {



        final String kafkaServer = System.getenv(KAFKA_SERVER_ENV_VAR_KEY);

        if (kafkaServer == null || kafkaServer.isEmpty()) {
            throw new Exception(String.format("Env var %s was not present", KAFKA_SERVER_ENV_VAR_KEY));
        }

        ArrayList<UVSensor> sensors = BsfUvWrapper.getSensorListFromUrl();
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (UVSensor sensor : sensors) {
            builder.append(UVSensorJsonSchemaCreator.create(sensor) + ",");
        }
        builder.deleteCharAt(builder.length());
        builder.append("]");

        System.out.println("Sending json to Kafka queue on " + kafkaServer);
        KafkaQueue kafkaQueue = new KafkaQueue(IMPORTER_ID, kafkaServer);
        Future<RecordMetadata> future = kafkaQueue.publish(builder.toString());
        System.out.println("Done!");

        System.out.println("Waiting for Kafka to acknowledge...");
        RecordMetadata rm = future.get();
        System.out.println(String.format("Topic: %s. Offset: %s. Partition: %s", rm.topic(), rm.offset(), rm.partition()));

        System.out.println("Done importing! Tschüss!");

    }

}
