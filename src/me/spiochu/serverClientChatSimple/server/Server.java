package me.spiochu.serverClientChatSimple.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(4999);
            List<Thread> threadList = new ArrayList<>();
            Socket socket = serverSocket.accept();
            Thread thread = new Thread(new ConnectedClient(socket));
            thread.start();
            threadList.add(thread);

            socket = serverSocket.accept();
            thread = new Thread(new ConnectedClient(socket));
            thread.start();
            threadList.add(thread);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
