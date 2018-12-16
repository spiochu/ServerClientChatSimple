package me.spiochu.serverClientChatSimple.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    public static BufferedReader in;
    public static PrintWriter out;
    public static String name;




    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 9001);
        name = "Spiochu";
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        Thread read = new Thread(new ReadFromServer());
        read.start();
        Thread write = new Thread((new WriteToServer()));
        write.start();

    }

    public static class WriteToServer extends Thread{
        private Scanner scanner;

        public WriteToServer(){
            this.scanner = new Scanner(System.in);
        }
        public void run(){
            do {
                System.out.print(name +":");
                String message = scanner.nextLine();
                out.println(message);
            }while (true);

        }

    }
    public static class ReadFromServer extends Thread {

        public ReadFromServer() {
        }

        public void run() {

            try {



            // Process all messages from server, according to the protocol.
            while (true) {
                String line = in.readLine();
                if (line.startsWith("SUBMITNAME")) {
                    out.println(name);
                } else if (line.startsWith("NAMEACCEPTED")) {
                    System.out.println("Name Accepted");
                } else if (line.startsWith("MESSAGE")) {

                    System.out.println(line.substring(8) + "\n");
                }
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}