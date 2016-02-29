package operations;

import java.util.Arrays;

import polynom.Polynom;

public class Division extends Operations {
	public static void div() {
		getPol(2);
		divOp(p, q);
		delPol(2);
	}

	public static double[] divOp(Polynom N, Polynom D) {
		int dif, index;
		dif = N.order - D.order;

		Polynom d = new Polynom(N.order);

		double[] r = new double[N.order];
		Arrays.fill(r, 0);
		double[] q = new double[dif + 1];
		Arrays.fill(q, 0);

		if (N.order >= D.order) {
			while (N.order >= D.order) {
				d.takes(D.shift(dif));
				q[dif] = N.coefficients[N.order - 1] / d.coefficients[d.order - 1];
				d.multByScal(q[dif]);
				N.subPol(d);
				N.order--;
			}
			for (int i = 0; i < N.order; i++) {
				r[i] = N.coefficients[i];
			}
		}else{
			for (int i = 0; i < N.order; i++) {
				r[i] = N.coefficients[i];
			}
		}

		for (index = 0; index < q.length; index++)
			System.out.print(q[index]);

		dispRes(q, dif, false);
		return q;

	}
}
