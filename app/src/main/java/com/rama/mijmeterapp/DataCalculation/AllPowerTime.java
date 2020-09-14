package com.rama.mijmeterapp.DataCalculation;

import com.rama.mijmeterapp.DataConversion.BillingThreePhaseData;
import com.rama.mijmeterapp.DataConversion.TODZone1;

public class AllPowerTime {

    public static String powerOnTime(String data){
        String consumedkwhtod1;
         consumedkwhtod1 = BillingThreePhaseData.getPon(data);

        return consumedkwhtod1;
    }
}
