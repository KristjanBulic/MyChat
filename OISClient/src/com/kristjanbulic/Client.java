package com.kristjanbulic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class Client extends Thread{
    private String username = "User1";
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Vector<String> chatLog = new Vector<>();
    private Boolean newChat = false;


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
                synchronized (this.newChat){
                    this.setNewChat(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String mes){
        String message = this.username + " ~>  " + mes;
        writer.println(message);
    }

    public boolean isNewChat() {
        return newChat;
    }

    public void setNewChat(boolean newChat) {
        this.newChat = newChat;
    }

    public String getLastChat(){
        return this.chatLog.lastElement();
    }

    public Boolean getNewChat() {
        return this.newChat;
    }
}
