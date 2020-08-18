package Assignment5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Thread implements Serializable {
	
	private Socket clientSocket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private GUIChat guichat;
	private String username = "";
	private volatile boolean running = false;
	
	public Client(GUIChat gui) throws IOException {
		this.guichat = gui;
	}
	
	public void connect(Message message) throws IOException {
		running = true;
		this.username = message.getMessage();
		clientSocket = new Socket("localhost", 8090);
		oos = new ObjectOutputStream(clientSocket.getOutputStream());
		ois = new ObjectInputStream(clientSocket.getInputStream());
		this.start();
		sendMessage(new Message(message.getMessage(), true, false));
	}

	public void disconnect() {
		try {

			
			System.out.println("Writing disconnect");
			running = false;
				oos.writeObject(new Message(username, false, true));
				oos.flush();
				ois.close();
				oos.close();
				clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(Message message) {
		try {
			oos.writeObject(message);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void receiveMessages() {
		try {
			Object obj = ois.readObject();
			if(obj instanceof String) {
				guichat.broadcastMessage(obj.toString());
			}
			if (obj instanceof ArrayList<?>) { // Om obj är en ArrayList så är det Users Connected som skickas
				ArrayList<String> users = (ArrayList<String>) obj;
				guichat.setLstConnected(users);
			} 
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(running) {
			receiveMessages();
		}
	}

	public static void main(String[] args) throws IOException {
		GUIChat gui = new GUIChat("Client");
		gui.Start();
		Client client = new Client(gui);
		gui.setClient(client);
	}
	
}
