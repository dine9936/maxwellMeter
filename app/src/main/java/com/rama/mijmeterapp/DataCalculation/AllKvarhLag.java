package com.rama.mijmeterapp.DataCalculation;

import com.rama.mijmeterapp.DataConversion.TODZone1;
import com.rama.mijmeterapp.DataConversion.TODZone2;
import com.rama.mijmeterapp.DataConversion.TODZone3;
import com.rama.mijmeterapp.DataConversion.TODZone4;
import com.rama.mijmeterapp.DataConversion.TODZone5;
import com.rama.mijmeterapp.DataConversion.TODZone6;
import com.rama.mijmeterapp.DataConversion.TODZone7;
import com.rama.mijmeterapp.DataConversion.TODZone8;

public class AllKvarhLag {
    double tod1,tod2,tod3,tod4,tod5,tod6,tod7,tod8;

    public String consumedKvarhLagTod1(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone1.getKVARHLag(current);
        int prevkwhtod1 = TODZone1.getKVARHLag(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod1 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }
    public String consumedKvarhLagTod2(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone2.getKVARHLag(current);
        int prevkwhtod1 = TODZone2.getKVARHLag(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod2 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }


    public String consumedKvarhLagTod3(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone3.getKVARHLag(current);
        int prevkwhtod1 = TODZone3.getKVARHLag(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod3 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }
    public String consumedKvarhLagTod4(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone4.getKVARHLag(current);
        int prevkwhtod1 = TODZone4.getKVARHLag(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod4 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }

    public String consumedKvarhLagTod5(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone5.getKVARHLag(current);
        int prevkwhtod1 = TODZone5.getKVARHLag(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod5 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }
    public String consumedKvarhLagTod6(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone6.getKVARHLag(current);
        int prevkwhtod1 = TODZone6.getKVARHLag(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod6 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }
    public String consumedKvarhLagTod7(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone7.getKVARHLag(current);
        int prevkwhtod1 = TODZone7.getKVARHLag(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod7 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }
    public String consumedKvarhLagTod8(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone8.getKVARHLag(current);
        int prevkwhtod1 = TODZone8.getKVARHLag(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod8 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }
    public String totalConsumedKvarhlag(String current, String pre){
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
