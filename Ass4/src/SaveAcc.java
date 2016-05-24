
public class SaveAcc extends Account {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2528550861389694854L;
	private static final double INTEREST_RATE = 1.05;

	public SaveAcc(int accid, double money) {
		super(accid, getInterest(money));
		this.setType("Savings Account");
	}

	public static double getInterest(double money) {
		return money * INTEREST_RATE;
	}

	public void addMoney(double money) {
		super.addMoney(getInterest(money));
	}

	public void withdrawMoney(double money) {
		if (money < this.getMoney()) {
			super.withdrawMoney(money);
		} else {
			Main.gui.errorMsg("Insuficient funds!");
		}
	}

	public String toStrings() {
		return super.toStrings();
	}

}
