package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static ServerSocket serverSocket;

	private void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		Socket client = serverSocket.accept();
		ClientSocketHandler jogador1 = new ClientSocketHandler(client, 1);
		Socket client2 = serverSocket.accept();
		ClientSocketHandler jogador2 = new ClientSocketHandler(client2, 2);
		Thread socketThread1 = new Thread(jogador1);
		socketThread1.start();
		Thread socketThread2 = new Thread(jogador2);
		socketThread2.start();
		Fight fight = new Fight(jogador1, jogador2);
		fight.start();
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
