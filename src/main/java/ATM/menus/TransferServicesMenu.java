package ATM.menus;

import ATM.ATM;
import ATM.Exceptions.ClosedAccountException;
import ATM.Exceptions.FrozenAccountException;
import ATM.Exceptions.InsufficientFundsException;
import ATM.accounts.Investment;
import ATM.accounts.Savings;
import ATM.services.AccountServices;
import ATM.services.TransferServices;
import ATM.User;
import ATM.interfaces.Menu;
import ATM.accounts.Account;
import ATM.Console;
import java.util.ArrayList;
import java.util.Collections;


public class TransferServicesMenu implements Menu {

    private String name = "Transfer Menu";
    private ATM atm;
    private Account sourceAccount;
    private AccountServices accountServices;
    private TransferServices transferServices;
    private ArrayList<Account> userAccounts;
    private User currentUser;

    public TransferServicesMenu(ATM atm, Account sourceAccount, ArrayList<Account> userAccounts) throws ClosedAccountException, FrozenAccountException {
        this.atm = atm;
        this.sourceAccount = sourceAccount;
        if (this.sourceAccount.getAcctStatus() == Account.Status.CLOSED) {
            throw new ClosedAccountException();
        } else if (this.sourceAccount.getAcctStatus() == Account.Status.OFAC) {
            throw new FrozenAccountException();
        }
        this.accountServices = this.atm.getAccountServices();
        this.transferServices = new TransferServices(this.atm, sourceAccount);
        this.currentUser = this.atm.getCurrentUser();
        this.userAccounts = userAccounts;
    }

    // needs input - no test
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
            header += "  Interest Rate: " + String.format("%.2f", ((Savings) sourceAccount).getInterestRate()) + "%%";
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
        ArrayList<Account> userAccountsTrimmed = new ArrayList<Account>();

        for (Account account : this.userAccounts) {
            if (!account.getAcctNum().equals(this.sourceAccount.getAcctNum())) {
                userAccountsTrimmed.add(account);
            }
        }
        return userAccountsTrimmed;
    }

    // needs input - no test (underlying method is tested)
    public void handleChoice(int choice) {
        ArrayList<Account> usrAccts = getDestinationAccounts();
        if (choice == usrAccts.size() - 2) { // exit transfer menu
            // drop though to account menu
        } else { // deal with an existing account
            double amount = Console.getCurrency("Amount to transfer: ");
            try {
                transferServices.executeTransfer(this.sourceAccount, usrAccts.get(choice - 1), amount);
            } catch (ClosedAccountException e) {
                Console.getInput("Error - cannot transfer to/from a closed account. Press Enter to continue");
            } catch (InsufficientFundsException e) {
                Console.getInput("Error - insufficient funds. Press Enter to continue");
            } catch (FrozenAccountException e) {
                Console.getInput("Error - cannot transfer to/from a frozen account. Press Enter to continue");
            }

        }
    }

    @Override
    public String getName() {
        return this.name;
    }

}