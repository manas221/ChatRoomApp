/*
    UserDAO : User Data Access Object
    Contains two methods ,isLogin() and register

    isLogin() returns True if credentials are in database

    register() adds the credentials to the database
 */
package com.ditu.chatapp.dao;

import com.ditu.chatapp.dto.UserDTO;

import java.sql.*;

public class UserDAO {
    public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "select user_id from users where user_id=? and pwd=?";

        // Creating connection
        try {
            conn = CommonDAO.createConnection();
            pstmt = conn.prepareStatement(query);

            String userID = userDTO.getUserID();
            String password = new String(userDTO.getPassword());
            pstmt.setString(1, userID);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();
            return rs.next();
        }finally {
            if (pstmt != null)  pstmt.close();
            if (rs != null) rs.close();
            if (conn != null)   conn.close();
        }
    }

    public int register(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;

        // Create Connection
        try {
            conn = CommonDAO.createConnection();
            stmt = conn.createStatement();

            String userID = userDTO.getUserID();
            String password = new String(userDTO.getPassword());

            String query = "insert into users(user_id ,pwd) values('"+userID+"','"+password+"')";
            return stmt.executeUpdate(query);
        }finally {
            if (stmt != null)   stmt.close();
            if (conn != null)   conn.close();
        }
    }
}
