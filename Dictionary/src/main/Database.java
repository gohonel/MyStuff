package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Database {
	public static void save() {
		JSONObject obj = new JSONObject();
		JSONArray words = new JSONArray();
		JSONArray definitions = new JSONArray();

		for (String word : Main.dic.getMap().keySet()) {
			words.add(word);
			definitions.add(Main.dic.getDefinition(word));
		}
		obj.put("Words:", words);
		obj.put("Definitions:", definitions);

		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter("file1.txt")) {
			file.write(obj.toJSONString());
		} catch (IOException e) {
			Main.gui.errorMsg("Error while saving");
		}

	}

	public static void load() {
		JSONParser parser = new JSONParser();

		try {
			Dictionary dic = new Dictionary();
			Object obj = parser.parse(new FileReader("file1.txt"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray words = (JSONArray) jsonObject.get("Words:");
			JSONArray definitions = (JSONArray) jsonObject.get("Definitions:");
			
			Iterator<String> wi = words.iterator();
			Iterator<String> di = definitions.iterator();
			while (wi.hasNext()) {
				dic.addWord(wi.next(), di.next(), false);
			}
			Main.dic = dic;
		} catch (Exception e) {
			Main.gui.errorMsg("Error while loading");
		}
	}
}
