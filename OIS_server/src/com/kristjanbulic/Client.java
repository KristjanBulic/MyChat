package com.kristjanbulic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{

    private final Server server;
    private final Socket socket;
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


    public void sendMessage(String str){
        writer.println(str);
    }

    public void updateChat(){
        for(String mes : server.chatLog){
            this.sendMessage(mes);
        }
    }

    public void disconnect(){
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
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String mes = reader.readLine();
                if (mes != null) {
                    if (mes.contains("<--EXIT-->")) {
                        this.disconnect();
                    } else {
                        server.chatLog.add(mes);
                        for (Client x: server.clients) {
                            x.sendMessage(mes);
                        }
                        if (server.chatLog.size() > 50){
                            server.clearChatLog();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
