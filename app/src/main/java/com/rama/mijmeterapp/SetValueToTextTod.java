package com.rama.mijmeterapp;

import android.widget.LinearLayout;
import android.widget.TextView;

public class SetValueToTextTod {
    public TextView textViewtotalcurrent, textViewtod1current, textViewtod2current, textViewtod3current, textViewtod4current, textViewtod5current, textViewtod6current, textViewtod7current, textViewtod8current;
    public TextView textViewtotal1, textViewtod11, textViewtod21, textViewtod31, textViewtod41, textViewtod51, textViewtod61, textViewtod71, textViewtod81;
    public TextView textViewtotal2, textViewtod12, textViewtod22, textViewtod32, textViewtod42, textViewtod52, textViewtod62, textViewtod72, textViewtod82;
    public TextView textViewtotal3, textViewtod13, textViewtod23, textViewtod33, textViewtod43, textViewtod53, textViewtod63, textViewtod73, textViewtod83;
    public TextView textViewtotal4, textViewtod14, textViewtod24, textViewtod34, textViewtod44, textViewtod54, textViewtod64, textViewtod74, textViewtod84;
    public TextView textViewtotal5, textViewtod15, textViewtod25, textViewtod35, textViewtod45, textViewtod55, textViewtod65, textViewtod75, textViewtod85;
    public TextView textViewtotal6, textViewtod16, textViewtod26, textViewtod36, textViewtod46, textViewtod56, textViewtod66, textViewtod76, textViewtod86;
    public TextView textViewtotal7, textViewtod17, textViewtod27, textViewtod37, textViewtod47, textViewtod57, textViewtod67, textViewtod77, textViewtod87;
    public TextView textViewtotal8, textViewtod18, textViewtod28, textViewtod38, textViewtod48, textViewtod58, textViewtod68, textViewtod78, textViewtod88;
    public TextView textViewtotal9, textViewtod19, textViewtod29, textViewtod39, textViewtod49, textViewtod59, textViewtod69, textViewtod79, textViewtod89;
    public TextView textViewtotal10, textViewtod110, textViewtod210, textViewtod310, textViewtod410, textViewtod510, textViewtod610, textViewtod710, textViewtod810;
    public TextView textViewtotal11, textViewtod111, textViewtod211, textViewtod311, textViewtod411, textViewtod511, textViewtod611, textViewtod711, textViewtod811;



