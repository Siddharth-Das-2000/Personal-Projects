// // package com.dashboard.util;

// // import java.util.Map;

// // public class XmlUtil {

// //     private XmlUtil() {
// //     }

// // //     public static boolean isErrorPayload(String payload) {

// // //         String lower = payload.toLowerCase();

// // //         return lower.contains("error")
// // //                 || lower.contains("exception")
// // //                 || lower.contains("failed")
// // //                 || lower.contains("failure");
// // //     }
// // // }

// // public static boolean isErrorPayload(Map<String, String> headers, String payload) {

// //     // 1. Explicit CPI error flag (MOST IMPORTANT)
// //     if ("true".equalsIgnoreCase(headers.get("X-CPI-ERROR"))) {
// //         return true;
// //     }

// //     // 2. Check response code (still useful but secondary)
// //     String codeStr = headers.get("CamelHttpResponseCode");
// //     if (codeStr != null) {
// //         try {
// //             int code = Integer.parseInt(codeStr);
// //             if (code >= 400) return true;
// //         } catch (Exception ignored) {}
// //     }

// //     // 3. Payload-based fallback (your case)
// //     if (payload != null && (
// //             payload.toLowerCase().contains("failed") ||
// //             payload.toLowerCase().contains("failure")
// //     )) {
// //         return true;
// //     }

// //     return false;
// // }
// // }


// package com.dashboard.util;

// import java.util.Map;

// public class XmlUtil {

//     private XmlUtil() {}

//     public static boolean isError(Map<String, String> headers, String payload) {

//         // =========================
//         // 1. CPI TRANSPORT LAYER
//         // =========================
//         if (isHttpError(headers)) {
//             return true;
//         }

//         // =========================
//         // 2. CPI EXPLICIT ERROR FLAGS (BEST PRACTICE)
//         // =========================
//         if (isExplicitCpiError(headers)) {
//             return true;
//         }

//         // =========================
//         // 3. PAYLOAD-BASED FALLBACK (handled exceptions)
//         // =========================
//         if (payload != null && isErrorPayload(payload)) {
//             return true;
//         }

//         return false;
//     }

//     private static boolean isHttpError(Map<String, String> headers) {
//         String codeStr = headers.get("CamelHttpResponseCode");

//         if (codeStr != null) {
//             try {
//                 int code = Integer.parseInt(codeStr);
//                 return code >= 400;
//             } catch (Exception ignored) {}
//         }
//         return false;
//     }

//     private static boolean isExplicitCpiError(Map<String, String> headers) {

//         // Recommended CPI design: set this in Exception Subprocess
//         if ("true".equalsIgnoreCase(headers.get("X-CPI-ERROR"))) {
//             return true;
//         }

//         // Alternative CPI indicators
//         String responseText = headers.get("CamelHttpResponseText");
//         if (responseText != null &&
//                 !responseText.equalsIgnoreCase("OK") &&
//                 !responseText.equalsIgnoreCase("SUCCESS")) {
//             return true;
//         }

//         return false;
//     }

//     public static boolean isErrorPayload(String payload) {

//         String lower = payload.toLowerCase();

//         // =========================
//         // 1. STRUCTURED FAILURE (IMPORTANT)
//         // =========================
//         if (lower.contains("<status>failed</status>") ||
//             lower.contains("\"status\":\"failed\"") ||
//             lower.contains("<success>false</success>") ||
//             lower.contains("\"success\":false")) {
//             return true;
//         }

//         // =========================
//         // 2. SOAP FAULT (VERY COMMON IN CPI)
//         // =========================
//         if (lower.contains("soap-env:fault") ||
//             lower.contains("<fault") ||
//             lower.contains(":faultcode")) {
//             return true;
//         }

//         // =========================
//         // 3. CPI EXCEPTION PATTERNS
//         // =========================
//         if (lower.contains("exceptionmessage") ||
//             lower.contains("errortext") ||
//             lower.contains("applicationexception")) {
//             return true;
//         }

//         return false;
//     }
// }


package com.dashboard.util;

import java.util.Map;

public class XmlUtil {

    private XmlUtil() {
    }

    public static boolean isErrorPayload(Map<String, String> headers) {
        return headers.containsKey("X-CPI-ERROR");
    }
}