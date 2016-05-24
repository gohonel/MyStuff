package main;

import java.awt.event.ActionEvent;

public class DisplayWord extends InterogationWindow {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1737919110346289049L;

	public DisplayWord(String word, String[] text) {
		super("Close", word, text);
	}

	public void actionPerformed(ActionEvent click) {
		if (click.getSource() == getButton()) {
			this.dispose();
		}
	}
}
