package com.obsidiskrivemaskine;

/**
 * Created by esben on 11/04/16.
 */
public class Utils {
    public static int ColorFromRGB(int R, int G, int B){
        return (R * 65536) + (G * 256) + B;
    }
    public static double roundDouble(double d){ return (double)(int)d;}
}