    public void setTextValue(int month,LinearLayout horizontalScrollView,String total, String tod1,String tod2,String tod3,String tod4,String tod5,String tod6,String tod7,String tod8){


        textViewtotalcurrent = horizontalScrollView.getChildAt(1).findViewById(R.id.current_total);
        textViewtotal1 = horizontalScrollView.getChildAt(2).findViewById(R.id.one_total);
        textViewtotal2 = horizontalScrollView.getChildAt(3).findViewById(R.id.two_total);
        textViewtotal3 = horizontalScrollView.getChildAt(4).findViewById(R.id.three_total);
        textViewtotal4 = horizontalScrollView.getChildAt(5).findViewById(R.id.four_total);
        textViewtotal5 = horizontalScrollView.getChildAt(6).findViewById(R.id.five_total);
        textViewtotal6 = horizontalScrollView.getChildAt(7).findViewById(R.id.six_total);
        textViewtotal7 = horizontalScrollView.getChildAt(8).findViewById(R.id.seven_total);
        textViewtotal8 = horizontalScrollView.getChildAt(9).findViewById(R.id.eight_total);
        textViewtotal9 = horizontalScrollView.getChildAt(10).findViewById(R.id.nine_total);
        textViewtotal10 = horizontalScrollView.getChildAt(11).findViewById(R.id.ten_total);
        textViewtotal11 = horizontalScrollView.getChildAt(12).findViewById(R.id.eleven_total);


///////////////////////////////

        textViewtod1current = horizontalScrollView.getChildAt(1).findViewById(R.id.current_tod1);
        textViewtod11 = horizontalScrollView.getChildAt(2).findViewById(R.id.one_tod1);
        textViewtod12 = horizontalScrollView.getChildAt(3).findViewById(R.id.two_tod1);
        textViewtod13 = horizontalScrollView.getChildAt(4).findViewById(R.id.three_tod1);
        textViewtod14 = horizontalScrollView.getChildAt(5).findViewById(R.id.four_tod1);
        textViewtod15 = horizontalScrollView.getChildAt(6).findViewById(R.id.five_tod1);
        textViewtod16 = horizontalScrollView.getChildAt(7).findViewById(R.id.six_tod1);
        textViewtod17 = horizontalScrollView.getChildAt(8).findViewById(R.id.seven_tod1);
        textViewtod18 = horizontalScrollView.getChildAt(9).findViewById(R.id.eight_tod1);
        textViewtod19 = horizontalScrollView.getChildAt(10).findViewById(R.id.nine_tod1);
        textViewtod110 = horizontalScrollView.getChildAt(11).findViewById(R.id.ten_tod1);
        textViewtod111 = horizontalScrollView.getChildAt(12).findViewById(R.id.eleven_tod1);


        /////////////////////////////

        textViewtod2current = horizontalScrollView.getChildAt(1).findViewById(R.id.current_tod2);
        textViewtod21 = horizontalScrollView.getChildAt(2).findViewById(R.id.one_tod2);
        textViewtod22 = horizontalScrollView.getChildAt(3).findViewById(R.id.two_tod2);
        textViewtod23 = horizontalScrollView.getChildAt(4).findViewById(R.id.three_tod2);
        textViewtod24 = horizontalScrollView.getChildAt(5).findViewById(R.id.four_tod2);
        textViewtod25 = horizontalScrollView.getChildAt(6).findViewById(R.id.five_tod2);
        textViewtod26 = horizontalScrollView.getChildAt(7).findViewById(R.id.six_tod2);
        textViewtod27 = horizontalScrollView.getChildAt(8).findViewById(R.id.seven_tod2);
        textViewtod28 = horizontalScrollView.getChildAt(9).findViewById(R.id.eight_tod2);
        textViewtod29 = horizontalScrollView.getChildAt(10).findViewById(R.id.nine_tod2);
        textViewtod210 = horizontalScrollView.getChildAt(11).findViewById(R.id.ten_tod2);
        textViewtod211 = horizontalScrollView.getChildAt(12).findViewById(R.id.eleven_tod2);


        /////////////////////////////////////


        textViewtod3current = horizontalScrollView.getChildAt(1).findViewById(R.id.current_tod3);
        textViewtod31 = horizontalScrollView.getChildAt(2).findViewById(R.id.one_tod3);
        textViewtod32 = horizontalScrollView.getChildAt(3).findViewById(R.id.two_tod3);
        textViewtod33 = horizontalScrollView.getChildAt(4).findViewById(R.id.three_tod3);
        textViewtod34 = horizontalScrollView.getChildAt(5).findViewById(R.id.four_tod3);
        textViewtod35 = horizontalScrollView.getChildAt(6).findViewById(R.id.five_tod3);
        textViewtod36 = horizontalScrollView.getChildAt(7).findViewById(R.id.six_tod3);
        textViewtod37 = horizontalScrollView.getChildAt(8).findViewById(R.id.seven_tod3);
        textViewtod38 = horizontalScrollView.getChildAt(9).findViewById(R.id.eight_tod3);
        textViewtod39 = horizontalScrollView.getChildAt(10).findViewById(R.id.nine_tod3);
        textViewtod310 = horizontalScrollView.getChildAt(11).findViewById(R.id.ten_tod3);
        textViewtod311 = horizontalScrollView.getChildAt(12).findViewById(R.id.eleven_tod3);


        ////////////////////////////

        textViewtod4current = horizontalScrollView.getChildAt(1).findViewById(R.id.current_tod4);
        textViewtod41 = horizontalScrollView.getChildAt(2).findViewById(R.id.one_tod4);
        textViewtod42 = horizontalScrollView.getChildAt(3).findViewById(R.id.two_tod4);
        textViewtod43 = horizontalScrollView.getChildAt(4).findViewById(R.id.three_tod4);
        textViewtod44 = horizontalScrollView.getChildAt(5).findViewById(R.id.four_tod4);
        textViewtod45 = horizontalScrollView.getChildAt(6).findViewById(R.id.five_tod4);
        textViewtod46 = horizontalScrollView.getChildAt(7).findViewById(R.id.six_tod4);
        textViewtod47 = horizontalScrollView.getChildAt(8).findViewById(R.id.seven_tod4);
        textViewtod48 = horizontalScrollView.getChildAt(9).findViewById(R.id.eight_tod4);
        textViewtod49 = horizontalScrollView.getChildAt(10).findViewById(R.id.nine_tod4);
        textViewtod410 = horizontalScrollView.getChildAt(11).findViewById(R.id.ten_tod4);
        textViewtod411 = horizontalScrollView.getChildAt(12).findViewById(R.id.eleven_tod4);


        //////////////////////////////////////////


        textViewtod5current = horizontalScrollView.getChildAt(1).findViewById(R.id.current_tod5);
        textViewtod51 = horizontalScrollView.getChildAt(2).findViewById(R.id.one_tod5);
        textViewtod52 = horizontalScrollView.getChildAt(3).findViewById(R.id.two_tod5);
        textViewtod53 = horizontalScrollView.getChildAt(4).findViewById(R.id.three_tod5);
        textViewtod54 = horizontalScrollView.getChildAt(5).findViewById(R.id.four_tod5);
        textViewtod55 = horizontalScrollView.getChildAt(6).findViewById(R.id.five_tod5);
        textViewtod56 = horizontalScrollView.getChildAt(7).findViewById(R.id.six_tod5);
        textViewtod57 = horizontalScrollView.getChildAt(8).findViewById(R.id.seven_tod5);
        textViewtod58 = horizontalScrollView.getChildAt(9).findViewById(R.id.eight_tod5);
        textViewtod59 = horizontalScrollView.getChildAt(10).findViewById(R.id.nine_tod5);
        textViewtod510 = horizontalScrollView.getChildAt(11).findViewById(R.id.ten_tod5);
        textViewtod511 = horizontalScrollView.getChildAt(12).findViewById(R.id.eleven_tod5);


        ////////////////////////////////////////


        textViewtod6current = horizontalScrollView.getChildAt(1).findViewById(R.id.current_tod6);
        textViewtod61 = horizontalScrollView.getChildAt(2).findViewById(R.id.one_tod6);
        textViewtod62 = horizontalScrollView.getChildAt(3).findViewById(R.id.two_tod6);
        textViewtod63 = horizontalScrollView.getChildAt(4).findViewById(R.id.three_tod6);
        textViewtod64 = horizontalScrollView.getChildAt(5).findViewById(R.id.four_tod6);
        textViewtod65 = horizontalScrollView.getChildAt(6).findViewById(R.id.five_tod6);
        textViewtod66 = horizontalScrollView.getChildAt(7).findViewById(R.id.six_tod6);
        textViewtod67 = horizontalScrollView.getChildAt(8).findViewById(R.id.seven_tod6);
        textViewtod68 = horizontalScrollView.getChildAt(9).findViewById(R.id.eight_tod6);
        textViewtod69 = horizontalScrollView.getChildAt(10).findViewById(R.id.nine_tod6);
        textViewtod610 = horizontalScrollView.getChildAt(11).findViewById(R.id.ten_tod6);
        textViewtod611 = horizontalScrollView.getChildAt(12).findViewById(R.id.eleven_tod6);


        ///////////////////////////////////////////


        textViewtod7current = horizontalScrollView.getChildAt(1).findViewById(R.id.current_tod7);
        textViewtod71 = horizontalScrollView.getChildAt(2).findViewById(R.id.one_tod7);
        textViewtod72 = horizontalScrollView.getChildAt(3).findViewById(R.id.two_tod7);
        textViewtod73 = horizontalScrollView.getChildAt(4).findViewById(R.id.three_tod7);
        textViewtod74 = horizontalScrollView.getChildAt(5).findViewById(R.id.four_tod7);
        textViewtod75 = horizontalScrollView.getChildAt(6).findViewById(R.id.five_tod7);
        textViewtod76 = horizontalScrollView.getChildAt(7).findViewById(R.id.six_tod7);
        textViewtod77 = horizontalScrollView.getChildAt(8).findViewById(R.id.seven_tod7);
        textViewtod78 = horizontalScrollView.getChildAt(9).findViewById(R.id.eight_tod7);
        textViewtod79 = horizontalScrollView.getChildAt(10).findViewById(R.id.nine_tod7);
        textViewtod710 = horizontalScrollView.getChildAt(11).findViewById(R.id.ten_tod7);
        textViewtod711 = horizontalScrollView.getChildAt(12).findViewById(R.id.eleven_tod7);


        //////////////////////////////////////////////


        textViewtod8current = horizontalScrollView.getChildAt(1).findViewById(R.id.current_tod8);
        textViewtod81 = horizontalScrollView.getChildAt(2).findViewById(R.id.one_tod8);
        textViewtod82 = horizontalScrollView.getChildAt(3).findViewById(R.id.two_tod8);
        textViewtod83 = horizontalScrollView.getChildAt(4).findViewById(R.id.three_tod8);
        textViewtod84 = horizontalScrollView.getChildAt(5).findViewById(R.id.four_tod8);
        textViewtod85 = horizontalScrollView.getChildAt(6).findViewById(R.id.five_tod8);
        textViewtod86 = horizontalScrollView.getChildAt(7).findViewById(R.id.six_tod8);
        textViewtod87 = horizontalScrollView.getChildAt(8).findViewById(R.id.seven_tod8);
        textViewtod88 = horizontalScrollView.getChildAt(9).findViewById(R.id.eight_tod8);
        textViewtod89 = horizontalScrollView.getChildAt(10).findViewById(R.id.nine_tod8);
        textViewtod810 = horizontalScrollView.getChildAt(11).findViewById(R.id.ten_tod8);
        textViewtod811 = horizontalScrollView.getChildAt(12).findViewById(R.id.eleven_tod8);




        if (month == 0){
            textViewtod1current.setText( tod1);
            textViewtotalcurrent.setText(total );
            textViewtod8current.setText( tod2 );
            textViewtod7current.setText( tod3 );
            textViewtod6current.setText( tod4 );
            textViewtod5current.setText( tod5 );
            textViewtod4current.setText( tod6 );
            textViewtod3current.setText( tod7 );
            textViewtod2current.setText( tod8 );


        }
        else if (month == 1)
        {
           textViewtotal1.setText(total);
           textViewtod11.setText(tod1 );
           textViewtod21.setText(tod2 );
           textViewtod31.setText(tod3 );
           textViewtod41.setText(tod4 );
           textViewtod51.setText(tod5 );
           textViewtod61.setText(tod6 );
           textViewtod71.setText(tod7 );
           textViewtod81.setText(tod8 );

        }



        else if (month == 2)
        {
            textViewtotal2.setText(total);
            textViewtod12.setText(tod1 );
            textViewtod22.setText(tod2 );
            textViewtod32.setText(tod3 );
            textViewtod42.setText(tod4 );
            textViewtod52.setText(tod5 );
            textViewtod62.setText(tod6 );
            textViewtod72.setText(tod7 );
            textViewtod82.setText(tod8 );

        }
        else if (month == 3)
        {
            textViewtotal3.setText(total);
            textViewtod13.setText(tod1 );
            textViewtod23.setText(tod2 );
            textViewtod33.setText(tod3 );
            textViewtod43.setText(tod4 );
            textViewtod53.setText(tod5 );
            textViewtod63.setText(tod6 );
            textViewtod73.setText(tod7 );
            textViewtod83.setText(tod8 );

        }



        else if (month == 4)
        {
            textViewtotal4.setText(total);
            textViewtod14.setText(tod1 );
            textViewtod24.setText(tod2 );
            textViewtod34.setText(tod3 );
            textViewtod44.setText(tod4 );
            textViewtod54.setText(tod5 );
            textViewtod64.setText(tod6 );
            textViewtod74.setText(tod7 );
            textViewtod84.setText(tod8 );

        }

        else if (month == 5)
        {
            textViewtotal5.setText(total);
            textViewtod15.setText(tod1 );
            textViewtod25.setText(tod2 );
            textViewtod35.setText(tod3 );
            textViewtod45.setText(tod4 );
            textViewtod55.setText(tod5 );
            textViewtod65.setText(tod6 );
            textViewtod75.setText(tod7 );
            textViewtod85.setText(tod8 );

        }


        else if (month == 6)
        {
            textViewtotal6.setText(total);
            textViewtod16.setText(tod1 );
            textViewtod26.setText(tod2 );
            textViewtod36.setText(tod3 );
            textViewtod46.setText(tod4 );
            textViewtod56.setText(tod5 );
            textViewtod66.setText(tod6 );
            textViewtod76.setText(tod7 );
            textViewtod86.setText(tod8 );

        }


        else if (month == 7)
        {
            textViewtotal7.setText(total);
            textViewtod17.setText(tod1 );
            textViewtod27.setText(tod2 );
            textViewtod37.setText(tod3 );
            textViewtod47.setText(tod4 );
            textViewtod57.setText(tod5 );
            textViewtod67.setText(tod6 );
            textViewtod77.setText(tod7 );
            textViewtod87.setText(tod8 );

        }


        else if (month == 8)
        {
            textViewtotal8.setText(total);
            textViewtod18.setText(tod1 );
            textViewtod28.setText(tod2 );
            textViewtod38.setText(tod3 );
            textViewtod48.setText(tod4 );
            textViewtod58.setText(tod5 );
            textViewtod68.setText(tod6 );
            textViewtod78.setText(tod7 );
            textViewtod88.setText(tod8 );

        }


        else if (month == 9)
        {
            textViewtotal9.setText(total);
            textViewtod19.setText(tod1 );
            textViewtod29.setText(tod2 );
            textViewtod39.setText(tod3 );
            textViewtod49.setText(tod4 );
            textViewtod59.setText(tod5 );
            textViewtod69.setText(tod6 );
            textViewtod79.setText(tod7 );
            textViewtod89.setText(tod8 );

        }

        else if (month == 10)
        {
            textViewtotal10.setText(total);
            textViewtod110.setText(tod1 );
            textViewtod210.setText(tod2 );
            textViewtod310.setText(tod3 );
            textViewtod410.setText(tod4 );
            textViewtod510.setText(tod5 );
            textViewtod610.setText(tod6 );
            textViewtod710.setText(tod7 );
            textViewtod810.setText(tod8 );

        }

        else if (month == 11)
        {
            textViewtotal11.setText(total);
            textViewtod111.setText(tod1 );
            textViewtod211.setText(tod2 );
            textViewtod311.setText(tod3 );
            textViewtod411.setText(tod4 );
            textViewtod511.setText(tod5 );
            textViewtod611.setText(tod6 );
            textViewtod711.setText(tod7 );
            textViewtod811.setText(tod8 );

        }






    }


}
