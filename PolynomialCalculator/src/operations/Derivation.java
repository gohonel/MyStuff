package operations;

import polynom.Polynom;

public class Derivation extends Operations {
	
	public static void der() {
		getPol(1);
		derOp(p);
		delPol(1);
	}

	public static int[] derOp(Polynom p) {
		int index;
		int[] result;
		result = new int[p.order - 1];

		for (index = 0; index < p.order - 1; index++) {
			result[index] = p.monoms.get(index + 1).coefficient * (index + 1);
		}
		dispRes(result, p.order - 1, false);
		return result;
	}
}
