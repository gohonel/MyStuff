
public class Main {
	public static Bank bank;
	public static GUI gui;

	public static void main(String[] args) {
		bank = new Bank();
		gui = new GUI();
		Database.load();
	}
}
