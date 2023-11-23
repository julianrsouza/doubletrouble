package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class ClientSocketHandler extends Thread {
    private PrintStream printStream;
    private boolean currentPlayer;
    private Socket clientSocket;
    private BufferedReader bufferedReader;
    private String clientName;
    private List<ClientSocketHandler> clients;
    private String input;
    private Persona persona = new Persona(100, 20, 10);
    
    public ClientSocketHandler(Socket socket, List<ClientSocketHandler> clients, int socketNumber) {
        this.clientSocket = socket;
        this.clientName = "Jogador" + socketNumber;
        this.clients = clients;
        this.currentPlayer = false;
    }
    
    public String getClientName() {
        return clientName;
    }

    public Persona getPersona() {
        return persona;
    }
    
    public boolean isCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void run() {
        try {
            printStream = new PrintStream(clientSocket.getOutputStream(), true);
            bufferedReader = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
            String connectionConfirm = "Iniciada a conexão do " + clientName;
            System.out.println(connectionConfirm);
            sendMessageToAll(connectionConfirm, true);
            while((input = bufferedReader.readLine()) != null) {
                listenPlayer();   
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public String listenPlayer(){
        return input;
    }

    public void kill() {
        try {
            bufferedReader.close();
            printStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void blockPlayer(long timeInMilliSeconds) {
        try {
            sleep(timeInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToAll(String message, boolean serverMessage) {
        for(ClientSocketHandler client : clients) {
            client.printStream.println(serverMessage ?  message : clientName + ": " + message);
        }
    }
}
