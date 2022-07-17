package com.ditu.chatapp.networking;

import com.ditu.chatapp.dao.ConfigReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    ServerSocket serverSocket;
    ArrayList<ServerWorker> workers = new ArrayList<ServerWorker>();

    public Server() throws IOException {
        int PORT = Integer.parseInt(ConfigReader.getValue("PORT_NO"));
        serverSocket = new ServerSocket(PORT);
        while (true) {
            System.out.println("Waiting for client..");
            handleClient();
        }
    }

    public void handleClient() throws IOException {
        Socket socket = serverSocket.accept();
        System.out.println("Client Connected with server");

        ServerWorker serverWorker = new ServerWorker(socket ,this);
        workers.add(serverWorker);
        serverWorker.start();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }
}
