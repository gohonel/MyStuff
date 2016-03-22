package operations;

import java.util.Arrays;

import polynom.Polynom;

public class Multiplication extends Operations {
	public static void mul() {
		getPol(2);
		mulOp(p, q);
		delPol(2);
	}

	public static int[] mulOp(Polynom p, Polynom q) {
		int[] result;
		int max, p_index, q_index;

		max = p.order + q.order - 1;

		result = new int[max];
		Arrays.fill(result, 0);

		for (p_index = 0; p_index < p.order; p_index++) {
			for (q_index = 0; q_index < q.order; q_index++) {
				result[p_index + q_index] = result[p_index + q_index]
						+ (p.monoms.get(p_index).coefficient * q.monoms.get(q_index).coefficient);
			}
		}

		dispRes(result, max, false);
		
		return result;
	}
}
