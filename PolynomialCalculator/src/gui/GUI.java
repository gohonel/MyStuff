package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import operations.Addition;
import operations.Derivation;
import operations.Division;
import operations.Integration;
import operations.Multiplication;
import operations.Subtraction;

public class GUI implements ActionListener {

	public JFrame window;
	public JPanel leftField;
	public JPanel ioField;
	public JPanel buttonField; // Buttons for:
	public JButton addButton; // addition
	public JButton subButton; // subtraction
	public JButton mulButton; // multiplication
	public JButton divButton; // division
	public JButton powButton; // power
	public JButton derButton; // derivation
	public JButton intButton; // integration
	public JButton clrButton; // resetting
	public JTextField inputP;
	public JTextField inputn;
	public JTextField inputQ;
	public JTextField output;
	public JTextField[] textPan;

	public GUI() {
		addButton = new JButton("Addition (+)");
		addButton.addActionListener(this);

		subButton = new JButton("Subtraction (-)");
		subButton.addActionListener(this);

		mulButton = new JButton("Multiplication (*)");
		mulButton.addActionListener(this);

		divButton = new JButton("Division (/)");
		divButton.addActionListener(this);

		powButton = new JButton("Power n (^n)");
		powButton.addActionListener(this);

		derButton = new JButton("Derivation (d/dx)");
		derButton.addActionListener(this);

		intButton = new JButton("Integration (∫)");
		intButton.addActionListener(this);

		clrButton = new JButton("Clear");
		clrButton.addActionListener(this);

		buttonField = new JPanel();
		buttonField.setVisible(true);
		buttonField.setLayout(new GridLayout(8, 1));
		buttonField.add(addButton);
		buttonField.add(subButton);
		buttonField.add(mulButton);
		buttonField.add(divButton);
		buttonField.add(powButton);
		buttonField.add(derButton);
		buttonField.add(intButton);
		buttonField.add(clrButton);

		inputP = new JTextField(300);
		inputn = new JTextField(300);
		inputQ = new JTextField(300);

		output = new JTextField(300);

		Color fgColor = UIManager.getColor("TextField.foreground");

		textPan = new JTextField[4];
		textPan[0] = new JTextField("P(x) = ", 10);
		textPan[1] = new JTextField("n = ", 10);
		textPan[2] = new JTextField("Q(x) = ", 10);
		textPan[3] = new JTextField("Result:", 10);
		for (int i = 0; i < 4; i++) {
			textPan[i].setHorizontalAlignment(JTextField.CENTER);
			textPan[i].setEnabled(false);
			textPan[i].setDisabledTextColor(fgColor);
		}

		leftField = new JPanel();
		leftField.setVisible(true);
		leftField.setLayout(new GridLayout(8, 1));
		leftField.add(new JPanel());
		leftField.add(textPan[0]);
		leftField.add(textPan[1]);
		leftField.add(textPan[2]);
		leftField.add(new JPanel());
		leftField.add(textPan[3]);
		leftField.add(new JPanel());
		leftField.add(new JPanel());

		ioField = new JPanel();
		ioField.setVisible(true);
		ioField.setLayout(new GridLayout(8, 1));
		ioField.add(new JPanel());
		ioField.add(inputP);
		ioField.add(inputn);
		ioField.add(inputQ);
		ioField.add(new JPanel());
		ioField.add(output);
		ioField.add(new JPanel());
		ioField.add(new JPanel());

		window = new JFrame("Polynomial Calculator");
		window.setLayout(new BorderLayout());
		window.setVisible(true);
		window.setSize(800, 400);
		window.add(ioField, BorderLayout.CENTER);
		window.add(buttonField, BorderLayout.EAST);
		window.add(leftField, BorderLayout.WEST);

	}

	public void clearText() {
		inputP.setText("");
		inputQ.setText("");
		output.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent click) {
		if (click.getSource() == addButton) {
			Addition.add();
		} else if (click.getSource() == subButton) {
			Subtraction.sub();
		} else if (click.getSource() == mulButton) {
			Multiplication.mul();
		} else if (click.getSource() == divButton) {
			Division.div();
		} else if (click.getSource() == derButton) {
			Derivation.der();
		} else if (click.getSource() == intButton) {
			Integration.Int();
		} else if (click.getSource() == clrButton) {
			clearText();
		}
	}
}
