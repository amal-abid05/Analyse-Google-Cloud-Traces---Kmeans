package machines;

import clusters.TaskMean;
import test.Test;

public class Task extends Coordinates
{
	private Job job;
	private int index;
	private User user;
	private int priority;
	private int classe;

	public Task(String ligne) 
	{
		String[] tab = ligne.split(",",-1);
		try {
			cpu= Double.parseDouble(tab[9]);
		} catch (Exception e) {
			cpu= 0;
		}
		try {
			classe= Integer.parseInt(tab[7]);
		} catch (Exception e) {
			classe= -5;
		}
		
		try {
			memory= Double.parseDouble(tab[10]);
		} catch (Exception e) {
			memory= 0;
		}
		try {
			disk= Double.parseDouble(tab[11]);
		} catch (Exception e) {
			disk= 0;
		}
		try {
			priority= Integer.parseInt(tab[8]);
		} catch (Exception e) {
			priority= 0;
		}
		index= Integer.parseInt(tab[3]);
		user=new User(tab[6]);
		job=(Job)Test.jobs.get(Long.parseLong(tab[2]));
		job.addTask(this);
	}
	
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getClasse() {
		return classe;
	}
	public void setClasse(int classe) {
		this.classe = classe;
	}
	
	
}
