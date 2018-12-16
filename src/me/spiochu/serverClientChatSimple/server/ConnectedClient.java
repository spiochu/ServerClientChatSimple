package me.spiochu.serverClientChatSimple.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectedClient implements Runnable {
    private Socket socket;

    public ConnectedClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());


            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            do {

                String string = bufferedReader.readLine();
                System.out.println("From Client : " + string);
                printWriter.println(string);
                printWriter.flush();
            } while (socket.isConnected());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
