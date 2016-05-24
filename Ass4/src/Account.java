import java.util.Observable;

public class Account extends Observable implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int accId;
	private double money;
	private String type;

	public Account(int accid, double money) {
		this.money = money;
		this.accId = accid;
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public double getMoney() {
		return money;
	}

	@Override
	public String toString() {
		return "Account [accId=" + accId + ", money=" + money + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accId != other.accId)
			return false;

		return true;
	}

	public void setMoney(double d) {
		this.money = d;
	}

	public void addMoney(double d) {
		setMoney(d + this.money);
		notifyObservers(d + this.money);
	}

	public void withdrawMoney(double money) {
		setMoney(this.money - money);
	}

	public String toStrings() {
		String result = new String();

		result = Integer.toString(accId) + " " +Double.toString(money) + " " + this.type;

		return result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
