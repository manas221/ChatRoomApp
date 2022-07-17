package com.ditu.chatapp.networking;

import com.ditu.chatapp.dao.ConfigReader;
import com.mysql.cj.jdbc.ConnectionGroup;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    Socket socket;
    OutputStream out;
    InputStream in;
    ClientWorker worker;
    JTextArea textArea;

    public Client(JTextArea textArea) throws IOException {
        int PORT = Integer.parseInt(ConfigReader.getValue("PORT_NO"));

        // we need server machine IP and PORT
        socket = new Socket(ConfigReader.getValue("SERVER_IP") ,PORT);
        System.out.println("Client arrived...");
        out = socket.getOutputStream(); // send data
        in = socket.getInputStream();   // recieve data
        this.textArea = textArea;
        readMessage();
    }

    public void readMessage() {
        // client worker will read message from the network
        worker = new ClientWorker(in ,textArea);
        worker.start();
    }

    public void sendMessage(String message) throws IOException {
        // write message on network
        out.write((message + "\n").getBytes());
    }

}
