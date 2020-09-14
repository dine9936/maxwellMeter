package com.rama.mijmeterapp;

public class ConvertHalfData {




    public static String getDate(String data) {


            String year = data.substring(6,10);
            int yearInt = convert(year);
            String month = data.substring(10,12);
            int monthInt = convert(month);
            String day = data.substring(12,14);
            int dayInt = convert(day);


            return dayInt + "/" + monthInt + "/" + yearInt;
        }

    public static String getTime(String data) {

            String hr = data.substring(16, 18);
            int hrInt = convert(hr);

            String min = data.substring(18,20);
            int minInt = convert(min);

            String sec = data.substring(20,22);
            int secInt = convert(sec);

            return hrInt + ":" + minInt +":" + secInt;
        }

    public static String getKwh(String data) {

            String value = data.substring(30, 34);
            double val = (double) convert(reverseOne(value))/100;
            value = Double.toString(val);
            return value;
        }

    public static String getKvarhLag(String data) {

            String value = data.substring(34, 38);
            double val = (double) convert(reverseOne(value))/100;
            value = Double.toString(val);
            return value;
        }

    public static String getKvarhLead(String data) {

            String value = data.substring(38, 42);
            double val = (double) convert(reverseOne(value))/100;
            value = Double.toString(val);
            return value;
        }

    public static String getKvah(String data) {

            String value = data.substring(42, 46);
            double val = (double) convert(reverseOne(value))/100;
            value = Double.toString(val);
            return value;
        }

    public static String getRvolt(String data) {

            String value = data.substring(46, 48);
            double val = (double) convert(value) *2;
            value = Double.toString(val);
            return value;
        }

    public    static String getYvolt(String data) {

            String value = data.substring(48, 50);
            double val = (double) convert(value)*2;
            value = Double.toString(val);
            return value;
        }

    public    static String getBvoltage(String data) {

            String value = data.substring(50, 52);
            double val = (double) convert(value)*2;
            value = Double.toString(val);
            return value;
        }

    public   static String getRcurrent(String data) {

            String value = data.substring(52, 56);
            double val = (double) convert(value)/100;
            value = Double.toString(val);
            return value;
        }

    public    static String getYcurrent(String data) {

            String value = data.substring(56, 60);
            double val = (double) convert(value)/100;
            value = Double.toString(val);
            return value;
        }

    public    static String getBcurrent(String data) {

            String value = data.substring(60, 64);
            double val = (double) convert(value)/100;
            value = Double.toString(val);
            return value;
        }

        private static int convert(String hex) {

            return Integer.parseInt(hex,16);

        }

        private static String reverseOne(String data) {
            String[] arrData = {data.substring(0,2), data.substring(2,4)};
            String revData = "";
            for(int i=1; i>=0; i--) {
                revData = revData + arrData[i];
            }
            return revData;
        }

    }

