package Assignment5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class User extends Thread implements Serializable {

	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Server server;
	private volatile boolean running = false;
	private int messages = 0;
	private String username = "";
	
	public User(Server server, Socket socket) {
		this.server = server;
		
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			running = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		while (running) {
			try {
				Object object = ois.readObject();
				Message message = (Message) object;
				if (message.isConnection()) {
					setUsername(message.getMessage());
					server.broadcastUserConnected(this);
				} else if (message.isDisconnection()) {
					server.disconnectUser(this);
					running = false;
				} else {
					server.broadcastMessage(this, message.getMessage());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void setUsername(String username) {
		this.username = username;
		
	}
	
	public String getUsername() {
		return username;
	}

	public void sendMessage(String message) {
		try {
			oos.writeObject(message);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendUserList(ArrayList<User> clients) {
		ArrayList<String> test = new ArrayList<String>();
		for (int i = 0; i < clients.size();i++) {
			test.add(clients.get(i).getUsername());
		}
		
		try {
			oos.writeObject(test);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	
