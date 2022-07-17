package com.ditu.chatapp.views;

import com.ditu.chatapp.dao.UserDAO;
import com.ditu.chatapp.dto.UserDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class UserScreen extends JFrame {
    private JTextField textField;
    private JPasswordField passwordField;

    public UserScreen() {
        getContentPane().setBackground(new Color(252, 216, 203, 147));
        setBounds(100, 100, 800, 451);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Login Page");
        lblNewLabel.setForeground(new Color(30, 26, 26));
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
        lblNewLabel.setBounds(304, 24, 158, 56);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Enter your UserID:");
        lblNewLabel_1.setForeground(new Color(30, 26, 26));
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(127, 118, 200, 56);
        getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Enter your Password:");
        lblNewLabel_1_1.setForeground(new Color(30, 26, 26));
        lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(127, 194, 200, 56);
        getContentPane().add(lblNewLabel_1_1);

        textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        textField.setBackground(new Color(243, 228, 207));
        textField.setBounds(403, 118, 293, 56);
        getContentPane().add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        passwordField.setBackground(new Color(243, 228, 207));
        passwordField.setBounds(403, 194, 293, 56);
        getContentPane().add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to put for the login button action
                try {
                    doLogin();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnLogin.setForeground(new Color(220, 214, 214));
        btnLogin.setBackground(new Color(52, 52, 52));
        btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 30));
        btnLogin.setBounds(227, 295, 111, 56);
        getContentPane().add(btnLogin);


        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to put for the register button action
                doRegister();
            }
        });
        btnRegister.setForeground(new Color(220, 214, 214));
        btnRegister.setBackground(new Color(52, 52, 52));
        btnRegister.setFont(new Font("Times New Roman", Font.BOLD, 30));
        btnRegister.setBounds(403, 295, 151, 56);
        getContentPane().add(btnRegister);


        setVisible(true);
    }

    UserDAO userDAO = new UserDAO();

    private void doRegister() {
        // Logic for the register button
        String userID = textField.getText();
        char []password = passwordField.getPassword();
        UserDTO userDTO = new UserDTO(userID ,password);

        try {
            int record = userDAO.register(userDTO);
            if (record > 0) {
                JOptionPane.showMessageDialog(this, "Registered Successfully..");
            }else {
                JOptionPane.showMessageDialog(this ,"Registeration Failed..");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void doLogin() throws IOException {
        // Logic for the login button
        String userID = textField.getText();
        char []password = passwordField.getPassword();
        UserDTO userDTO = new UserDTO(userID ,password);

        try {
            boolean res = userDAO.isLogin(userDTO);
            if (res) {
                setVisible(false);
                dispose();
                JOptionPane.showMessageDialog(this ,"Login Successful");
                ChatScreen chatScreen = new ChatScreen(userID);
                chatScreen.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(this ,"Login Failed, please try again");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserScreen window = new UserScreen();
    }
}
