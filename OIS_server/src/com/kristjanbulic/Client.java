package com.kristjanbulic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{

    private final Server server;
    private final Socket socket;
    private String name;
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
    }


    public String getClientName() {
        return name;
    }

    public void sendMessage(String str){
        writer.println(str);
    }

    public void updateChat(){
        for(String mes : server.chatLog){
            this.sendMessage(mes);
        }
    }

    public void disconnect(){
        for (Client c : server.clients){
            if (c.getClientName().equals(this.name)){
                server.chatLog.remove(c);
            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String mes = reader.readLine();
                if (mes != null) {
                    if (mes.equals("<--EXIT-->")) {
                        this.disconnect();
                    } else {
                        server.chatLog.add(mes);
                        server.getEchoer().interrupt();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
