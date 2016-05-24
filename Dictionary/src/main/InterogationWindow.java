package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InterogationWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2217572414939879332L;
	private JTextField word;
	private JTextArea text;
	private JButton button;

	public InterogationWindow(String type, String word, String[] text) {
		setWord(new JTextField(word));
		getWord().setVisible(true);

		String s = "";
		if (text != null) {
			for (String t : text) {
				s = s + " " + t;
			}
		}
		this.setText(new JTextArea(s));
		getText().setVisible(true);

		setButton(new JButton(type));
		getButton().setVisible(true);
		getButton().addActionListener(this);

		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setSize(300, 200);
		this.add(this.getWord(), BorderLayout.NORTH);
		this.add(this.getText(), BorderLayout.CENTER);
		this.add(getButton(), BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent arg0) {

	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton type) {
		this.button = type;
	}

	public JTextArea getText() {
		return text;
	}

	public void setText(JTextArea text) {
		this.text = text;
	}

	public JTextField getWord() {
		return word;
	}

	public void setWord(JTextField word) {
		this.word = word;
	}

}
