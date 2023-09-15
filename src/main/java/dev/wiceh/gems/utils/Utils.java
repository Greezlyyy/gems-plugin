package dev.wiceh.gems.utils;

import java.text.DecimalFormat;

public class Utils {

    public static boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        }catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String withLargeIntegers(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }
}
