
public interface Bankl {
	/**
	 * 
	 * @preCond p!=NULL && account!=NULL
	 * @param acc
	 */
	public void addAccforPers(Person p,Account acc);
	/**
	 * 
	 * @param p
	 * @param id
	 * @param money
	 */
	public void addDeposit(Person p,int id,int money);
}
