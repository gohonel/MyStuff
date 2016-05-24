import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Database {
	public static void save() {
		try {
			FileOutputStream fos = new FileOutputStream("Database.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(Main.bank);
			oos.close();
		} catch (IOException e) {
			Main.gui.errorMsg("Error while saving");
		}

	}

	public static void load() {
		try {
			FileInputStream fis = new FileInputStream("Database.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Main.bank = (Bank) ois.readObject();
			ois.close();
			Main.gui.displayPeople(Main.bank.peopleData());
			Main.gui.infoMsg("Loadet succesfully");
		} catch (Exception e) {
			Main.gui.errorMsg("Error while loading");
		}
	}
}
