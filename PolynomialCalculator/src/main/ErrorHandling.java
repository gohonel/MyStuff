package main;

public class ErrorHandling {
	public static boolean error = false;
	public static String errorMsg;
	
	public static void displayError(){
		Main.calculator.output.setText(errorMsg);
		ErrorHandling.error = false;
	}

}
