package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class ClientSocketHandler implements Runnable {
    private PrintStream printStream;
    private Socket clientSocket;
    private BufferedReader bufferedReader;
    private String input = "";  
	private Persona persona; 
    
    public ClientSocketHandler(Socket socket, int playerNumber) {
        this.clientSocket = socket;
        this.persona = new Persona(100, 20, 10, "Player" + playerNumber);
    }
    
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Persona getPersona() {
        return persona;
    }
    
    @Override
    public void run() {
        try {
            printStream = new PrintStream(clientSocket.getOutputStream(), true);
            bufferedReader = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
            String connectionConfirm = "Jogador conectado!";
            System.out.println(connectionConfirm);
            printStream.println(connectionConfirm);
            while(true) {
                input = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public void sendMessage(String message) {
        printStream.println(message);
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

}
