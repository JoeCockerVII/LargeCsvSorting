package com.example.stringutils;

import java.math.*;

public class StringUtils {

    public String replace(String str, char oldElem, char newElem) {

        if (str == null || str.equals("")) throw new IllegalArgumentException("Incorrect argument: {" + str + "}");

        char[] chStr = str.toCharArray();

        for (int i = 0; i < chStr.length; i++) {
            if (chStr[i] == oldElem) {
                chStr[i] = newElem;
            }
        }
        return new String(chStr);
    }

    // Methods: Double.valueOf(str), Double.parseDouble(str) and RegExp constructions;
    public double stringToDouble(String str) {
        str = str.trim();

        if (str == null || str.equals(""))
            throw new IllegalArgumentException("Incorrect argument: {" + str + "}");

        double value = 0, result = 0, scale = 0;
        boolean isPositive = true;
        boolean isFractional = false;
        int fractionalNum = 0;
        int denominator = 1;
        /*
            First symbol checking for Sign and Number
        */
        if (str.charAt(0) == '-')
            isPositive = false;
        else if (str.charAt(0) == '+' || Character.isDigit(str.charAt(0)))
            isPositive = true;
        else
            throw new IllegalArgumentException("Incorrect argument: {" + str + "}");

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '+' || ch == '-') continue;

            if (!Character.isDigit(ch)) {
                if ((ch == '.' || ch == ',') && !isFractional && Character.isDigit(str.charAt(i - 1)))
                    isFractional = true;
                else
                    throw new IllegalArgumentException("Incorrect argument: {" + str + "}");
            } else {
                value = ch - '0';
                if (!isFractional)
                    result = result * 10 + value;
                else {
                    fractionalNum++;
                    denominator *= 10;
                    double tmp = value / denominator;
                    tmp = tmp;
                    result = result +  tmp;
                }
            }
        }
        scale = Math.pow(10, fractionalNum);
        result = Math.ceil(result * scale) / scale;
        return isPositive ? result : -result;
    }
}
