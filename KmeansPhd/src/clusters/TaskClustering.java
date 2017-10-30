package clusters;

import java.util.ArrayList;
import java.util.Iterator;

import machines.Coordinates;
import machines.Task;

public class TaskClustering 
{
	private int k;
	public TaskMean[] means;
	private ArrayList<Coordinates> tasks;
	public TaskClustering(int k,ArrayList<Coordinates> tasks) 
	{
		this.k=k;
		this.tasks = tasks;
		means= new TaskMean[k];
		for (int i = 0; i < means.length; i++) 
		{
			means[i]=new TaskMean(tasks);
			System.out.println(means[i].cpu+" "+means[i].disk+" "+means[i].memory);
		}
	}
	public void cluster()
	{
		boolean finie;
		do 
		{
			ArrayList<Coordinates> l = tasks;
			for (Iterator iterator = l.iterator(); iterator.hasNext();) 
			{
				Task t = (Task) iterator.next();
				double minDistance=t.distaceTo(means[0]);
				TaskMean minDistanceMean=means[0];
				for (int i = 1; i < means.length; i++) 
				{
					double dist=t.distaceTo(means[i]);
					if(minDistance>dist)
					{
						minDistanceMean = means[i];
						minDistance=dist;
					}
				}
				minDistanceMean.tasks.add(t);
			}
			System.out.println("-");
			finie=init();
		} 
		while (!finie);
	}
	private boolean init() 
	{
		boolean finie = true;
		for (int i = 0; i < means.length; i++) 
		{
			int n=means[i].tasks.size();
			if(n>0)
			{
				double cpu=0;
				double memory=0;
				double disk=0;
				for (int j = 0; j < n; j++) 
				{
					Coordinates t = means[i].tasks.get(j);
					cpu+=t.cpu;
					memory+=t.memory;
					disk+=t.disk;
				}
				cpu/=n;
				memory/=n;
				disk/=n;
				if(!(means[i].cpu==cpu&&means[i].memory==memory&&means[i].disk==disk))
				{
					means[i].cpu=cpu;
					means[i].memory=memory;
					means[i].disk=disk;
					finie=false;
				}
			}
		}
		if(!finie)
		{
			for (int i = 0; i < means.length; i++) 
			{
				means[i].clear();
			}
		}
		
		return finie;
	}
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
	
}
