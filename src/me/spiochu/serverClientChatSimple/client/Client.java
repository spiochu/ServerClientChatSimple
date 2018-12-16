package me.spiochu.serverClientChatSimple.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(),4999);
            Thread thread = new Thread(new ClientThreat(socket,"first"));
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
