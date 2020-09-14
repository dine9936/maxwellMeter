package com.rama.mijmeterapp.DataConversion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BillingThreePhaseData {

    public static String getTableHeading(String data) {
        return data.substring(2,4);
    }

    public static String getKWH(String data) {


        String value = data.substring(4, 12);
        String revData = reverseTwo(value);
        double val = (double) convert(revData)/100;
        revData = Double.toString(val);

        return revData;
    }

    public static String getKVARHLag(String data) {

        String value = data.substring(12, 20);
        String revData = reverseTwo(value);

        double val = (double) convert(revData)/100;
        revData = Double.toString(val);
        return revData;
    }

    public  static String getKVARHLead(String data) {

        String value = data.substring(20, 28);
        String revData = reverseTwo(value);

        double val = (double) convert(revData)/100;
        revData = Double.toString(val);
        return revData;
    }

    public  static String getKVAH(String data) {

        String value = data.substring(28, 36);
        String revData = reverseTwo(value);

        double val = (double) convert(revData)/100;
        revData = Double.toString(val);
        return revData;
    }

    public static String getMDKW(String data) {

        String value = data.substring(36, 40);
        String revData = reverseOne(value);

        double val = (double) convert(revData)/100;
        revData = Double.toString(val);

        return revData;
    }

    public static String getMDKWTime(String data) {

        String value = data.substring(40, 48);
        String revData = reverseTwo(value);

        int val = convert(revData);
        revData = convertDate(val);

        return revData;
    }

    public  static String getMDKVA(String data) {

        String value = data.substring(48, 52);
        String revData = reverseOne(value);

        double val = (double) convert(revData)/100;
        revData = Double.toString(val);
        return revData;
    }

    public static String getMDKVATime(String data) {

        String value = data.substring(52, 60);
        String revData = reverseTwo(value);

        int val = convert(revData);
        revData = convertDate(val);

        return revData;
    }

    public  static String getPoff(String data) {

        String value = data.substring(60, 68);
        String revData = reverseTwo(value);

        int val = convert(revData);
        revData = Integer.toString(val);

        return revData;
    }

    public  static String getPon(String data) {

        String value = data.substring(68, 76);
        String revData = reverseTwo(value);

        int val = convert(revData);
        revData = Integer.toString(val);

        return revData;
    }

    public  static int getAvgPF(String data) {

        String value = data.substring(76, 80);
        String revData = reverseOne(value);

        int val =  convert(revData);
        revData = Double.toString(val);

        return val;
    }

    public  static String getTCount(String data) {

        String value = data.substring(80, 84);
        String revData = reverseOne(value);

        int val = convert(revData);
        revData = Integer.toString(val);

        return revData;
    }

    public  static String getMDResetCount(String data) {

        String value = data.substring(84, 88);
        String revData = reverseOne(value);

        int val = convert(revData);
        revData = Integer.toString(val);

        return revData;
    }

    public static String getBillTimeOffset(String data) {

        String value = data.substring(88, 96);
        String revData = reverseTwo(value);

        int val = convert(revData);
        revData = convertDate(val);

        return revData;
    }

    public  static String getMDKWCumlative(String data) {

        String value = data.substring(96, 104);
        String revData = reverseTwo(value);

        double val = (double) convert(revData)/100;
        revData = Double.toString(val);
        return revData;
    }

    public static String getMDKVACumlative(String data) {

        String value = data.substring(104, 112);
        String revData = reverseTwo(value);

        double val = (double) convert(revData)/100;
        revData = Double.toString(val);
        return revData;
    }


    private static int convert(String hex) {

        return Integer.parseInt(hex,16);

    }

    private static String reverseTwo(String data) {
        String[] arrData = {data.substring(0,2), data.substring(2,4), data.substring(4,6), data.substring(6,8)};
        String revData = "";
        for(int i=3; i>=0; i--) {
            revData = revData + arrData[i];
        }
        return revData;
    }

    private static String reverseOne(String data) {
        String[] arrData = {data.substring(0,2), data.substring(2,4)};
        String revData = "";
        for(int i=1; i>=0; i--) {
            revData = revData + arrData[i];
        }
        return revData;
    }

    private static String convertDate(int timestamp) {

        timestamp = timestamp + 946684800;
        long unixSeconds = (long) timestamp;
        // convert seconds to milliseconds
        Date date = new java.util.Date(unixSeconds*1000L);
        // the format of your date
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+0:00"));
        String formattedDate = sdf.format(date);

        return formattedDate;
    }
}