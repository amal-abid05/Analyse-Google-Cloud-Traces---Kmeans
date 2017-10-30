package clusters;

import java.util.ArrayList;

import machines.Coordinates;
import machines.Task;

public class TaskMean extends Mean
{
	
	public TaskMean(ArrayList<Coordinates> tasks) 
	{
		super(tasks);
	}
	public TaskMean(double cpu , double memory ) 
	{
		super(cpu,memory);
	}
}
