package com.kristjanbulic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class Server extends Thread{
    private ServerSocket socket;
    private String name;
    public Echoer echoer = new Echoer(this);
    public Vector<String> chatLog = new Vector<>();
    public Vector<Client> clients = new Vector();


    public Server(ServerSocket socket, String name) {
        this.socket = socket;
        this.name = name;
    }


    @Override
    public void run() {
        echoer.start();
        while (true) {
            try {
                Client c = new Client(this, socket.accept());
                clients.add(c);
                c.start();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}

