package com.ditu.chatapp.networking;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerWorker extends Thread{
    private Socket clientSocket;
    private InputStream in;
    private OutputStream out;
    private Server server;

    public ServerWorker(Socket clientSocket ,Server server) throws IOException {
        this.clientSocket = clientSocket;
        this.server = server;
        in = clientSocket.getInputStream(); // for reading data
        out = clientSocket.getOutputStream();   // for writing data
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while(true) {
                line = bufferedReader.readLine();
                System.out.println("Message : " + line);
                for (ServerWorker serverWorker : server.workers) {
                    serverWorker.out.write((line + "\n").getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (in != null) in.close();
                if (out != null)    out.close();
                if (clientSocket != null)   clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
