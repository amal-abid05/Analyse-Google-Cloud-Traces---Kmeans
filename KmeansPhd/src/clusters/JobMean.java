package clusters;

import java.util.ArrayList;

import machines.Coordinates;
import machines.Job;
import machines.Task;

public class JobMean extends Mean
{
	public ArrayList<Job> jobs;
	public ArrayList<Task> tasks;
	public JobMean(ArrayList<Coordinates> tasks) 
	{
		super(tasks);
		this.tasks=new ArrayList<Task>();
	}
	public void clear() 
	
	{
		tasks.clear();
	}
}
