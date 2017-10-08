package com.example.moveup;

/**
 * Created by jiangyiming on 10/8/17.
 */

public class Util {
    public static final String category1 = "Underweight";
    public static final String category2 = "Normal (healthy weight)";
    public static final String category3 = "Overweight";
    public static final String category4 = "Obese Class I (Moderately obese)";
    public static final String category5 = "Obese Class II (Severely obese)";
    public static final String category6 = "Obese Class III (Very severely obese)";


    public static Double calculateBMI(int height, int weight) {
        double height1 = (double) (height) / 100;
        return ((double) weight) / (height1 * height1);
    }

    public static int getResult(Double BMI) {
        int result;
        if (BMI < 18.5) {
            result = 1;
        } else if (BMI < 25) {
            result = 2;
        } else if (BMI < 30) {
            result = 3;
        } else if (BMI < 35) {
            result = 4;
        } else if (BMI < 40) {
            result = 5;
        } else {
            result = 6;
        }
        return result;
    }
}
