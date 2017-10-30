package machines;

public class Machine 
{
	private long id;
	private Platform platform;
	private boolean active;
	private double cpu;
	private double memory;
	public Machine(String ligne) 
	{
		String[] tab = ligne.split(",");
	}
	
}
