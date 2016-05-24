package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = -7936081133021236059L;

	private JPanel buttons;
	private JButton addWord;
	private JButton removeWord;
	private JButton search;
	private JButton edit;
	private JButton specialSearch;
	private JButton isConsistent;

	public GUI() {
		buttons = new JPanel();
		buttons.setVisible(true);

		addWord = new JButton("Add new entry");
		addWord.addActionListener(this);

		removeWord = new JButton("Remove entry");
		removeWord.addActionListener(this);

		search = new JButton("Search");
		search.addActionListener(this);

		edit = new JButton("Edit");
		edit.addActionListener(this);

		specialSearch = new JButton("Special Search");
		specialSearch.addActionListener(this);

		isConsistent = new JButton("Is Consistent");
		isConsistent.addActionListener(this);

		buttons.setLayout(new GridLayout(3, 2));
		buttons.add(addWord);
		buttons.add(removeWord);
		buttons.add(search);
		buttons.add(edit);
		buttons.add(specialSearch);
		buttons.add(isConsistent);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(260, 120);
		this.add(buttons);
	}

	public void errorMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void infoMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
	}

	public synchronized void actionPerformed(ActionEvent click) {
		if (click.getSource() == addWord) {
			new NewWord();
			
			
		} else if (click.getSource() == removeWord) {
			String word = JOptionPane.showInputDialog(this, "Word:");
			if (word == null || word.isEmpty())
				return;
			Main.dic.removeWord(word, true);
			
		} else if (click.getSource() == edit) {
			String word = JOptionPane.showInputDialog(this, "Word:");
			if (word == null || word.isEmpty())
				return;
			Main.dic.editWord(word);
			
		} else if (click.getSource() == search) {
			String word = JOptionPane.showInputDialog(this, "Word:");
			if (word == null || word.isEmpty())
				return;
			Main.dic.searchWord(word);
			
		} else if (click.getSource() == specialSearch) {
			String word = JOptionPane.showInputDialog(this, "Word:");
			if (word == null || word.isEmpty())
				return;
			Main.dic.specialSearch(word);
			
		} else if (click.getSource() == isConsistent) {
			if (Main.dic.isConsistent()) {
				this.infoMsg("Dictionary is consistent");
			} else {
				this.errorMsg("Dictionary is not consistent!");
			}
		}
	}
}
