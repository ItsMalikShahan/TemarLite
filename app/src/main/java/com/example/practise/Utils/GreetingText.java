package com.example.practise.Utils;

import java.util.Calendar;

public class GreetingText {


    public static String Greeting(){
        Calendar c;
        int time;
        String greeting;
        c = Calendar.getInstance();
        time = c.get(Calendar.HOUR_OF_DAY);
        if (time >=0 && time <=11){
             greeting = "Good Morning!";
        }else if (time>11 && time <=14){
            greeting = "Good Noon!";
        }else if (time>14 && time<=16){
            greeting = "Good Afternoon!";
        }else if (time>16 && time<=19){
            greeting = "Good Evening!";
        }else
        {
            greeting = "Good Night!";
        }
        return greeting;
    }
}
