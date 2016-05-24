package main;

import java.util.Arrays;
import java.util.Scanner;

public class Simulator implements Runnable {
	public static int SCHEDULES;
	private int finishTime;
	private int maxProcessingTime;
	private int minProcessingtime;
	private int lastMin = 0;
	private Scheduler[] s;
	private static int[] totalTime;
	private static int[] totalTasks;
	private static int opens = 1;

	private static SimulatorFrame frame;

	public Simulator(int schuldes, int ft, int minpt, int maxpt) {
		SCHEDULES = schuldes;
		finishTime = ft;
		maxProcessingTime = maxpt;
		minProcessingtime = minpt;	
		
		s = new Scheduler[SCHEDULES];
		totalTime = new int[SCHEDULES];
		totalTasks = new int[SCHEDULES];
		
		Arrays.fill(totalTime, 0);
		Arrays.fill(totalTasks, 0);
		setFrame(new SimulatorFrame());
		for (int i = 0; i < SCHEDULES; i++) {
			s[i] = new Scheduler(i);
		}
	}
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter max number of servers: ");
		int schuldes = keyboard.nextInt();
		System.out.print("Enter finish time: ");
		int ft = keyboard.nextInt();
		System.out.print("Enter minPtime: ");
		int minpt = keyboard.nextInt();
		System.out.print("Enter maxPtime: ");
		int maxpt = keyboard.nextInt();
		Simulator sim = new Simulator(schuldes, ft, minpt, maxpt);
		Thread th = new Thread(sim);
		th.start();
	}

	public int getMin() {
		int min = 0;
		if (s[lastMin].getNewServer().isEmpty()) {
			int ret = lastMin;
			lastMin++;
			if (lastMin == getOpens()) {
				lastMin = 0;
			}
			return ret;
		}
		for (int i = 0; i < getOpens() - 1; i++) {
			if (s[SCHEDULES - i - 1].getNewServer().isEmpty()) {
				return SCHEDULES - i - 1;
			} else if (s[i].getNewServer().totalTime() > s[i + 1]
					.getNewServer().totalTime()) {
				min = i + 1;
			}
		}
		return min;
	}

	public boolean notEmpty() {
		for (int i = 0; i < SCHEDULES; i++) {
			if (!s[i].getNewServer().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public void run() {
		int currentTime = 0;
		int min;
		while (currentTime < finishTime) {
			getFrame().refreshTime(currentTime);
			int processTime = (int) ((Math.random()
					* (maxProcessingTime - minProcessingtime) + minProcessingtime));
			Task t = new Task(currentTime, processTime);
			min = getMin();
			s[min].dispatchTaskOnServer(t);
			totalTime[min] += processTime;
			totalTasks[min]++;
			getFrame().displayData(s[min].getNewServer().getTasks(), min);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currentTime++;
		}
		while (notEmpty()) {

		}
		for (int i = 0; i < SCHEDULES; i++) {
			float av = (float) totalTime[i] / totalTasks[i];
			System.out.println("At queue " + i + " total tasks: "
					+ totalTasks[i] + ", total time: " + totalTime[i]
					+ ", average time: " + av);
		}
		System.exit(0);
		// while (!s1.getNewServer().isEmpty() || !s2.getNewServer().isEmpty())
		// {
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// frame.displayData(s1.getNewServer().getTasks(), 1);
		// frame.displayData(s2.getNewServer().getTasks(), 2);
		// }
	}

	public static SimulatorFrame getFrame() {
		return frame;
	}

	public static void setFrame(SimulatorFrame frame1) {
		frame = frame1;
	}

	public static int getOpens() {
		return opens;
	}

	public static void setOpens(int opens) {
		Simulator.opens = opens;
	}

	public static void openNew() {
		if (opens < SCHEDULES)
			opens++;
		
		System.out.println("open new");
	}
}
