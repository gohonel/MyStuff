package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class GUI implements ActionListener {
	private JButton addCustomer;
	private JButton addProduct;
	private JButton addOrder;
	private JButton delCustomer;
	private JButton delProduct;
	private JButton delOrder;
	private JButton save;
	private JPanel[] fields;
	private JPanel[] bFields;
	private JPanel[] tFields;
	public JFrame window;

	public static OPD OPD = new OPD();
	public static Warehouse Warehouse = new Warehouse();

	public GUI() {
		load();
		addCustomer = new JButton("Add Customer");
		addCustomer.addActionListener(this);

		addProduct = new JButton("Add Product");
		addProduct.addActionListener(this);

		addOrder = new JButton("Add Order");
		addOrder.addActionListener(this);

		delCustomer = new JButton("Delete Customer");
		delCustomer.addActionListener(this);

		delProduct = new JButton("Delete Product");
		delProduct.addActionListener(this);

		delOrder = new JButton("Delete Order");
		delOrder.addActionListener(this);

		save = new JButton("Save");
		save.addActionListener(this);

		fields = new JPanel[3];
		bFields = new JPanel[3];
		tFields = new JPanel[3];
		for (int i = 0; i < 3; i++) {
			fields[i] = new JPanel();
			fields[i].setVisible(true);
			fields[i].setLayout(new BorderLayout());
			bFields[i] = new JPanel();
			bFields[i].setVisible(true);
			bFields[i].setLayout(new GridLayout(1, 3));
			tFields[i] = new JPanel();
			tFields[i].setVisible(true);
			tFields[i].setLayout(new GridLayout());
		}

		bFields[0].add(addCustomer);
		bFields[0].add(delCustomer);
		fields[0].add(bFields[0], BorderLayout.SOUTH);
		fields[0].add(tFields[0]);

		bFields[1].add(addProduct);
		bFields[1].add(delProduct);
		fields[1].add(bFields[1], BorderLayout.SOUTH);
		fields[1].add(tFields[1]);

		bFields[2].add(addOrder);
		bFields[2].add(delOrder);
		bFields[2].add(save);
		fields[2].add(bFields[2], BorderLayout.SOUTH);
		fields[2].add(tFields[2]);

		JFrame window = new JFrame("Order Management");
		window.setLayout(new GridLayout(1, 3));
		window.setVisible(true);
		window.setSize(900, 400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (int i = 0; i < 3; i++) {
			window.add(fields[i]);
		}
		
		repaintProduct(Warehouse.getProducts().inOrder(Warehouse.getProducts().getRoot()));
		repaintCustomers(OPD.getCustomers().inOrder(OPD.getCustomers().getRoot()));
		repaintOrders(OPD.getOrders().inOrder(OPD.getOrders().getRoot()));
	}
	
	public void errorMsg(String msg){
		JOptionPane.showMessageDialog(window, msg, "Account error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void infoMsg(String msg){
		JOptionPane.showMessageDialog(window, msg, "Account error", JOptionPane.INFORMATION_MESSAGE);
	}
	

	public void repaintCustomers(String[] ini) {
		tFields[0].removeAll();
		tFields[0].revalidate();

		Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
		for (String row : ini) {
			if (row == null) {
				break;
			}
			Vector<String> newdata = new Vector<String>();
			newdata.addAll(Arrays.asList(row.split("\\s+")));
			dataVector.add(newdata);
		}

		Vector<String> header = new Vector<String>(2);
		header.add("Name");
		header.add("Adress");
		header.add("ID");

		TableModel model = new DefaultTableModel(dataVector, header);
		JTable table = new JTable(model);

		tFields[0].add(new JScrollPane(table));
		tFields[0].repaint();
		tFields[0].revalidate();
	}

	public void repaintProduct(String[] ini) {
		tFields[1].removeAll();
		tFields[1].revalidate();

		Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
		for (String row : ini) {
			if (row == null) {
				break;
			}
			Vector<String> newdata = new Vector<String>();
			newdata.addAll(Arrays.asList(row.split("\\s+")));
			dataVector.add(newdata);
		}

		Vector<String> header = new Vector<String>(2);
		header.add("Name");
		header.add("Price");
		header.add("Quantity");
		header.add("ID");

		TableModel model = new DefaultTableModel(dataVector, header);
		JTable table = new JTable(model);

		tFields[1].add(new JScrollPane(table));
		tFields[1].repaint();
		tFields[1].revalidate();
	}

	public void repaintOrders(String[] ini) {
		tFields[2].removeAll();
		tFields[2].revalidate();

		Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
		for (String row : ini) {
			if (row == null) {
				break;
			}
			Vector<String> newdata = new Vector<String>();
			newdata.addAll(Arrays.asList(row.split("\\s+")));
			dataVector.add(newdata);
		}

		Vector<String> header = new Vector<String>(2);
		header.add("oID");
		header.add("cID");
		header.add("pID");
		header.add("Quantity");

		TableModel model = new DefaultTableModel(dataVector, header);
		JTable table = new JTable(model);

		tFields[2].add(new JScrollPane(table));
		tFields[2].repaint();
		tFields[2].revalidate();
	}

	public void save() {
		try {
			FileOutputStream fos = new FileOutputStream("Save.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(OPD.getCustomers());
			oos.writeObject(Warehouse.getProducts());
			oos.writeObject(OPD.getOrders());
			oos.close();
			infoMsg("Saved succesfully");
		} catch (IOException e) {
			errorMsg("Error while saving");
		}

	}

	public void load() {
		try {
			FileInputStream fis = new FileInputStream("Save.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			OPD.setCustomers((BST) ois.readObject());
			Warehouse.setProducts((BST) ois.readObject());
			OPD.setOrders((BST) ois.readObject());
			ois.close();
			infoMsg("Loadet succesfully");
		} catch (Exception e) {
			errorMsg("Error while loading");
		}
	}

	public void actionPerformed(ActionEvent click) {
		if (click.getSource() == addCustomer) {
			String name = JOptionPane.showInputDialog(window, "Customer Name:");
			if (name == null || name.isEmpty())
				return;

			String adress = JOptionPane.showInputDialog(window, "Customer Adress:");
			if (adress == null || adress.isEmpty())
				return;

			GUI.OPD.getCustomers().add(new Customer(name, adress));
			repaintCustomers(OPD.getCustomers().inOrder(OPD.getCustomers().getRoot()));
		} else if (click.getSource() == addProduct) {
			String name = JOptionPane.showInputDialog(window, "Product Name:");
			if (name == null || name.isEmpty())
				return;

			int price;
			try {
				price = Integer.parseInt(JOptionPane.showInputDialog(window, "Product price:"));
			} catch (NumberFormatException e) {
				return;
			}

			int quantity;
			try {
				quantity = Integer.parseInt(JOptionPane.showInputDialog(window, "Product quantity:"));
			} catch (NumberFormatException e) {
				return;
			}

			GUI.Warehouse.getProducts().add(new Product(name, price, quantity));
			GUI.OPD.verifyOrders();
			repaintProduct(Warehouse.getProducts().inOrder(Warehouse.getProducts().getRoot()));
		} else if (click.getSource() == addOrder) {
			int pid;
			try {
				pid = Integer.parseInt(JOptionPane.showInputDialog(window, "Product ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			int cid;
			try {
				cid = Integer.parseInt(JOptionPane.showInputDialog(window, "Customer ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			int quantity;
			try {
				quantity = Integer.parseInt(JOptionPane.showInputDialog(window, "Product quantity:"));
			} catch (NumberFormatException e) {
				return;
			}
			GUI.OPD.orderProcess(new Order(pid, cid, quantity));
			GUI.OPD.verifyOrders();
			repaintProduct(Warehouse.getProducts().inOrder(Warehouse.getProducts().getRoot()));
			
			//this.OPD.getOrders().add(new Order(pid, cid, quantity));
			repaintOrders(OPD.getOrders().inOrder(OPD.getOrders().getRoot()));
		} else if (click.getSource() == delCustomer) {
			int cid;
			try {
				cid = Integer.parseInt(JOptionPane.showInputDialog(window, "Customer ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			GUI.OPD.getCustomers().remove(cid);
			repaintCustomers(OPD.getCustomers().inOrder(OPD.getCustomers().getRoot()));
		} else if (click.getSource() == delProduct) {
			int pid;
			try {
				pid = Integer.parseInt(JOptionPane.showInputDialog(window, "Product ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			int quantity;
			try {
				quantity = Integer.parseInt(JOptionPane.showInputDialog(window, "Quantity:"));
			} catch (NumberFormatException e) {
				return;
			}

			GUI.Warehouse.getProducts().removeProduct(pid, quantity);
			repaintProduct(Warehouse.getProducts().inOrder(Warehouse.getProducts().getRoot()));
		} else if (click.getSource() == delOrder) {
			int pid;
			try {
				pid = Integer.parseInt(JOptionPane.showInputDialog(window, "Order ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			GUI.OPD.getOrders().remove(pid);
			repaintOrders(OPD.getOrders().inOrder(OPD.getOrders().getRoot()));
		} else if (click.getSource() == save) {
			save();
		}
	}
}
