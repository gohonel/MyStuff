package main;

import java.util.Iterator;

public class OPD {
	private BST customers = new BST();
	private BST orders = new BST();

	public BST getCustomers() {
		return customers;
	}

	public void setCustomers(BST customers) {
		this.customers = customers;
	}

	public BST getOrders() {
		return orders;
	}

	public void setOrders(BST orders) {
		this.orders = orders;
	}

	public void orderProcess(Order order) {
		boolean found = false;
		for (Node c : customers.getBst()) {
			c = (Customer) c;
			if (c.getID() == order.getCid()) {
				found = true;
				break;
			}
		}
		if (!found) {
			Main.GUI.errorMsg("Customer does not exist");
			return;
		}

		Iterator<Node> iterp = GUI.Warehouse.getProducts().getBst().iterator();
		while (iterp.hasNext()) {
			Product n = (Product) iterp.next();
			if (order.getPid() == n.getID()) {
				if (n.getProdQ() > order.getProdQ()) {
					GUI.Warehouse.getProducts().removeProduct(order.getPid(), order.getProdQ());
					Main.GUI.infoMsg("Order " + order.getOid() + " has been sent");
				} else {
					Main.GUI.errorMsg("Not enough items in stock");
					this.orders.add(order);
				}
				return;
			}
		}
		Main.GUI.errorMsg("Item not on stock");
		this.orders.add(order);
	}

	public void verifyOrders() {
		Iterator<Node> iterp = GUI.Warehouse.getProducts().getBst().iterator();
		Iterator<Node> itero = orders.getBst().iterator();
		while (iterp.hasNext()) {
			Product p = (Product) iterp.next();
			while (itero.hasNext()) {
				Order o = (Order) itero.next();
				if (o.getPid() == p.getID()) {
					if (p.getProdQ() > o.getProdQ()) {
						GUI.Warehouse.getProducts().removeProduct(o.getPid(), o.getProdQ());
						Main.GUI.infoMsg("Order " + o.getOid() + " has been sent");
						itero.remove();
					}
				}
			}
		}
	}
}
