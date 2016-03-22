package main;

public class ErrorHandling {
	public static boolean error = false;
	public static String errorMsg;
	
	public static void displayError(){                     // Displays error message in Result text field
		Main.calculator.output.setText(errorMsg);
	}


}
