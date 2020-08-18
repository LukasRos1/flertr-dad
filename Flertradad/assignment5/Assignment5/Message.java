package Assignment5;

import java.io.Serializable;

public class Message implements Serializable {
	
	private String message;
	private boolean connection;
	private boolean disconnection;
	
	public Message(String message, boolean c, boolean d) {
		this.setMessage(message);
		this.setConnection(c);
		this.setDisconnection(d);
	}

	public boolean isConnection() {
		return connection;
	}

	public void setConnection(boolean connection) {
		this.connection = connection;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isDisconnection() {
		return disconnection;
	}

	public void setDisconnection(boolean disconnection) {
		this.disconnection = disconnection;
	}

}
