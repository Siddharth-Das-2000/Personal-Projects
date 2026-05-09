package com.dashboard.util;

public class XmlUtil {

    private XmlUtil() {
    }

    public static boolean isErrorPayload(String payload) {

        String lower = payload.toLowerCase();

        return lower.contains("error")
                || lower.contains("exception")
                || lower.contains("failed")
                || lower.contains("failure");
    }
}