package com.rama.mijmeterapp.DataCalculation;

import com.rama.mijmeterapp.DataConversion.BillingThreePhaseData;
import com.rama.mijmeterapp.DataConversion.TODZone1;

public class AllAvgPow {
    public static String values(String data){

        String consumedkwhtod1;
        int currentkwhtod1 = BillingThreePhaseData.getAvgPF(data);

        double a = (double) (currentkwhtod1 ) / 100;
        consumedkwhtod1 = String.valueOf(a);
        return consumedkwhtod1;
    }
}
