package Assignment5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server extends Thread {

	private ArrayList<User> clients = new ArrayList<User>();
	private ServerSocket serverSocket;
	private volatile boolean running;
	private ExecutorService executor = Executors.newFixedThreadPool(5);
	private GUIChat gui;

	public Server(GUIChat gui) throws IOException {
		this.gui = gui;
		this.serverSocket = new ServerSocket(8090);
	}


	public synchronized void broadcastMessage(User test, String string) {
		for (User user : clients) {
			user.sendMessage(test.getUsername() + ": " + string);
		}
		gui.broadcastMessage(test.getUsername() + ": " + string);
	}
	
	public synchronized void broadcastUserConnected(User user) {
		if (user == null) {
			
		}
		for (User users : clients) {
			users.sendMessage("User " + user.getUsername() + " connected");
			users.sendUserList(clients);
		}
		gui.broadcastMessage("User " + user.getUsername() + " connected");
	}
	
	public synchronized void broadcastUserDisconnected(User user) {
		for (User users : clients) {
			users.sendMessage("User " + user.getUsername() + " disconnected");
			users.sendUserList(clients);
		}
		gui.broadcastMessage("User " + user.getUsername() + " disconnected");
	}

	public void run() {
		Socket socket = null;
		User user;
		running = true;
		gui.broadcastMessage("Server started...");
		while (running) {
			try {
				socket = serverSocket.accept();
				
				synchronized (this) {
					user = new User(this, socket);
					clients.add(user);
					executor.execute(user);
					gui.broadcastMessage("User connected");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}


	public void disconnectUser(User user) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).equals(user)) {
				clients.remove(i);
			}
		}
		broadcastUserDisconnected(user);
	}
}
