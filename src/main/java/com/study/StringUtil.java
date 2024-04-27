package com.study;

public class StringUtil {
    public static String fromCodepoint(int codepoint) {
        return new String(new int[]{codepoint}, 0, 1);
    }
}
