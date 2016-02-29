package operations;

import java.util.Arrays;

import polynom.Polynom;

public class Multiplication extends Operations {
	public static void mul() {
		getPol(2);
		mulOp(p, q);
		delPol(2);
	}

	public static double[] mulOp(Polynom p, Polynom q) {
		double[] result;
		int max, p_index, q_index;

		max = p.order + q.order - 1;

		result = new double[max];
		Arrays.fill(result, 0);

		for (p_index = 0; p_index < p.order; p_index++) {
			for (q_index = 0; q_index < q.order; q_index++) {
				result[p_index + q_index] = result[p_index + q_index]
						+ (p.coefficients[p_index] * q.coefficients[q_index]);
			}
		}

		dispRes(result, max, false);
		return result;

	}
}
