package machines;

import java.util.HashMap;

public class Job extends Coordinates {
	private long id;
	private User user;
	public HashMap<Integer, Task> tasks;

	public Job(String ligne, Coordinates job) {
		String[] tab = ligne.split(",");
		this.id = Long.parseLong(tab[2]);
		this.user = new User(tab[4]);
		if (job == null)
			tasks = new HashMap<Integer, Task>();
		else
			tasks = ((Job) job).tasks;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addTask(Task t) {
		Task aux = tasks.get(t.getIndex());
		if (aux != null) {
			cpu -= aux.cpu;
			memory -= aux.memory;
			disk -= aux.disk;
		}
		cpu += t.cpu;
		memory += t.memory;
		disk += t.disk;
		tasks.put(t.getIndex(), t);
	}
}
