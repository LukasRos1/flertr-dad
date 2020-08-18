package Assignment5;

import java.io.IOException;

public class main {
	
	public static void main(String[] args) throws IOException {
		GUIChat gui = new GUIChat("Server");
		gui.Start();
		Server server = new Server(gui);
		server.start();
	}

}
