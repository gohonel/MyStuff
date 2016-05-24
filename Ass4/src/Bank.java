import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Bank implements Bankl, java.io.Serializable{
	private static final long serialVersionUID = 9158463052890296347L;
	private Map<Person, Set<Account>> map;

	public Bank() {
		map = new HashMap<Person, Set<Account>>();
	}
	
	public void setBank(Bank bank){
		this.map = bank.map;
	}

	public void addAccforPers(Person p, Account ac) {
		assert p != null : "The person must not be null!";
		int preSize;
		if (map.containsKey(p)) {
			preSize = map.get(p).size();
			map.get(p).add(ac);
		} else {
			Set<Account> accounts = new HashSet<Account>();
			preSize = 0;
			accounts.add(ac);
			map.put(p, accounts);
		}
		int postSize = map.get(p).size();
		assert preSize != postSize - 1 : "Error";
	}

	@Override
	public String toString() {
		return "Bank [map=" + map + "]";
	}

	public String[] peopleData() {
		String[] result = new String[2];
		int i = 0;

		for (Map.Entry<Person, Set<Account>> entry : map.entrySet()) {
			result[i] = entry.getKey().toStrings();
			i++;
		}

		return result;
	}

	public String[] accountData(Person p) {
		assert p != null : "The person must not be null!";
		if (map.containsKey(p)) {
			Set<Account> accounts = map.get(p);
			String[] result = new String[3];
			int i = 0;

			for (Account a : accounts) {
				result[i] = a.toStrings();
				i++;
			}

			return result;
		}
		return null;
	}

	public void addDeposit(Person start, int money, int accId) {
		if (map.containsKey(start)) {
			Set<Account> accounts = map.get(start);
			System.out.println();
			for (Account a : accounts) {
				if (a.getAccId() == accId) {
					a.addMoney(money);
					break;
				}
			}
		}
	}

	public void withdrawMoney(Person start, int money, int accId) {
		if (map.containsKey(start)) {
			Set<Account> accounts = map.get(start);
			System.out.println();
			for (Account a : accounts) {
				if (a.getAccId() == accId) {
					a.withdrawMoney(money);
					break;
				}
			}
		}
	}

	public void deletePerson(Person p) {
		if (map.containsKey(p)) {
			Set<Account> accounts = map.get(p);
			map.get(p).remove(accounts);
			map.remove(p);
		}
	}

	public void deleteAccount(Person p, int accId) {
		if (map.containsKey(p)) {
			Set<Account> accounts = map.get(p);
			System.out.println();
			for (Account a : accounts) {
				if (a.getAccId() == accId) {
					accounts.remove(a);
					break;
				}
			}
		}
	}

	public Boolean isWellFormed() {
		for (Entry<Person, Set<Account>> entry : map.entrySet()) {
			if (entry.getValue() != null || entry.getValue().isEmpty()) {
				return false;
			}
		}
		return true;
	}
}
