package org.academiadecodigo.bootcamp.filipejorge.uberlisbondriver;

import java.util.Random;

/**
 * Created by Filipe Jorge <Academia de Código_> on 25/01/16.
 */
public class RandomF {

    public static int getRandom(int min, int max) {

        return min + (int) (Math.random() * (max - min + 1));
    }


    public static float getRandomFloat(int min, int max) {

        final int DECIMALS = 1000000;

        Random random = new Random();

        min *= DECIMALS;
        max *= DECIMALS;

        int number = random.nextInt(max + 1 - min) + min;
        float numberF = (float) number;

        return numberF / DECIMALS;
    }

}
