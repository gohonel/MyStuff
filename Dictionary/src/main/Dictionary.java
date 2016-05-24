package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Dictionary implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1277043950403486281L;
	private Map<String, String[]> map;

	public Dictionary() {
		map = new HashMap<String, String[]>();
	}

	public void addWord(String word, String auxText, boolean edit) {
		assert word != null : "The person must not be null!";
		int preSize = 0;
		if (map.containsKey(word) && edit == false) {
			Main.gui.infoMsg("Word already existent");
		} else if (!map.containsKey(word) || edit == true) {
			String[] text = auxText.split(" ");
			preSize = 0;
			removeWord(word, false);
			map.put(word, text);
		}
		int postSize = map.get(word).length;
		assert preSize != postSize - 1 : "Error";
	}

	public void editWord(String word) {
		if (map.containsKey(word)) {
			new EditWord(word, map.get(word));
		} else {
			Main.gui.errorMsg("Word not in dictionary!");
		}
	}

	public void removeWord(String word, boolean error) {
		if (map.containsKey(word)) {
			map.remove(word);
		} else if (error == true) {
			Main.gui.errorMsg("Word not in dictionary!");
		}
		Database.save();
	}

	public void searchWord(String word) {
		if (map.containsKey(word)) {
			new DisplayWord(word, map.get(word));
		} else {
			Main.gui.errorMsg("Word not in dictionary!");
		}
	}

	public void specialSearch(String search) {
		boolean add = true;
		ArrayList<String> result = new ArrayList<String>();

		if (search.equals("*")) {
			String[] resultS = new String[map.size()];
			int i = 0;
			for (String word : map.keySet()) {
				resultS[i] = word;
				i++;
			}
			new SpecialSearch(resultS);
		} else {
			for (String word : map.keySet()) {
				add = true;
				String[] strings = search.split("[*?]+");
				for (String s : strings) {
					if (!word.toLowerCase().contains(s.toLowerCase())) {
						add = false;
					}
				}
				if (add) {
					result.add(word);
				}
			}
			if (!result.isEmpty()) {
				new SpecialSearch(result.toArray(new String[result.size()]));
			} else {
				Main.gui.errorMsg("No word found in dctionary!");
			}
		}
	}

	public boolean isConsistent() {
		boolean found = false;

		for (String key : map.keySet()) {
			for (String def : map.get(key)) {
				found = false;
				for (String word : map.keySet()) {
					if (def.toLowerCase().equals(word.toLowerCase())) {
						found = true;
					}
				}
				if (!found)
					return false;
			}
		}
		return true;
	}

	public Map<String, String[]> getMap() {
		return map;
	}

	public void setMap(Map<String, String[]> map) {
		this.map = map;
	}

	public Boolean isWellFormed() {
		for (Entry<String, String[]> entry : map.entrySet()) {
			if (entry.getValue() != null || entry.getValue().toString() != "") {
				return false;
			}
		}
		return true;
	}

	public String getDefinition(String word) {
		String result = new String();
		if (map.containsKey(word)) {
			for (String words : map.get(word)) {
				result = result + " " + words;
			}
		}
		return result;
	}
}
