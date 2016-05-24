package main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

	private BlockingQueue<Task> bg;
	private AtomicInteger waitingTime;
	private int queue;

	public Server(int i) {
		queue = i;
		bg = new LinkedBlockingQueue<Task>();
		waitingTime = new AtomicInteger(0);
	}

	public void run() {
		while (true) {
			try {
				Task t = bg.take();
				System.out.println("Process task: " + t.toString()
						+ " at queue " + queue);
				Thread.sleep(t.getProcessTime() * 1000);
				Simulator.getFrame().displayData(getTasks(), queue);
				waitingTime.addAndGet((-1) * t.getProcessTime());
				if(totalTime() == 4){
					Simulator.openNew();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isEmpty() {
		return bg.isEmpty();
	}

	public void addTask(Task t) {
		bg.add(t);
		waitingTime.addAndGet(t.getProcessTime());
		System.out
		.println("                                         "
				+ " Task: "
				+ t.getArrivalTime()
				+ " added to "
				+ queue
				+ " " + bg.size());
		if(totalTime() == 4){
			Simulator.openNew();
		}
	}

	public Task[] getTasks() {
		Task[] tasks = new Task[bg.size()];
		bg.toArray(tasks);
		return tasks;
	}

	public int totalTime() {
		return bg.size();
	}
}
