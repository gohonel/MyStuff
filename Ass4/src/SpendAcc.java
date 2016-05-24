
public class SpendAcc extends Account{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8670191808978159791L;
	private static final double CREDIT_LIMIT = -1000;

	public SpendAcc(int accid, double money) {
		super(accid, money);
		this.setType("Spending Account");
	}
	
	public void addMoney(double money) {
		super.addMoney(money);
	}

	public void withdrawMoney(double money) {
		if (this.getMoney() - money >= CREDIT_LIMIT) {
			super.withdrawMoney(money);
		}else{
			Main.gui.errorMsg("Credit limit reached!");
		}
	}
	
	public String toStrings(){
		return super.toStrings();
	}

}
