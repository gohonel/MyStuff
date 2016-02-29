package operations;

import main.ErrorHandling;
import main.Main;
import polynom.Polynom;

public class Power extends Operations {
	public static void div() {
		getPol(1);
		powOp(p);
		delPol(1);
	}

	public static double[] powOp(Polynom p) {
		int index;
		int n = 0;
		double[] result;
		try {
			n = Integer.parseInt(Main.calculator.inputn.getText());
		} catch (NumberFormatException iError) {
			ErrorHandling.errorMsg = "Error on n" + iError.getMessage();
			ErrorHandling.error = true;
		}

		Polynom aux = new Polynom(p.order);
		aux.takes(p);

		for (index = 0; index < n; index++) {
			for (int i = 0; i < 2 * aux.order - 1; i++) {
				aux.coefficients[i] = Multiplication.mulOp(aux, aux)[i];
			}
		}
		result = aux.coefficients;
		dispRes(result, result.length, false);
		return result;
	}
}
