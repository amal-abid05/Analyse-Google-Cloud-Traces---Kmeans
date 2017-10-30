package clusters;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import machines.Coordinates;
import machines.Task;

public class Clustering {
	private int k;
	public Mean[] means;
	private ArrayList<Coordinates> tasks;

	public Clustering(int k, ArrayList<Coordinates> tasks) {
		this.k = k;
		this.tasks = tasks;
		means = new TaskMean[k];
		for (int i = 0; i < means.length; i++) {
			means[i] = new TaskMean(tasks);
		}

	}

	public void cluster() {
		boolean finie;
		do {
			ArrayList<Coordinates> l = tasks;
			for (Iterator iterator = l.iterator(); iterator.hasNext();) {
				Coordinates t = (Coordinates) iterator.next();
				double minDistance = t.distaceTo(means[0]);
				Mean minDistanceMean = means[0];
				for (int i = 1; i < means.length; i++) {
					double dist = t.distaceTo(means[i]);
					if (minDistance > dist) {
						minDistanceMean = means[i];
						minDistance = dist;
					}
				}
				minDistanceMean.tasks.add(t);
			}
			finie = init();
		} while (!finie);
		tri();
	}

	private boolean init() {
		boolean finie = true;
		for (int i = 0; i < means.length; i++) {
			int n = means[i].tasks.size();
			if (n > 0) {
				double cpu = 0;
				double memory = 0;
				double disk = 0;
				for (int j = 0; j < n; j++) {
					Coordinates t = means[i].tasks.get(j);
					cpu += t.cpu;
					memory += t.memory;
					disk += t.disk;
				}
				cpu /= n;
				memory /= n;
				disk /= n;
				double r = 1E-6;
				Mean m = new Mean(cpu, memory);
				if (!(means[i].distaceTo(m) < r)) {
					means[i].cpu = m.cpu;
					means[i].memory = m.memory;
					means[i].disk = m.disk;
					finie = false;
				}
			}
		}
		if (!finie) {
			for (int i = 0; i < means.length; i++) {
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

	private void tri() {
		Mean orig = new Mean(0, 0);
		Mean cpu = new Mean(0, 0);
		if (k == 2) {
			double t[] = new double[2];
			for (int i = 0; i < t.length; i++) {
				t[i] = means[i].distaceTo(orig);
			}

			if (t[1] < t[0]) {
				Mean aux = means[0];
				means[0] = means[1];
				means[1] = aux;
			}
		}
		if (k == 3) {
			int min = 0;
			double mind = means[0].distaceTo(orig);
			for (int i = 1; i < 3; i++) {
				if (mind > means[i].distaceTo(orig)) {
					mind = means[i].distaceTo(orig);
					min = i;
				}
			}
			Mean aux = means[min];
			means[min] = means[0];
			means[0] = aux;
			double t[] = new double[2];
			for (int i = 1; i < 3; i++) {
				t[i - 1] = means[1].distaceTo(cpu);
			}
			if (t[1] < t[0]) {
				aux = means[1];
				means[1] = means[2];
				means[2] = aux;
			}
		}
		if (k == 4) {
			int min = 0;
			double mind = means[0].distaceTo(orig);
			for (int i = 1; i < 4; i++) {
				if (mind > means[i].distaceTo(orig)) {
					mind = means[i].distaceTo(orig);
					min = i;
				}
			}
			Mean aux = means[min];
			means[min] = means[0];
			means[0] = aux;
			min = 1;
			mind = means[1].distaceTo(orig);
			for (int i = 2; i < 4; i++) {
				if (mind > means[i].distaceTo(orig)) {
					mind = means[i].distaceTo(orig);
					min = i;
				}
			}
			aux = means[min];
			means[min] = means[1];
			means[1] = aux;
			double t[] = new double[2];
			for (int i = 2; i < 4; i++) {
				t[i - 2] = means[i].distaceTo(cpu);
			}
			if (t[1] < t[0]) {
				aux = means[2];
				means[2] = means[3];
				means[3] = aux;
			}
		}
	}
}
