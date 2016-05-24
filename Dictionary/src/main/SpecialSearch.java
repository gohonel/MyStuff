package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

public class SpecialSearch extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1937661927858661777L;
	private JButton[] buttons;
	private JPanel panel;
	private JScrollPane scrollPane;
	private int length;
	private String[] words;

	public SpecialSearch(String[] words) {
		length = words.length;
		this.setWords(words);

		panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(new GridLayout(length, 1));

		buttons = new JButton[length];
		for (int i = 0; i < length; i++) {
			buttons[i] = new JButton(words[i]);
			buttons[i].addActionListener(this);
			buttons[i].setVisible(true);
			panel.add(buttons[i]);
		}
		int l = (words.length >= 5) ? 5 : words.length;

		int h = 67 + 26 * (l - 1);

		scrollPane = new JScrollPane(panel);
		scrollPane.setVisible(true);
		scrollPane.setLayout(new ScrollPaneLayout());

		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setSize(300, h);
		this.add(this.scrollPane);
	}

	public void actionPerformed(ActionEvent click) {
		for (int i = 0; i < length; i++) {
			if (click.getSource() == buttons[i]) {
				Main.dic.searchWord(getWords()[i]);
			}
		}
	}

	public String[] getWords() {
		return words;
	}

	public void setWords(String[] words) {
		this.words = words;
	}
}
