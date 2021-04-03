package com.kristjanbulic;

import javax.swing.*;
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

    public void sendMessage(String str){
        for (Client x: this.clients) {
            x.getWriter().println(str);
        }
        if (this.chatLog.size() > 50){
            this.clearChatLog();
        }
    }

    public void stopServer(){
        try {
            socket.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to stop server");
        }
        this.interrupt();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Client c = new Client(this, socket.accept());
                clients.add(c);
                c.updateChat();
                sendMessage("\tSERVER ~>  " + c.getUsername() + " joined server");
                c.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

