package main;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class SimulatorFrame extends JFrame {
	private static final long serialVersionUID = -8613491258160802313L;
	private final static int WIDTH = 500;
	private final static int HEIGHT = 500;
	private JScrollPane[] sp = new JScrollPane[Simulator.SCHEDULES];
	private JTextField time = new JTextField();
	public JPanel[] panel = new JPanel[Simulator.SCHEDULES];

	public SimulatorFrame() {
		this.setSize(HEIGHT, WIDTH);
		this.setLayout(new GridLayout());
		for (int i = 0; i < Simulator.SCHEDULES; i++) {
			panel[i] = new JPanel();
			panel[i].setVisible(true);
			this.add(panel[i]);
		}
		time.setVisible(true);
		this.add(time);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void refreshTime(int time){
		this.time.setText(Integer.toString(time));
	}

	public void displayData(Task[] tasks, int queue) {
		panel[queue].removeAll();
		panel[queue].revalidate();
		JList<Task> jTasks = new JList<Task>(tasks);
		sp[queue] = new JScrollPane(jTasks);
		panel[queue].add(sp[queue]);
		panel[queue].repaint();
		panel[queue].revalidate();
	}
}
