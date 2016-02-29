package operations;

public class Shift {

	public static double[] sft(double[] coefficients, int deg) {
		double[] result = new double[coefficients.length + deg];
		int index;

		for (index = 0; index < coefficients.length; index++) {
			result[index + deg] = coefficients[index];
		}

		return result;
	}
}
