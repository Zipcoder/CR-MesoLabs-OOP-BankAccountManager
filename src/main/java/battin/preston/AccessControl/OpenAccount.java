package battin.preston.AccessControl;

import static battin.preston.AccessControl.Main.checkingAccount;
import static battin.preston.AccessControl.Main.savingsAccount;

/**
 * Created by prestonbattin on 1/17/17.
 */
public class OpenAccount {

    public static void openAccount(String choice, String name) {
        if (choice.equals("Checking")) {
            Checking account = new Checking(choice, name);
            checkingAccount.add(account);
        } else if (choice.equals("Savings")) {
            Savings account = new Savings(choice, name);
            savingsAccount.add(account);
        }
    }
}
