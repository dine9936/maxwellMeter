package com.rama.mijmeterapp.DataCalculation;

import com.rama.mijmeterapp.DataConversion.TODZone1;
import com.rama.mijmeterapp.DataConversion.TODZone2;
import com.rama.mijmeterapp.DataConversion.TODZone3;
import com.rama.mijmeterapp.DataConversion.TODZone4;
import com.rama.mijmeterapp.DataConversion.TODZone5;
import com.rama.mijmeterapp.DataConversion.TODZone6;
import com.rama.mijmeterapp.DataConversion.TODZone7;
import com.rama.mijmeterapp.DataConversion.TODZone8;

public class AllKvahData {
    double tod1,tod2,tod3,tod4,tod5,tod6,tod7,tod8;

    public String consumedKvahTod1(String current, String prev) {
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone1.getKVAH(current);
        int prevkwhtod1 = TODZone1.getKVAH(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod1 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }

    public String consumedKvahTod2(String current, String prev) {


        String consumedkwhtod1;
        int currentkwhtod1 = TODZone2.getKVAH(current);
        int prevkwhtod1 = TODZone2.getKVAH(prev);

        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod2 = a;

        consumedkwhtod1 = String.valueOf(a);


        return consumedkwhtod1;


    }

    public String consumedKvahTod3(String current, String prev) {


        String consumedkwhtod1;
        int currentkwhtod1 = TODZone3.getKVAH(current);
        int prevkwhtod1 = TODZone3.getKVAH(prev);

        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod3 = a;
        consumedkwhtod1 = String.valueOf(a);


        return consumedkwhtod1;


    }

    public String consumedKvahTod4(String current, String prev) {


        String consumedkwhtod1;
        int currentkwhtod1 = TODZone4.getKVAH(current);
        int prevkwhtod1 = TODZone4.getKVAH(prev);

        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod4 = a;

        consumedkwhtod1 = String.valueOf(a);


        return consumedkwhtod1;


    }

    public String consumedKvahTod5(String current, String prev) {


        String consumedkwhtod1;
        int currentkwhtod1 = TODZone5.getKVAH(current);
        int prevkwhtod1 = TODZone5.getKVAH(prev);

        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod5 = a;
        consumedkwhtod1 = String.valueOf(a);


        return consumedkwhtod1;


    }

    public String consumedKvahTod6(String current, String prev) {


        String consumedkwhtod1;
        int currentkwhtod1 = TODZone6.getKVAH(current);
        int prevkwhtod1 = TODZone6.getKVAH(prev);

        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod6 = a;
        consumedkwhtod1 = String.valueOf(a);


        return consumedkwhtod1;


    }

    public String consumedKvahTod7(String current, String prev) {


        String consumedkwhtod1;
        int currentkwhtod1 = TODZone7.getKVAH(current);
        int prevkwhtod1 = TODZone7.getKVAH(prev);

        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod7 = a;
        consumedkwhtod1 = String.valueOf(a);


        return consumedkwhtod1;


    }

    public String consumedKvahTod8(String current, String prev) {


        String consumedkwhtod1;
        int currentkwhtod1 = TODZone8.getKVAH(current);
        int prevkwhtod1 = TODZone8.getKVAH(prev);

        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod8 = a;
        consumedkwhtod1 = String.valueOf(a);


        return consumedkwhtod1;


    }
    public String totalConsumedKvah(String current, String pre){
        String totalkwh;

        float total = (float)( tod1+tod8+tod4+tod3+tod2+tod5+tod6+tod7);


        totalkwh = String.valueOf(total);


        return totalkwh;
    }
    public double modofValue(double value){
        double result;
        if (value<0){
            result = -(value);
        }
        else {
            result = value;
        }
        return  result;
    }
}
