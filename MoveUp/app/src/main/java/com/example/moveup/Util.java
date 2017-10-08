package com.example.moveup;

/**
 * Created by jiangyiming on 10/8/17.
 */

public class Util {
    public static final String category1 = "YourBMI result shows you are underweight. It is likely to lead to poor immunity, gallstones and anaemia. We suggest you pay attention to nutrition balance and have a healthy diet. Try the stretch exercise!";
    public static final String category2 = "Your BMI result shows you are in a healthy weight. Congratulations! Reasonable exercise and diet can keep you in shape. Try some strength exercise!";
    public static final String category3 = "Your BMI result shows you are overweight. It may lead to hyperglycaemia and hypertension. Having more vegetables and less meat are good for you. We suggest you do some yoga exercise! ";
    public static final String category4 = "Your BMI shows you are in obese class I. Moderate obesity carries an increased risk of conditions such as: hypertension, heart disease and type 2 diabetes. Please exercise 4 times a week, at least 40 minutes for each. Try “lose weight” exercise!";
    public static final String category5 = "Your BMI shows you are in obese class II. Medium obesity carries an increased risk of conditions such as: hypertension, heart disease and type 2 diabetes. Please exercise 4 times a week, at least 40 minutes for each. Try “lose weight” exercise!";
    public static final String category6 = "Your BMI shows you are in obese class II. Medium obesity carries an increased risk of conditions such as: hypertension, heart disease and type 2 diabetes. Please exercise 4 times a week, at least 40 minutes for each. We strongly recommend you seek a medication assistance.";


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
