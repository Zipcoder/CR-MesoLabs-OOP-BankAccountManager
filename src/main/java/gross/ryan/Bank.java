package gross.ryan;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ryangross on 1/17/17.
 */
public class Bank {

    protected HashMap<String,BankAccount> allAccounts = new HashMap<String, BankAccount>();

    protected void addAccount(String name, String type, double number) {
        BankAccount anAcct = new BankAccount(name, type,number);
        allAccounts.put(name, anAcct);
    }

    protected BankAccount getAccount(String name) {
        if (allAccounts.containsKey(name)) {
            return allAccounts.get(name);
        }
      return null;
    }
}
