package ATM.menus;

import ATM.ATM;
import ATM.accounts.Investment;
import ATM.accounts.Savings;
import ATM.services.AccountServices;
import ATM.services.TransferServices;
import ATM.User;
import ATM.interfaces.Menu;
import ATM.ATM;
import ATM.accounts.Account;
import ATM.Console;
import java.util.ArrayList;
import java.util.Date;


public class TransferServicesMenu implements Menu {

    private String name = "Transfer Menu";
    private ATM atm;
    private Account sourceAccount;
    private AccountServices accountServices;
    private TransferServices transferServices;
    private User currentUser;

    public TransferServicesMenu(ATM atm, Account sourceAccount) {
        this.atm = atm;
        this.sourceAccount = sourceAccount;
        this.accountServices = this.atm.getAccountServices();
        this.transferServices = new TransferServices(this.atm, sourceAccount);
        this.currentUser = this.atm.getCurrentUser();
    }

    public void displayMenu() {
        String header = getHeader();

        ArrayList<String> choices = new ArrayList<>();
        choices = addAccountOptions(choices);
        choices.add("Exit");

        handleChoice(Console.getInput(header, choices.toArray(new String[choices.size()])));
    }

    public String getHeader() {
        String header = "Transfer from: " + sourceAccount.getClass().getSimpleName() + " Account #" + sourceAccount.getAcctNum().toString() + "  Balance: $" + String.format("%,.2f", sourceAccount.getBalance());
        if (sourceAccount instanceof Savings) {
            header += "  Interest Rate: " + String.format("%.2f", ((Savings) sourceAccount).getInterestRate()) + "%";
        } else if (sourceAccount instanceof Investment) {
            header += "  Risk: " + String.format("%d", Math.round(100 * ((Investment) sourceAccount).getRisk())) + "/10";
        }
        return header;
    }

    public ArrayList<String> addAccountOptions(ArrayList<String> choices) {
        // auto-generate account choices
        String nextAcctChoice;
        ArrayList<Account> usrAccts = getDestinationAccounts();
        for (int i = 0; i < usrAccts.size(); i++) {
            nextAcctChoice = String.format("%s #%d ($%,.2f)", usrAccts.get(i).getClass().getSimpleName(), usrAccts.get(i).getAcctNum(), usrAccts.get(i).getBalance());
            choices.add(nextAcctChoice);
        }
        return choices;
    }

    public ArrayList<Account> getDestinationAccounts() {
        ArrayList<Account> userAccounts = this.accountServices.getAccountsForUser(this.currentUser);
        userAccounts.remove(this.sourceAccount);
        return userAccounts;
    }

    public void handleChoice(int choice) {
        ArrayList<Account> usrAccts = getDestinationAccounts();
        if (choice == usrAccts.size() + 3) { // exit transfer menu
            // drop though to account menu
        } else { // deal with an existing account
            //new AccountMenu(this.atm, usrAccts.get(choice - 3)).displayMenu();
            double amount = Console.getCurrency("Amount to transfer: ");
            //transferServices.transfer(this.sourceAccount, usrAccts.get(choice - 1), amount);
            displayMenu();
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

}