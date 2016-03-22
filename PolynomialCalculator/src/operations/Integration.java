package operations;

import polynom.Polynom;

public class Integration extends Operations {
	public static void Int() {
		getPol(1);
		intOp(p);
		delPol(1);
	}

	public static int[] intOp(Polynom p) {
		int index;
		int[] result;
		result = new int[p.order + 1];

		for (index = 0; index < p.order; index++) {
			result[index + 1] = p.monoms.get(index).coefficient / (index + 1);
		}

		dispRes(result, p.order + 1, true);
		return result;
	}

}
