package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import clusters.Clustering;
import clusters.Mean;
import clusters.TaskClustering;
import machines.Coordinates;
import machines.Job;
import machines.Task;

public class Test 
{
	public final static String PATH_TO_STORAGE="C:/Users/amal/workspace/KmeansPhd/storage/";
	public final static String PATH_TO_RESULT="C:/Users/amal/workspace/KmeansPhd//result/";
	public static HashMap<Long, Coordinates> jobs;
	public static ArrayList<Coordinates> tasks;
	public static String [][] names;
	public static void main(String[] args) throws FileNotFoundException 
	{
		names = new String[][]{{"irregular","regular"},{"irregular","regular-cpu","regular-ram"}
		,{"irregular-heavy","irregular-light","regular-heavy","regular-light"}};
		jobs= new HashMap<Long, Coordinates> ();
		tasks = new ArrayList<Coordinates>();
		FileInputStream in = new FileInputStream(PATH_TO_STORAGE+"job_events/part-00000-of-00500.csv");
		Scanner sc = new Scanner(in);
		String s;
		while (sc.hasNext()) 
		{
			s=sc.nextLine();
			Job j = new Job(s,jobs.get(s.split(",")[2]));
			jobs.put(j.getId(), j);
		}
		FileInputStream intt = new FileInputStream(PATH_TO_STORAGE+"task_events/part-00000-of-00500.csv");
		sc = new Scanner(intt);
		while (sc.hasNext()) 
		{
			s=sc.nextLine();
			Task t = new Task(s);
			if(t.cpu!=0||t.memory!=0||t.disk!=0)
				tasks.add(t);
		}
		for (int k = 2; k < 5; k++) 
		{
			Clustering t =  new Clustering(k, tasks);
			t.cluster();
			for (int i = 0; i < k; i++) 
			{
				System.out.println(names[k-2][i]+" "+t.means[i].cpu+" "+t.means[i].memory);
				PrintStream out = new PrintStream(new FileOutputStream(PATH_TO_RESULT+"/"+k+"/"+names[k-2][i]+".csv"));
				for (int j = 0; j < t.means[i].tasks.size(); j++) 
				{
					out.println(t.means[i].tasks.get(j).cpu+","+t.means[i].tasks.get(j).memory);
				}
				out.close();
			}
		}
		
	}
}