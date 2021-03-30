package com.kristjanbulic;


import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            Client usr = new Client(new Socket("127.0.0.1", 5000));
            usr.start();
            usr.sendMessage("hello you to");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}