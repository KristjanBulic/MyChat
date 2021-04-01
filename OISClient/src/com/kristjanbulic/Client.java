package com.kristjanbulic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class Client extends Thread{
    private String username;
    private String password;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    public Vector<String> unRead = new Vector<>();



    public Client(Socket socket, String username, String password, boolean newuser) {
        this.username = username;
        this.password =password;
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
                if (message != null) {
                    unRead.add(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String mes){
        String message = "\n  " + this.username + " ~>  " + mes;
        writer.println(message);
    }



    public void disconnect(){
        try {
            this.sendMessage("<--EXIT-->");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
