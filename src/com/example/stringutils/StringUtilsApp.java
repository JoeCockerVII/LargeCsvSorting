package com.example.stringutils;

public class StringUtilsApp {

    public static void main(String[] args) {
        StringUtils stringUtils = new StringUtils();

        String str = "String with zeros: 000";
        System.out.println(str);

        try {
            System.out.println(stringUtils.replace(str, '0','1'));
        }
        catch(IllegalArgumentException e){System.out.println(e.getMessage());}

        try {
            System.out.println(stringUtils.replace("", '0','1'));
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(stringUtils.replace(null, '0','1'));
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }


        System.out.println("====================================");
        String str1 = "2с";
        System.out.println("Original= " + str1);
        try {
            System.out.println("Double =  " + stringUtils.stringToDouble(str1));
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        System.out.println("====================================");
        str1 = "0";
        System.out.println("Original= " + str1);
        try {
            System.out.println("Double =  " + stringUtils.stringToDouble(str1));
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        System.out.println("====================================");
        str1 = "-100";
        System.out.println("Original= " + str1);
        try {
            System.out.println("Double =  " + stringUtils.stringToDouble(str1));
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        System.out.println("====================================");
        str1 = "0.5";
        System.out.println("Original= " + str1);
        try {
            System.out.println("Double =  " + stringUtils.stringToDouble(str1));
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        System.out.println("====================================");
        str1 = "10.00436";
        System.out.println("Original= " + str1);
        try {
            System.out.println("Double =  " + stringUtils.stringToDouble(str1));
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        System.out.println("====================================");
        str1 = "10.00000436";
        System.out.println("Original= " + str1);
        try {
            System.out.println("Double =  " + stringUtils.stringToDouble(str1));
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        System.out.println("====================================");
        str1 = "-100.2233";
        System.out.println("Original= " + str1);
        try {
            System.out.println("Double =  " + stringUtils.stringToDouble(str1));
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        System.out.println("====================================");
        str1 = "-1.002";
        System.out.println("Original= " + str1);
        try {
            System.out.println("Double =  " + stringUtils.stringToDouble(str1));
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        System.out.println("====================================");
        str1 = "--1.002";
        System.out.println("Original= " + str1);
        try {
            System.out.println("Double =  " + stringUtils.stringToDouble(str1));
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        

    }
}
