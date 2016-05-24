package main;

public class Main {
	public static GUI gui;
	public static Dictionary dic;

	public static void main(String[] args) {
		gui = new GUI();
		dic = new Dictionary();
		Database.load();
	}
}
