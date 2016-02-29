package polynom;

import java.util.Arrays;

import main.ErrorHandling;

public class Polynom {
	public String name;
	public double[] coefficients;
	public int order = 0;

	public Polynom(String name, String inputString) {
		this.name = name;
		splitString(inputString);
	}

	public Polynom(int order) {
		this.order = order;
		coefficients = new double[this.order];
		Arrays.fill(coefficients, 0);
	}

	public void splitString(String input) {
		String[] stringArray = input.split(",");
		order = stringArray.length;
		coefficients = new double[order];
		try {
			for (int i = order - 1; i >= 0; i--) {
				coefficients[order - i - 1] = Integer.parseInt(stringArray[i]);
			}
		} catch (NumberFormatException iError) {
			ErrorHandling.errorMsg = "Error on " + name + " " + iError.getMessage();
			ErrorHandling.error = true;
		}
	}

	public Polynom shift(int deg) {
		Polynom result = new Polynom(this.order + deg + 1);
		for (int i = (order - 1); i >= 0; i--) {
			result.coefficients[i + deg] = this.coefficients[i];
		}
		return result;
	}

	public void takes(Polynom p) {
		if (this.order == p.order) {
			for (int i = 0; i < order; i++) {
				this.coefficients[i] = p.coefficients[i];
			}
		}
	}

	public void multByScal(double scalar) {
		for (int i = 0; i < order; i++) {
			coefficients[i] = coefficients[i] * scalar;
		}
	}

	public void subPol(Polynom subtrahend) {
		for (int i = 0; i < order; i++) {
			coefficients[i] = coefficients[i] - subtrahend.coefficients[i];
		}
	}

}
