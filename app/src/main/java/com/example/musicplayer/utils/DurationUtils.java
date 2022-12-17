package com.example.musicplayer.utils;

public class DurationUtils {

    public static String convertToTimerMode(int duration){
        String timer = "";

        int minute = (duration % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = ((duration % (1000 * 60 * 60)) % (1000 * 60)) / (1000);

        if (minute < 10)
            timer += "0";
        timer += minute + ":";
        if (seconds < 10)
            timer += "0";
        timer += seconds;


        return timer;
    }
}
