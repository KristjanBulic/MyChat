package com.kristjanbulic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        Socket c = null;
        try {
            c = new Socket("127.0.0.1", 5000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
            PrintWriter writer = new PrintWriter(c.getOutputStream(), true);
            String mes = javax.swing.JOptionPane.showInputDialog("hwl");

                try{
                    writer.println(mes);
                    System.out.println(reader.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }


            //System.out.println(reader.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
