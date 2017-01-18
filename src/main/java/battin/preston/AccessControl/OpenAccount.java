package battin.preston.AccessControl;

import static battin.preston.AccessControl.Main.*;

/**
 * Created by prestonbattin on 1/17/17.
 */
public class OpenAccount {

    protected static String choice, name;

    public static void getNameandAccountType() {

        System.out.println("Would you like to open a Checking or Savings account?");
        choice = input.nextLine();
        System.out.println("What is your full name?");
        name = input.nextLine();
    }

    public  void openAccount() {
        if (choice.equals("Checking")) {
            Checking account = new Checking(choice, name);
            checkingAccount.add(account);
            MainMenu.decideChecking();
        } else if (choice.equals("Savings")) {
            Savings account = new Savings(choice, name);
            savingsAccount.add(account);
            MainMenu.decideSavings();
        }
    }
}
