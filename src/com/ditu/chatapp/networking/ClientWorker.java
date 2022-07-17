package com.ditu.chatapp.networking;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClientWorker extends Thread{
    private InputStream in;
    private JTextArea textArea;

    public ClientWorker(InputStream in, JTextArea textArea) {
        this.in = in;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while(true) {
                line = bufferedReader.readLine();
                textArea.setText(textArea.getText()+line+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
