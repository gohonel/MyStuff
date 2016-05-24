package main;

import java.util.ArrayList;

public class BST implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8470369457215842267L;
	private Node root;
	private ArrayList<Node> bsta = new ArrayList<Node>();
	private Product p = new Product();

	public void add(Node newNode) {
		if (newNode.getClass() == p.getClass()) {
			for (Node n : bsta) {
				if (newNode.getID() == n.getID()) {
					n.addQuantity(newNode.getProdQ());
					return;
				}
			}
		}

		bsta.add(newNode);
	}

	public String[] inOrder(Node node) {
		String[] s = new String[100];
		int i = 0;
		for (Node n : bsta) {
			s[i] = n.toString();
			i++;
		}
		return s;
	}

	public void remove(int id) {
		for (int i = 0; i < bsta.size(); i++) {
			if (id == bsta.get(i).getID()) {
				bsta.remove(bsta.get(i));
			}
		}
	}

	public void removeProduct(int id, int quantity) {
		for (int i = 0; i < bsta.size(); i++) {
			if (id == bsta.get(i).getID()) {
				bsta.get(i).addQuantity(-quantity);
				;
			}
		}
	}

	public BST() {
		this.setRoot(null);
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * public void add(Node newNode) { if (this.root == null) { this.root =
	 * newNode; } else { Node current = root; if (newNode.getID() <
	 * current.getID()) { if (current.getLeft() != null) { current =
	 * current.getLeft(); } else { current.setLeft(newNode); } } else if
	 * (newNode.getID() > current.getID()) { if (current.getRight() != null) {
	 * current = current.getRight(); } else { current.setRight(newNode); } }
	 * else if (newNode.getID() == current.getID()) { if (newNode.getClass() ==
	 * p.getClass()) { current.addProducts(newNode.getProdQ()); } else { if
	 * (current.getLeft() != null) { current = current.getLeft(); } else {
	 * current.setLeft(newNode); } } } } }
	 * 
	 * public void remove(int ID) { Node current = root; if (ID <
	 * current.getID()) { if (current.getLeft().getID() == ID) {
	 * current.setLeft(current.getLeft().getLeft()); return; } else { current =
	 * current.getLeft(); } } else if (ID > current.getID()) { if
	 * (current.getRight().getID() == ID) {
	 * current.setRight(current.getRight().getRight()); return; } else { current
	 * = current.getRight(); } } }
	 * 
	 * public Node search(int ID) { if (this.root == null) { return null; } else
	 * { Node current = root; if (ID == current.getID()) { return current; }
	 * else if (ID > current.getID()) { current = current.getRight(); } else if
	 * (ID < current.getID()) { current = current.getLeft(); } } return null; }
	 * 
	 * 
	 * public String[] inOrder(Node current) { String[] result = new
	 * String[100]; result[i] = current.toStrings(); i++; if (current.getLeft()
	 * != null) { inOrder(current.getLeft()); }
	 * 
	 * if (current.getRight() != null) { inOrder(current.getRight()); }
	 * 
	 * return result; }
	 * 
	 * public void reseti() { i = 0; }
	 **/

	public ArrayList<Node> getBst() {
		return bsta;
	}

	public void setBst(ArrayList<Node> bst) {
		this.bsta = bst;
	}
}
