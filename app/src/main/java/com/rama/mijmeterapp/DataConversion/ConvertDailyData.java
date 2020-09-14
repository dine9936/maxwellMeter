package com.rama.mijmeterapp.DataConversion;

public class ConvertDailyData {

    public static String getDay(String data) {


        String day = data.substring(2,4);
        int i = convert(day);
        day = String.valueOf(i);
        return day;
    }

    public static String getDate(String data) {


        String year = data.substring(4,8);
        int yearInt = convert(year);
        String month = data.substring(8,10);
        int monthInt = convert(month);
        String day = data.substring(10,12);
        int dayInt = convert(day);


        return dayInt + "/" + monthInt + "/" + yearInt;
    }
    public static String getTime(String data) {

        String hr = data.substring(14, 16);
        int hrInt = convert(hr);

        String min = data.substring(16,18);
        int minInt = convert(min);

        String sec = data.substring(18,20);
        int secInt = convert(sec);

        return hrInt + ":" + minInt +":" + secInt;
    }

    public static String getKwh(String data) {

        String value = data.substring(28, 36);
        double val = (double) convert(reverseOne(value))/100;
        value = Double.toString(val);
        return value;
    }

    public static String getKvah(String data) {

        String value = data.substring(36, 44);
        double val = (double) convert(reverseOne(value))/100;
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
