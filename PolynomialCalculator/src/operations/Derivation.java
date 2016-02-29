package operations;

import polynom.Polynom;

public class Derivation extends Operations {
	public static void der() {
		getPol(1);
		derOp(p);
		delPol(1);
	}

	public static double[] derOp(Polynom p) {
		int index;
		double[] result;
		result = new double[p.order - 1];

		for (index = 0; index < p.order - 1; index++) {
			result[index] = p.coefficients[index + 1] * (index + 1);
		}
		dispRes(result, p.order - 1, false);
		return result;
	}
}
