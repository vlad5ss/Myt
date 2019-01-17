package com.uladzislau.tylkovich.myapplication.utils;

public final class StringUtils {
    public static boolean foo(String line, String reg) {
        String[] strings = line.split("\\.");
        for (String word : strings) {
            if (word.matches(reg)) {
                return true;
            }
        }
        return false;
    }
}
