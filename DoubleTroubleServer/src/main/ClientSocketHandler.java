package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class ClientSocketHandler extends Thread {
    private PrintStream printStream;
    private Socket clientSocket;
    private BufferedReader bufferedReader;
    private String clientName;
    private List<ClientSocketHandler> clients;

    public ClientSocketHandler(Socket socket, List<ClientSocketHandler> clients, int socketNumber) {
        this.clientSocket = socket;
        this.clientName = "Jogador" + socketNumber;
        this.clients = clients;
    }

    public void run() {
        try {
            printStream = new PrintStream(clientSocket.getOutputStream(), true);
            bufferedReader = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
            String connectionConfirm = "Iniciada a conexão do " + clientName;
            System.out.println(connectionConfirm);
            sendMessageToAll(connectionConfirm, true);
            String input;
            while((input = bufferedReader.readLine()) != null) {
                if("EXIT".equals(input)) {
                    System.out.println(input);
                    sendMessageToAll("Encerrada a conexão do " + clientName, true);
                    break;
                }
                System.out.println(clientName + ": " + input);
                sendMessageToAll(input, false);
            }
            bufferedReader.close();
            printStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    private void sendMessageToAll(String message, boolean serverMessage) {
        for(ClientSocketHandler client : clients) {
            client.printStream.println(serverMessage ?  "Servidor: " + message : clientName + ": " + message);
        }
    }
}
