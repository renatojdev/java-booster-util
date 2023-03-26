package io.rjdev.booster.util.string;

public class StringUtil {

    public static String removeCharFromString(char c, String str) {
        if(Character.valueOf(c).equals(null) || str == null)
            throw new IllegalArgumentException("No arguments provided");

        return str.replace(String.valueOf(c), "");
    }



}
