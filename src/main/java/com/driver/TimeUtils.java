package com.driver;

import java.util.Arrays;
import java.util.List;

public class TimeUtils {


    private TimeUtils(){

    }
    public static int convertTime(String deliveryTime) {
        List<String> list= Arrays.asList(deliveryTime.split(":"));
        int HH=Integer.parseInt(list.get(0));

        int MM=Integer.parseInt(list.get(1));

        return HH*60 + MM;
    }

    public static String convertTime(int deliveryTime){
        int HH = deliveryTime / 60;
        int MM = deliveryTime % 60;
        String n1 = String.valueOf(HH);
        String n2 = String.valueOf(MM);
        if(n1.length()==1){
            n1='0'+n1;
        }
        if(n2.length()==1){
            n2='0'+n2;
        }
        return n1 + ":" + n2;
    }
}
