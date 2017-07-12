package de.tu_berlin.open_data.uv;

import de.tu_berlin.open_data.uv.model.UVSensor;
import de.tu_berlin.open_data.uv.model.UVSensorJsonSerializer;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.ArrayList;
import java.util.concurrent.Future;


/**
 * Created by Oliver Bruski on 03.07.2017.
 * Main Class to start importing date from the BFS UV-Index website.
 */
public class BfsImporter {

    private static final String KAFKA_SERVER_ENV_VAR_KEY = "KAFKA_HOST";
    private static final String KAFKA_TOPIC_ENV_VAR_KEY = "KAFKA_TOPIC";

    public static void main(String[] args) throws Exception {



        final String kafkaServer = System.getenv(KAFKA_SERVER_ENV_VAR_KEY);

        if (kafkaServer == null || kafkaServer.isEmpty()) {
            throw new Exception(String.format("Env var %s was not present", KAFKA_SERVER_ENV_VAR_KEY));
        }

        final String kafkaTopic = System.getenv(KAFKA_TOPIC_ENV_VAR_KEY);

        if(kafkaTopic == null || kafkaTopic.isEmpty()) {
            throw new Exception(String.format("Env var %s was not present", KAFKA_SERVER_ENV_VAR_KEY));
        }


        System.out.println("Sending json to Kafka queue on " + kafkaServer);
        KafkaQueue kafkaQueue = new KafkaQueue(kafkaTopic, kafkaServer);

        ArrayList<UVSensor> sensors = BfsUvWrapper.getSensorListFromUrl();
        for (UVSensor sensor : sensors) {
            Future<RecordMetadata> future = kafkaQueue.publish(UVSensorJsonSerializer.create(sensor));
            RecordMetadata rm = future.get();
            System.out.println(String.format("Topic: %s. Offset: %s. Partition: %s", rm.topic(), rm.offset(), rm.partition()));
        }

        System.out.println("Done importing! Tschüss!");
    }

}
