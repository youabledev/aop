package com.youable.aop_example.common;

import java.util.concurrent.ThreadLocalRandom;

public class UniqueValueGenerator {
    public static String getAccountNumber() {
        String part1 = String.format("%06d", ThreadLocalRandom.current().nextInt(0, 1000000));
        String part2 = String.format("%02d", ThreadLocalRandom.current().nextInt(0, 100));
        String part3 = String.format("%04d", ThreadLocalRandom.current().nextInt(0, 10000));

        return part1 + "-" + part2 + "-" + part3;
    }
}