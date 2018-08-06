package com.learnkafka.consumer.partitioner.producer;

import java.util.UUID;

public class RandomString {

    public static void main(String[] args) {
        System.out.println(generateString());
    }

    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

}