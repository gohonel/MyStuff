import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 6411499808530678723L;

	private JPanel display;
	private JPanel upPanel;
	private JPanel downPanel;
	private JButton displayPeople;
	private JButton displayAccounts;
	private JButton addPerson;
	private JButton addAccount;
	private JButton delPerson;
	private JButton delAccount;
	private JButton depositMoney;
	private JButton withdrawMoney;

	public GUI() {
		display = new JPanel();
		display.setVisible(true);

		upPanel = new JPanel();
		upPanel.setVisible(true);

		downPanel = new JPanel();
		downPanel.setVisible(true);

		displayPeople = new JButton("Display People");
		displayPeople.addActionListener(this);

		displayAccounts = new JButton("Display Accounts");
		displayAccounts.addActionListener(this);

		addPerson = new JButton("Add Person");
		addPerson.addActionListener(this);

		addAccount = new JButton("Add Account");
		addAccount.addActionListener(this);

		delPerson = new JButton("Delete Person");
		delPerson.addActionListener(this);

		delAccount = new JButton("Delete Account");
		delAccount.addActionListener(this);

		depositMoney = new JButton("Deposit Money");
		depositMoney.addActionListener(this);

		withdrawMoney = new JButton("Withdraw Money");
		withdrawMoney.addActionListener(this);

		upPanel.setLayout(new GridLayout(1, 2));
		upPanel.add(displayPeople);
		upPanel.add(displayAccounts);

		downPanel.setLayout(new GridLayout(2, 3));
		downPanel.add(addPerson);
		downPanel.add(delPerson);
		downPanel.add(depositMoney);
		downPanel.add(addAccount);
		downPanel.add(delAccount);
		downPanel.add(withdrawMoney);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(600, 400);
		this.add(upPanel, BorderLayout.NORTH);
		this.add(display, BorderLayout.CENTER);
		this.add(downPanel, BorderLayout.SOUTH);
	}

	public void errorMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Account error", JOptionPane.ERROR_MESSAGE);
	}

	public void infoMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Account error", JOptionPane.INFORMATION_MESSAGE);
	}

	public void displayPeople(String[] data) {
		display.removeAll();
		display.revalidate();

		Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
		for (String row : data) {
			if (row == null) {
				break;
			}
			Vector<String> newdata = new Vector<String>();
			newdata.addAll(Arrays.asList(row.split("\\s+")));
			dataVector.add(newdata);
		}

		Vector<String> header = new Vector<String>(2);
		header.add("Name");
		header.add("ID");

		TableModel model = new DefaultTableModel(dataVector, header);
		JTable table = new JTable(model);

		display.add(new JScrollPane(table));
		display.repaint();
		display.revalidate();
	}

	public void displayAccounts() {
		String name = JOptionPane.showInputDialog(this, "Name:");
		if (name == null || name.isEmpty())
			return;

		int pid;
		try {
			pid = Integer.parseInt(JOptionPane.showInputDialog(this, "Client ID:"));
		} catch (NumberFormatException e) {
			return;
		}

		Person p = new Person(pid, name);
		String[] data = Main.bank.accountData(p);

		if (data == null) {
			errorMsg("Error retrieveing data");
		}

		display.removeAll();
		display.revalidate();

		Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
		for (String row : data) {
			if (row == null) {
				break;
			}
			Vector<String> newdata = new Vector<String>();
			newdata.addAll(Arrays.asList(row.split("\\s+")));
			dataVector.add(newdata);
		}

		Vector<String> header = new Vector<String>(2);
		header.add("ID");
		header.add("Money");
		header.add("Type");

		TableModel model = new DefaultTableModel(dataVector, header);
		JTable table = new JTable(model);

		display.add(new JScrollPane(table));

		display.repaint();
		display.revalidate();
	}

	public void actionPerformed(ActionEvent click) {
		if (click.getSource() == displayPeople) {
			displayPeople(Main.bank.peopleData());
			Database.save();
		} else if (click.getSource() == displayAccounts) {
			displayAccounts();
			Database.save();
		} else if (click.getSource() == addPerson) {
			String name = JOptionPane.showInputDialog(this, "Name:");

			int pid;
			try {
				pid = Integer.parseInt(JOptionPane.showInputDialog(this, "ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			int accid;
			try {
				accid = Integer.parseInt(JOptionPane.showInputDialog(this, "Account ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			int money;
			try {
				money = Integer.parseInt(JOptionPane.showInputDialog(this, "Money:"));
			} catch (NumberFormatException e) {
				return;
			}
			String[] options = { "Savings Account", "Spending Account", "Cancel" };
			int type = JOptionPane.showOptionDialog(this, "What account type? ", "Account type",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

			if (type == JOptionPane.CANCEL_OPTION) {
				return;
			}

			if (type == JOptionPane.YES_OPTION) {
				Main.bank.addAccforPers(new Person(pid, name), new SaveAcc(accid, money));
			} else if (type == JOptionPane.NO_OPTION) {
				Main.bank.addAccforPers(new Person(pid, name), new SpendAcc(accid, money));
			}
			displayPeople(Main.bank.peopleData());

			Database.save();
		} else if (click.getSource() == addAccount) {
			String name = JOptionPane.showInputDialog(this, "Name:");
			if (name == null || name.isEmpty())
				return;

			int pid;
			try {
				pid = Integer.parseInt(JOptionPane.showInputDialog(this, "Client ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			int accid;
			try {
				accid = Integer.parseInt(JOptionPane.showInputDialog(this, "Account ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			int money;
			try {
				money = Integer.parseInt(JOptionPane.showInputDialog(this, "Money:"));
			} catch (NumberFormatException e) {
				return;
			}
			String[] options = { "Savings Account", "Spending Account", "Cancel" };
			int type = JOptionPane.showOptionDialog(this, "What account type? ", "Account type",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

			if (type == JOptionPane.CANCEL_OPTION) {
				return;
			}
			if (type == JOptionPane.YES_OPTION) {
				Main.bank.addAccforPers(new Person(pid, name), new SaveAcc(accid, money));
			} else if (type == JOptionPane.NO_OPTION) {
				Main.bank.addAccforPers(new Person(pid, name), new SpendAcc(accid, money));
			}

			Database.save();
		} else if (click.getSource() == delPerson) {
			String name = JOptionPane.showInputDialog(this, "Name:");
			if (name == null || name.isEmpty())
				return;

			int pid;
			try {
				pid = Integer.parseInt(JOptionPane.showInputDialog(this, "ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			Main.bank.deletePerson(new Person(pid, name));
			displayPeople(Main.bank.peopleData());
			Database.save();
		} else if (click.getSource() == delAccount) {
			String name = JOptionPane.showInputDialog(this, "Name:");
			if (name == null || name.isEmpty())
				return;

			int pid;
			try {
				pid = Integer.parseInt(JOptionPane.showInputDialog(this, "Client ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			int accid;
			try {
				accid = Integer.parseInt(JOptionPane.showInputDialog(this, "Account ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			Main.bank.deleteAccount(new Person(pid, name), accid);
			Database.save();
		} else if (click.getSource() == depositMoney) {
			String name = JOptionPane.showInputDialog(this, "Name:");
			if (name == null || name.isEmpty())
				return;

			int pid;
			try {
				pid = Integer.parseInt(JOptionPane.showInputDialog(this, "Client ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			int accid;
			try {
				accid = Integer.parseInt(JOptionPane.showInputDialog(this, "Account ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			int money;
			try {
				money = Integer.parseInt(JOptionPane.showInputDialog(this, "Money:"));
			} catch (NumberFormatException e) {
				return;
			}
			Person p = new Person(pid, name);
			Main.bank.addDeposit(p, money, accid);

			Database.save();
		} else if (click.getSource() == withdrawMoney) {
			String name = JOptionPane.showInputDialog(this, "Name:");
			if (name == null || name.isEmpty())
				return;

			int pid;
			try {
				pid = Integer.parseInt(JOptionPane.showInputDialog(this, "Client ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			int accid;
			try {
				accid = Integer.parseInt(JOptionPane.showInputDialog(this, "Account ID:"));
			} catch (NumberFormatException e) {
				return;
			}

			int money;
			try {
				money = Integer.parseInt(JOptionPane.showInputDialog(this, "Money:"));
			} catch (NumberFormatException e) {
				return;
			}
			Person p = new Person(pid, name);
			Main.bank.withdrawMoney(p, money, accid);

			Database.save();
		}
	}
}
