package operations;

import polynom.Polynom;

public class Addition extends Operations {

	public static void add() {
		getPol(2);
		addOp(p, q);
		delPol(2);
	}

	public static int[] addOp(Polynom p, Polynom q) {
		int[] result;
		int min, max, index;

		if (p.order > q.order) {
			min = q.order;
			max = p.order;
		} else {
			min = p.order;
			max = q.order;
		}

		result = new int[max];

		for (index = 0; index < min; index++) {
			result[index] = p.monoms.get(index).coefficient + q.monoms.get(index).coefficient;
		}

		for (index = min; index < max; index++) {
			if (p.order > q.order) {
				result[index] = p.monoms.get(index).coefficient;
			} else {
				result[index] = q.monoms.get(index).coefficient;
			}
		}

		dispRes(result, max, false);
		return result;

	}
}
