package com.ditu.chatapp.dao;

import java.util.ResourceBundle;;


public class ConfigReader {
    private static ResourceBundle rb = ResourceBundle.getBundle("config");

    public static String getValue(String key) {
        return rb.getString(key);
    }
}
