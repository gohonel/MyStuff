package polynom;

import java.util.ArrayList;

import main.ErrorHandling;

public class Polynom {
	public String name;
	public int order = 0;
	public ArrayList<Monom> monoms = new ArrayList<Monom>();
	
	public Polynom(String name, String inputString) {
		this.name = name;
		splitString(inputString);
	}
	
	public void splitString(String input) {                 // fishes out the usable data given by the user
		String[] stringArray = input.split(",");
		order = stringArray.length;
		ErrorHandling.error = false;
		try {
			for (int i = order - 1; i >= 0; i--) {
				monoms.add(new Monom(Integer.parseInt(stringArray[i]),i));
			}
		} catch (NumberFormatException iError) {           //data validation
			ErrorHandling.errorMsg = "Error on " + name + " " + iError.getMessage();
			ErrorHandling.error = true;
			ErrorHandling.displayError();
		}
	}
}
