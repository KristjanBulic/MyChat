package com.kristjanbulic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{

    private Server server;
    private Socket socket;
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

    public void sendMessage(String str){
        writer.println(str);
    }

    @Override
    public void run() {

        while (true) {
            try {
                String mes = reader.readLine();
                if (mes != null) {
                    server.chatLog.add(mes);
                    server.echoer.interrupt();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
