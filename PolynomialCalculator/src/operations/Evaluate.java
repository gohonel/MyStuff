package operations;

import main.ErrorHandling;
import main.Main;
import polynom.Polynom;

public class Evaluate extends Operations {
	public static void evl() {
		getPol(1);
		evlOp(p);
		delPol(1);
	}

	public static int[] evlOp(Polynom p) {
		int[] result = new int[1];
		int index, max = 1;
		int n = 0;
		
		try {
			n = Integer.parseInt(Main.calculator.inputn.getText());			
		} catch (NumberFormatException iError) {
			ErrorHandling.errorMsg = "Error on n " + iError.getMessage();
			ErrorHandling.error = true;
			ErrorHandling.displayError();
		}

		for (index = 0; index < p.order; index++) {
			result[0] = (int) (result[0] + ((Math.pow(n, index)) * p.monoms.get(index).coefficient));
		}

		dispRes(result, max, false);
		return result;
	}
}
