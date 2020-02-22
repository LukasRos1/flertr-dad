package Assignment2;

public class Reader extends Thread {
	
	CharacterBuffer buffer;
	private JGUIAssignment2 test;
	public Reader (CharacterBuffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		System.out.println("Reader tråd startas");
		
//		char c = buffer.synchedRead();
//		String test11 = ""+c;
//		test.setlistR(test11);
		
	}

	public void addGUIobj(JGUIAssignment2 test) {
		this.test = test;
	}
	
	
//	Send GUI class to reader ?
}
