package com.kristjanbulic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Thread{
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private ArrayList<String> chatLog = new ArrayList<String>();


    public Client(Socket socket) {
        this.socket = socket;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run() {
        while (true){
            try {
                String message = reader.readLine();
                System.out.println(message);
                chatLog.add(message);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String mes){
        writer.println(mes);
    }
}
