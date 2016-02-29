package operations;

import polynom.Polynom;

public class Subtraction extends Operations {

	public static void sub() {
		getPol(2);
		subOp(p, q);
		delPol(2);
	}

	public static double[] subOp(Polynom p, Polynom q) {
		double[] result;
		int min, max, index;

		if (p.order > q.order) {
			min = q.order;
			max = p.order;
		} else {
			min = p.order;
			max = q.order;
		}

		result = new double[max];

		for (index = 0; index < min; index++) {
			result[index] = p.coefficients[index] - q.coefficients[index];
		}

		for (index = min; index < max; index++) {
			if (p.order > q.order) {
				result[index] = p.coefficients[index];
			} else {
				result[index] = 0 - q.coefficients[index];
			}
		}

		dispRes(result, max, false);
		return result;
	}
}
