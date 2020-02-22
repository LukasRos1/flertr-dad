package Assignment2;

public class Writer extends Thread {
	// extend thread
	CharacterBuffer buffer;
	private String transferedString = "";
	private JGUIAssignment2 test;

	public Writer(CharacterBuffer buffer) {
		this.buffer = buffer;
	}

	public Writer() {

	}
	
	public void addGUIobj(JGUIAssignment2 test) {
		this.test = test;
	}

	public void write(String stringToBuffer) {
		this.transferedString = stringToBuffer;
		buffer.write(stringToBuffer);
	}

	public void run() {
		int i = 0;
		while (true) {
			while (transferedString.length() > i) {
			try {
				if (buffer.synchedWrite(transferedString.charAt(i)) == true) {
				i++;
				}
				Thread.sleep(1000);
			} catch (Exception E) {

			}
			}
		}

	}

}
