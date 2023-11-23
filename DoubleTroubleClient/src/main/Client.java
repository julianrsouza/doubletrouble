package main;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            Scanner scanner = new Scanner(System.in);
           
            while (!socket.isClosed()) {
                ReadThread readThread = new ReadThread(socket);
                readThread.start();
                PrintStream output = new PrintStream(socket.getOutputStream());
                String outputString = scanner.nextLine();
                output.println(outputString);
                if("EXIT".equals(outputString)) {
                    socket.close();
                    scanner.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
