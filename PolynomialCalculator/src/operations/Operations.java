package operations;

import main.ErrorHandling;
import main.Main;
import polynom.Polynom;

public class Operations {
	static Polynom p;
	static Polynom q;

	public static void getPol(int i) {                                                 // retrieves the polynomials to be used by the operations
		if (i == 2) {
			p = new Polynom("P(x)", Main.calculator.inputP.getText());
			q = new Polynom("Q(X)", Main.calculator.inputQ.getText());
		} else if (i == 1) {
			p = new Polynom("P(x)", Main.calculator.inputP.getText());
		}
	}

	public static void dispRes(int[] result, int max, boolean integrate) {
		StringBuilder value = new StringBuilder();
		int sum = 0;
		int index = 0;                                                                // this portion is to build a nice string for the output

		for (index = max - 1; index >= 0; index--) {
			if (result[index] < 0 && index == max - 1) {
				value.append(" - ");
			}
			if (result[index] != 0) {
				if (index > 1) {
					if (result[index] != 1)
						value.append(Integer.toString(Math.abs(result[index])));
					value.append("x^");
					value.append(Integer.toString(index));
					if (result[index - 1] > 0) {
						value.append(" + ");
					} else if (result[index - 1] < 0) {
						value.append(" - ");
					}
				} else if (index == 1) {
					if (result[index] != 1)
						value.append(Integer.toString(Math.abs(result[index])));
					value.append("x");
					if (result[index - 1] > 0) {
						value.append(" + ");
					} else if (result[index - 1] < 0) {
						value.append(" - ");
					}
				} else {
					value.append(Integer.toString(Math.abs(result[index])));
				}
			} else if (index > 0) {
				if (result[index - 1] > 0) {
					value.append(" + ");
				} else if (result[index - 1] < 0) {
					value.append(" - ");
				}
			}
			sum = sum + result[index];
		}

		if (sum == 0) {
			value.append("0");
		}
		
		if(integrate){
			value.append(" + C");
		}
		
		Main.calculator.output.setText(value.toString());

		if (ErrorHandling.error) {
			ErrorHandling.displayError();
		}
	}

	public static void delPol(int i) {                      // nullifies the polynomial objects used for the operations
		if (i == 2) {
			p = null;
			q = null;
		} else if (i == 1) {
			p = null;
		}
	}
}
