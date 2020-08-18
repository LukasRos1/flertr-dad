
package Assignment5;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

/**
 * The GUI for assignment 5
 */
public class GUIChat
{
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private JFrame frame;				// The Main window
	private JTextField txt;				// Input for text to send
	private JTextField txt2;
	private JButton btnSend;			// Send text in txt
	private JButton btnConnect;
	private JButton btnDisconnect;
	private JTextArea lstMsg;			// The logger listbox
	private JTextArea lstConnected;     // Connected users
	private Client client;
	private String type;
	/**
	 * Constructor
	 */
	public GUIChat(String type)
	{
		this.type = type;
	}
	
	/**
	 * Starts the application
	 * @throws IOException 
	 */
	public void Start() throws IOException
	{

		frame = new JFrame();
		frame.setBounds(100, 100, 450,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multi Chat Server/Client");			// Change to "Multi Chat Server" on server part and vice versa 
		InitializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
			
		}

	
	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI()
	{
		txt = new JTextField();
		txt.setBounds(13,  13, 177, 23);
		frame.add(txt);
		btnSend = new JButton("Send");
		btnSend.setBounds(197, 13, 75, 23);
		frame.add(btnSend);
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					client.sendMessage(new Message(txt.getText(), false, false));
					txt.setText("");
			}
		});
		
		if (type.equals("Client")) {
		JLabel lab1 = new JLabel("Enter username");
		lab1.setBounds(290, 2, 120, 23);
		frame.add(lab1);
		
		txt2 = new JTextField();
		txt2.setBounds(290, 20, 100, 23);
		frame.add(txt2);
		
		btnConnect = new JButton("Connect");
		btnConnect.setBounds(290, 48, 120, 23);
		frame.add(btnConnect);
		
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						client.connect(new Message(txt2.getText(), true, false));
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		});
	
		btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setBounds(290, 73, 120, 23);
		frame.add(btnDisconnect);
		
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					client.disconnect();
			}
		});
		
		}
		
		
		lstMsg = new JTextArea();
		lstMsg.setEditable(false);
		JScrollPane pane = new JScrollPane(lstMsg);
		pane.setBounds(12, 51, 260, 199);
		pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.add(pane);
		
		lstConnected = new JTextArea();
		lstConnected.setEditable(false);
		JScrollPane connectedPane = new JScrollPane(lstConnected);
		connectedPane.setBounds(290, 100, 100, 150);
		connectedPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.add(connectedPane);
		
	}

	public void broadcastMessage(String message) {
		lstMsg.append(message + "\n");
	}

	public void setClient(Client client) {
//		System.out.println("received client object");
		this.client = client;
	}
	
	public void setLstConnected(ArrayList<String> users) {
		lstConnected.setText("");
		for (int i = 0; i < users.size(); i++) {
			lstConnected.append(users.get(i) + "\n");
		}
	}
	
}
