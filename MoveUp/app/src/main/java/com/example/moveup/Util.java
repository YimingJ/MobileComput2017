package com.example.moveup;

/**
 * Created by jiangyiming on 10/8/17.
 */

public class Util {
    public static Double calculateBMI(int height, int weight) {
        double height1 = (double) (height) / 100;
        return ((double) weight) / (height1 * height1);
    }
}
