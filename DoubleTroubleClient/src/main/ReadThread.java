package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread {
    private Socket socket;

    public ReadThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStreamReader InputReader = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(InputReader);
            String serverMessage;
            while(!socket.isClosed()) {
                serverMessage = reader.readLine();
                if("CLEAR".equals(serverMessage)){
                    System.out.flush();
                } else {
                    System.out.println(serverMessage);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
