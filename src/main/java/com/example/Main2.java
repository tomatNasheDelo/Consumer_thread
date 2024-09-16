package com.example;




import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Main2 {
    


 public static void main(String[] args){

     Properties props = new Properties();

        props.setProperty("bootstrap.servers", "localhost:9092");
         props.setProperty("group.id", "my-group-id");
        props.setProperty("key.deserializer", 
        "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", 
        "org.apache.kafka.common.serialization.StringDeserializer");

        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("fetch.min.bytes", "150");
        props.setProperty("fetch.max.wait.ms", "2500");

        try(KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)){

            consumer.subscribe(Arrays.asList("kafka_thread"));

            while(true){
                  ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

                  for(ConsumerRecord<String, String> record : records){

                    System.out.printf("received messge : %\n", record.value());
                  }
            }
            
        }

 }


}
