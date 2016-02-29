package operations;

import polynom.Polynom;

public class Addition extends Operations {

	public static void add() {
		getPol(2);
		addOp(p, q);
		delPol(2);
	}

	public static double[] addOp(Polynom p, Polynom q) {
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
			result[index] = p.coefficients[index] + q.coefficients[index];
			System.out.println(result[index] + " " + p.coefficients[index] + " " + q.coefficients[index]);
		}

		for (index = min; index < max; index++) {
			if (p.order > q.order) {
				result[index] = p.coefficients[index];
			} else {
				result[index] = q.coefficients[index];
			}
		}

		dispRes(result, max, false);
		return result;

	}
}
