package clusters;

import java.util.ArrayList;

import machines.Coordinates;
import machines.Task;

public class Mean extends Coordinates {
	public ArrayList<Coordinates> tasks;

	public Mean(ArrayList<Coordinates> tasks) {
		int r1 = (int) (Math.random() * tasks.size());
		int r2 = (int) (Math.random() * tasks.size());
		int r3 = (int) (Math.random() * tasks.size());
		cpu = (tasks.get(r1).cpu + tasks.get(r2).cpu + tasks.get(r3).cpu) / 3;
		memory = (tasks.get(r1).memory + tasks.get(r2).memory + tasks.get(r3).memory) / 3;
		this.tasks = new ArrayList<Coordinates>();
	}

	public Mean(double cpu, double memory) {
		this.cpu = cpu;
		this.memory = memory;
		this.tasks = new ArrayList<Coordinates>();
	}

	public void clear() {
		tasks.clear();
	}
}
