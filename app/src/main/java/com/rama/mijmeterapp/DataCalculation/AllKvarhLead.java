package com.rama.mijmeterapp.DataCalculation;

import com.rama.mijmeterapp.DataConversion.TODZone1;
import com.rama.mijmeterapp.DataConversion.TODZone2;
import com.rama.mijmeterapp.DataConversion.TODZone3;
import com.rama.mijmeterapp.DataConversion.TODZone4;
import com.rama.mijmeterapp.DataConversion.TODZone5;
import com.rama.mijmeterapp.DataConversion.TODZone6;
import com.rama.mijmeterapp.DataConversion.TODZone7;
import com.rama.mijmeterapp.DataConversion.TODZone8;

public class AllKvarhLead {
    double tod1,tod2,tod3,tod4,tod5,tod6,tod7,tod8;
    public String consumedKvarhLeadTod1(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone1.getKVARHLead(current);
        int prevkwhtod1 = TODZone1.getKVARHLead(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod1 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }
    public String consumedKvarhLeadTod2(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone2.getKVARHLead(current);
        int prevkwhtod1 = TODZone2.getKVARHLead(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod2 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }


    public String consumedKvarhLeadTod3(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone3.getKVARHLead(current);
        int prevkwhtod1 = TODZone3.getKVARHLead(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod3 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }
    public String consumedKvarhLeadTod4(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone4.getKVARHLead(current);
        int prevkwhtod1 = TODZone4.getKVARHLead(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod4 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }

    public String consumedKvarhLeadTod5(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone5.getKVARHLead(current);
        int prevkwhtod1 = TODZone5.getKVARHLead(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod5 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }
    public String consumedKvarhLeadTod6(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone6.getKVARHLead(current);
        int prevkwhtod1 = TODZone6.getKVARHLead(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod6 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }
    public String consumedKvarhLeadTod7(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone7.getKVARHLead(current);
        int prevkwhtod1 = TODZone7.getKVARHLead(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod7 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }
    public String consumedKvarhLeadTod8(String current,String prev){
        String consumedkwhtod1;
        int currentkwhtod1 = TODZone8.getKVARHLead(current);
        int prevkwhtod1 = TODZone8.getKVARHLead(prev);
        double a = modofValue((double) (currentkwhtod1-prevkwhtod1)/100);
        tod8 = a;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }

    public String totalConsumedKvarhlead(String current, String pre){
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
