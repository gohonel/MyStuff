package main;

public class Product extends Node implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8266791246083928941L;
	private String productName;
	private int price;
	private int quantity;
	private int pid;

	public Product() {

	}

	public Product(String name, int price, int quantity) {
		this.setProductName(name);
		this.setPrice(price);
		this.setQuantity(quantity);
		pid = 0;
		for (int i = 0; i < name.length(); i++) {
			pid = pid * 31 + name.charAt(i);
		}
		this.setID(pid);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
		this.setID(pid);
	}

	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}

	public void reduceQuantity(int quantity) {
		if (this.quantity > quantity) {
			this.quantity = this.quantity - quantity;
		} else {
			this.quantity = 0;
		}
	}

	public int getID() {
		return pid;
	}

	public void addProducts(int q) {
		addQuantity(q);
	}
	
	public int getProdQ() {
		return quantity;
	}
	

	public String toString() {
		return productName + " " + price + " " + quantity + " " + pid;
	}
}
