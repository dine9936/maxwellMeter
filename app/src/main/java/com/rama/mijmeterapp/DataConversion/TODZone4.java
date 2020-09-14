package com.rama.mijmeterapp.DataConversion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TODZone4 {

    public static int getKWH(String data) {

        String value = data.substring(170, 178);
        String revData = reverseTwo(value);
        int val = convert(revData);
        return val;
    }

    public  static int getKVARHLag(String data) {

        String value = data.substring(178, 186);
        String revData = reverseTwo(value);
        int val = convert(revData);
        return val;
    }

    public   static int getKVARHLead(String data) {

        String value = data.substring(186, 194);
        String revData = reverseTwo(value);
        int val = convert(revData);
        return val;
    }

    public   static int getKVAH(String data) {

        String value = data.substring(194, 202);
        String revData = reverseTwo(value);
        int val = convert(revData);
        return val;
    }

    public  static int getMDKW(String data) {

        String value = data.substring(202, 206);
        String revData = reverseOne(value);

        int val = convert(revData);
        return val;
    }

    public   static String getMDKWTime(String data) {

        String value = data.substring(206, 214);
        String revData = reverseTwo(value);

        int val = convert(revData);
        revData = convertDate(val);

        return revData;
    }

    public   static int getMDKVA(String data) {

        String value = data.substring(214, 218);
        String revData = reverseOne(value);

        int val = convert(revData);
        return val;
    }

 public    static String getMDKVATime(String data) {

        String value = data.substring(218, 226);
        String revData = reverseTwo(value);

     int val = convert(revData);
        revData = convertDate(val);

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

    private static String convertDate(long timestamp) {

        timestamp = timestamp + 946684800;
        long unixSeconds = (long) timestamp;
        // convert seconds to milliseconds
        Date date = new Date(unixSeconds*1000L);
        // the format of your date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+0:00"));
        String formattedDate = sdf.format(date);

        return formattedDate;
    }
}
