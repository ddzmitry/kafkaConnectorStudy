package com.learnkafka.consumer.partitioner.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Properties;

public class consumerClassManualOffset {

    public static void main(String[] args) {

        Properties properties=new Properties();
        properties.put("bootstrap.servers", "178.128.153.12:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("enable.auto.commit", false);
//        Promising to Kaffka that it will take offset management
//        So it will read only  new messages
        properties.put("group.id","test1");

        KafkaConsumer< String, String> consumer=new KafkaConsumer<String, String>(properties);


        ArrayList<String> topics=new ArrayList<String>();
        topics.add("test");

        consumer.subscribe(topics); // You can subscribe to any number of topics.

        try {

            while(true){

                ConsumerRecords<String, String> records = consumer.poll(1000);

                for(ConsumerRecord<String, String> record : records){

                    System.out.println("Record read in KafkaConsumerApp : " +  record.toString());
//                    this like allows you to do it
//                    if  this line is missing it will re-read messages again
                    consumer.commitSync();
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Inside exception loop : ");
            e.printStackTrace();
        }finally{
            consumer.close();
        }
    }
}
