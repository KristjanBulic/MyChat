package com.kristjanbulic;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Vector;


public class Server extends Thread{
    private ServerSocket socket;
    public Vector<String> chatLog = new Vector<>();
    public Vector<Client> clients = new Vector();


    public Server(ServerSocket socket) {
        this.socket = socket;

    }


    public void clearChatLog(){
        int i = 0;
        while (i < 20){
            this.chatLog.remove(0);
            i++;
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Client c = new Client(this, socket.accept());
                clients.add(c);
                c.start();
                c.updateChat();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

