package com.kristjanbulic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class Client extends Thread{
    private final String username;
    private final Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    public Vector<String> unRead = new Vector<>();



    public Client(Socket socket, String username) {
        this.username = username;
        this.socket = socket;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println(username); //send username to the server

    }

    @Override
    public void run() { //listens for messages from server
        while (!Thread.currentThread().isInterrupted()){
            try {
                String message = reader.readLine();
                if (message != null) {
                    unRead.add(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String mes){
        //sends message to the server
        String message = "\n  " + this.username + " ~>  " + mes;
        writer.println(message);
    }

    public void disconnect(){
        try {
            this.sendMessage("<--EXIT-->");
            this.interrupt(); //stops this thread
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
