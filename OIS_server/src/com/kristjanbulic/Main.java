package com.kristjanbulic;

import java.io.IOException;
import java.net.ServerSocket;


public class Main  {
    public static void main(String[] args){
        try {
            int port =5000;
            ServerSocket s = new ServerSocket(5000);
            Server ser = new Server(s, "mimi");
            ser.start();
            //(new Thread(ser)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
