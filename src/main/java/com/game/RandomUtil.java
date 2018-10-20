package com.game;

import java.util.Random;

public class RandomUtil {

    public static double generateRandomInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
