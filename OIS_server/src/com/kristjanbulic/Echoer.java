package com.kristjanbulic;



public class Echoer extends Thread{
    //poskrbi da vsi uporabniki dobijo sporocilo
    private Server server;

    public Echoer(Server server) {
        this.server = server;
    }

    @Override
    public void run() {

        while(true){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    String mes = server.chatLog.lastElement();
                    for (Client x: server.clients){
                        x.sendMessage(mes);
                }
                    if (server.chatLog.size() > 50){
                        server.clearChatLog();
                    }
            }
        }
    }
}
