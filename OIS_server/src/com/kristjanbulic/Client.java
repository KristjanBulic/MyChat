package com.kristjanbulic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{

    private final Server server;
    private final Socket socket;
    private String username;
    private BufferedReader reader;
    private PrintWriter writer;


    public Client(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.username = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PrintWriter getWriter(){
        return this.writer;
    }

    public String getUsername(){
        return this.username;
    }



    public void updateChat(){
        for(String mes : server.chatLog){
            writer.println(mes);
        }
    }

    public void disconnect(){
        server.sendMessage("\tSERVER ~>  " + this.username + " left server");
        server.clients.remove(this);
        this.interrupt();
        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) { //
            try {
                String mes = reader.readLine();
                if (mes != null) {
                    if (mes.contains("<--EXIT-->")) {
                        this.disconnect();
                    } else {
                        server.chatLog.add(mes);
                        server.sendMessage(mes);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
