package main;

public class Customer extends Node implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6888329415229510258L;
	private String customerName;
	private String adress;
	private int cid;
	
	public Customer(){
		
	}
	
	public Customer(String name, String adress) {
		this.setCustomerName(name);
		this.setAdress(adress);
		this.cid = (int) (Math.random() * 2147483647);
		this.setID(cid);
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
		this.setID(cid);
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public int getID(){
		return cid;
	}
	
	public String toString() {
		return customerName + " " + adress + " " + cid;
	}

}
