package Authenticator;

import java.util.Base64;

public class Authenticator{

    private static String username;
    private static String password;

    public static void setUsername(String username) {
        Authenticator.username = username;
    }

    public static void setPassword(String password) {
        Authenticator.password = password;
    }

    public static String getBasicAuthenticationHeader() {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
}
