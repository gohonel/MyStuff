package main;

import java.awt.event.ActionEvent;

public class NewWord extends InterogationWindow{
	/**
	 * 
	 */
	private static final long serialVersionUID = 761758450887810171L;
	
	public NewWord(){
		super("Save", null, null);		
	}

	public void actionPerformed(ActionEvent click) {
		if(click.getSource() == getButton()){
			Main.dic.addWord(getWord().getText(), getText().getText(), false);
			Database.save();
			this.setVisible(false);
			this.dispose();
		}
	}
}
