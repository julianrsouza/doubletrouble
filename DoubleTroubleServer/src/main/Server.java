package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	private static ServerSocket serverSocket;
	private static int connectionNumber = 1;
	private static List<ClientSocketHandler> clients = new ArrayList<>();

	private void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		while(true) {
			Socket client = serverSocket.accept();
			ClientSocketHandler clientSocketHandler = new ClientSocketHandler(client, clients, connectionNumber);
			clients.add(clientSocketHandler);
			clientSocketHandler.start();
			connectionNumber++;
		}
	}
	
	private void stop() throws IOException {  
		serverSocket.close();
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.start(8080);
			server.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
