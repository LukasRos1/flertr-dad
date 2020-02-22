package Assignment2;



public class Assignment2Test {

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		CharacterBuffer buffer = new CharacterBuffer();
		Writer writer = new Writer(buffer);
		Reader reader = new Reader(buffer);
		JGUIAssignment2 test = new JGUIAssignment2(writer, reader);
		
		reader.addGUIobj(test);
		writer.addGUIobj(test);
		
		test.Start();
		writer.start();
		reader.start();

	}
}
