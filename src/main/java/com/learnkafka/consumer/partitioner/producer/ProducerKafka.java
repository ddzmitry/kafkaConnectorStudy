package com.learnkafka.consumer.partitioner.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

    public class ProducerKafka {

        public static void main(String[] args) {
            Properties properties=new Properties();
            properties.put("bootstrap.servers", "178.128.153.12:9092");
            properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        Put customer parition class for the kaffka topic
//        Have to configure it
//        properties.put("partitioner.class", "com.learnkafka.partitioner.ProducerKafkaPartitioner");


            KafkaProducer<String,String> myProducer= new KafkaProducer<String,String>(properties);

            try {
//                ways to pass hash key
//                Round-Robin
//            Custom Partiton implementation
                for(int i=101;i<150;i++){
                    myProducer.send(new ProducerRecord<String, String>("test","message","Poop Value : " + Integer.toString(i)));
//                myProducer.send(new  ProducerRecord<String, String>("my-fifth-topic", "url:<local-directory-path>/file"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                myProducer.close();
            }
        }

    }

