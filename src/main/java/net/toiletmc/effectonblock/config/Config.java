package net.toiletmc.effectonblock.config;


public class Config {
    public static double x1;

    public static double y1;

    public static double z1;

    public static double x2;

    public static double y2;

    public static double z2;

    public static int exp_points = 1;
    public static int playercount = 0;

    public static void init() {
        if (x1 > x2) {
            double x3 = x1;
            x1 = x2;
            x2 = x3;
        }
        if (y1 > y2) {
            double y3 = y1;
            y1 = y2;
            y2 = y3;
        }
        if (z1 > z2) {
            double z3 = z1;
            z1 = z2;
            z2 = z3;
        }
    }
}