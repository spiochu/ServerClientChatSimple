package me.spiochu.serverClientChatSimple.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientThreat implements Runnable {
    Socket socket;
    String name;

    public ClientThreat(Socket socket, String name) {
        this.name = name;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.flush();
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            do {
                //Write data to server


                printWriter.println("Client connected : " + name);
                printWriter.flush();

                System.out.println("Type your message:");
                printWriter.println(scanner.nextLine());
                printWriter.flush();
                //Read data from sever

                String string = bufferedReader.readLine();
                System.out.println("From server :" + string);

            } while (socket.isConnected());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
