package operations;

import polynom.Polynom;

public class Integration extends Operations {
	public static void Int() {
		getPol(1);
		intOp(p);
		delPol(1);
	}

	public static double[] intOp(Polynom p) {
		int index;
		double[] result;
		result = new double[p.order + 1];

		for (index = 0; index < p.order; index++) {
			result[index + 1] = p.coefficients[index] / (index + 1);
		}

		dispRes(result, p.order + 1, true);
		return result;
	}

}
