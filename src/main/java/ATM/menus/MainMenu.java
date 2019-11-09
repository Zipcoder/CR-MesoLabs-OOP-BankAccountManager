package ATM.menus;

import ATM.ATM;
import ATM.Console;
import ATM.Exceptions.FrozenAccountException;
import ATM.interfaces.Menu;
import ATM.accounts.Account;
import ATM.services.AccountServices;
import ATM.services.TransactionServices;
import ATM.services.UserServices;

import java.util.ArrayList;

public class MainMenu implements Menu {

    private Console console;
    private String name = "User Menu";
    private ATM atm;
    private UserServices userServices;
    private TransactionServices transactionServices;
    private AccountServices accountServices;

    /**
     * Main Menu - landing page after logging in; other menus collapse back to here
     * @param atm - ATM instance
     */
    public MainMenu(ATM atm){
        this.atm = atm;
        this.userServices = atm.getUserServices();
        this.accountServices = atm.getAccountServices();
        this.transactionServices = atm.getTransactionServices();
    }

    public void displayMenu() {
        String header = "ZCNB Main Menu";
        //maybe Younger Bank and Trust (YBT)
        //logo is giant ASCII of Kris' face

        ArrayList<String> choices = new ArrayList<>();
        choices.add("Transaction History");
        choices.add("Add Account");
        choices.add("Change Name");

        choices = addAccountOptions(choices);

        choices.add("Log Out");

        handleChoice(Console.getInput(header, choices.toArray(new String[choices.size()])));
    }

    public ArrayList<String> addAccountOptions(ArrayList<String> choices) {
        // auto-generate account choices
        String nextAcctChoice;
        ArrayList<Account> usrAccts = accountServices.getAccountsForUser(atm.getCurrentUser());
        for (int i = 0; i < usrAccts.size(); i++) {
            if (usrAccts.get(i).getAcctStatus() != Account.Status.OFAC) {
                nextAcctChoice = String.format("%s #%d ($%,.2f)", usrAccts.get(i).getClass().getSimpleName(), usrAccts.get(i).getAcctNum(), usrAccts.get(i).getBalance());
            }  else {
                nextAcctChoice = String.format("%s #%d (FROZEN)", usrAccts.get(i).getClass().getSimpleName(), usrAccts.get(i).getAcctNum(), usrAccts.get(i).getBalance());
            }
            choices.add(nextAcctChoice);
        }
        return choices;
    }

    public void handleChoice(int input) {
        ArrayList<Account> usrAccts = accountServices.getAccountsForUser(atm.getCurrentUser());
        if (input == 1) { // View overall transaction history
            Console.outputTransactionsWithHeader("Transaction History", transactionServices.getTransactionsForUser(atm.getCurrentUser()));
            displayMenu();
        } else if (input == 2) { // create a new account
            addAccountChoice();
            displayMenu();
        } else if (input == 3) { // change name
            attemptNameChange();
            displayMenu();
        }else if (input == usrAccts.size()+4) { // quit/log out
            // log out user and drop though to service loop
            atm.setCurrentUser(null);
        } else { // deal with an existing account
            try {
                new AccountMenu(this.atm, usrAccts.get(input - 3)).displayMenu();
            } catch (FrozenAccountException e) {
                Console.getInput("Error - this account is frozen by OFAC. Press Enter to continue");
            }
            displayMenu();
        }

    }

    private void attemptNameChange() {
        String firstName = Console.getInput("First name: ");
        String lastName = Console.getInput("Last name: ");
//        if (userServices.changeName(this.atm.getCurrentUser(), firstName, lastName)) {
//            Console.println("Name change successful");
//        } else {
//            Console.getInput("Name change failed. Please try again");
//        }
    }

    private void addAccountChoice() {
        Double deposit = Console.getCurrency("Initial deposit amount for this account: ");
        String header = "Choose Account Type:";
        String[] choices = new String[] {"Checking", "Savings", "Investment", "Back to Main Menu" };
        int acctTypeInput = Console.getInput(header, choices);
        if (acctTypeInput != 4) {
            //accountServices.addAccount(deposit, choices[acctTypeInput]);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
}
