package com.ditu.chatapp.dao;

import static com.ditu.chatapp.dao.ConfigReader.getValue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface CommonDAO {
    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        // Load Driver
        Class.forName(getValue("DRIVER"));

        //Build connection
        final String CON_STRING = getValue("CON_STRING");
        final String username = getValue("USER_ID");
        final String password = getValue("USER_PWD");
        Connection con = DriverManager.getConnection(CON_STRING ,username ,password);
        if (con != null) {
            System.out.println("---Connection Created---");
        }

        return con;
    }


}
