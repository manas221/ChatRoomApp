package com.ditu.chatapp.views;

import com.ditu.chatapp.networking.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ChatScreen extends JFrame{
    private JTextField textField;
    private String useridString;    // to name the client window
    private JTextArea textArea;
    private Client client;

    public ChatScreen(String useridstring) throws IOException {
        setTitle(useridstring);
        getContentPane().setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(null);
        this.useridString=useridString;
        textArea = new JTextArea();
        textArea.setBackground(new Color(220, 214, 214));
        textArea.setBounds(10, 10, 766, 297);
        getContentPane().add(textArea);

        textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        textField.setBackground(new Color(220, 214, 214));
        textField.setBounds(10, 328, 545, 64);
        getContentPane().add(textField);
        textField.setColumns(10);

        client = new Client(textArea);
        this.useridString = useridstring;

        JButton sendMessageButton = new JButton("Send Message");
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // logic for sending message
                try {
                    sendMessage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        sendMessageButton.setBounds(570, 328, 206, 64);
        sendMessageButton.setForeground(new Color(255, 255, 255));
        sendMessageButton.setBackground(new Color(52, 52, 52));
        sendMessageButton.setFont(new Font("Times New Roman", Font.BOLD, 28));
        getContentPane().add(sendMessageButton);
        setBounds(100, 100, 800, 451);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void sendMessage() throws IOException {
        String message = textField.getText();
        client.sendMessage(this.useridString + ": " + message);
    }

//    public static void main(String[] args) throws IOException {
//        ChatScreen chatScreen = new ChatScreen("dummy");
//        chatScreen.setVisible(true);
//    }
}
