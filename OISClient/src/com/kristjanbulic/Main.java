package com.kristjanbulic;


import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            User usr = new User("tester", new Socket("127.0.0.1", 5000));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}