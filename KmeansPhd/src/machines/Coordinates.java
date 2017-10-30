package machines;



public class Coordinates
{
	public double cpu;
	public double memory;
	public double disk;
	public double distaceTo(Coordinates mean) 
	{	
		return Math.sqrt((mean.cpu-cpu)*(mean.cpu-cpu)+(mean.memory-memory)*(mean.memory-memory));
	}
	
}
