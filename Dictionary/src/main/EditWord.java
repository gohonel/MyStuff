package main;

import java.awt.event.ActionEvent;

public class EditWord extends InterogationWindow{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8102821764149355623L;

	public EditWord(String word, String[] text) {
		super("Save", word, text);
	}
	
	public void actionPerformed(ActionEvent click) {
		if(click.getSource() == getButton()){
			Main.dic.addWord(getWord().getText(), getText().getText(), true);
			Database.save();
			this.setVisible(false);
			this.dispose();
		}
	}
}
