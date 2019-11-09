package ATM.menus;

import ATM.ATM;
import ATM.Console;
import ATM.Exceptions.BalanceRemainingException;
import ATM.Exceptions.ClosedAccountException;
import ATM.Exceptions.FrozenAccountException;
import ATM.Exceptions.InsufficientFundsException;
import ATM.interfaces.Menu;
import ATM.User;
import ATM.Transaction;
import ATM.accounts.Account;
import ATM.accounts.Investment;
import ATM.accounts.Savings;
import ATM.services.AccountServices;
import ATM.services.TransactionServices;

public class AccountMenu implements Menu {

    private String name = "Account Menu";
    private Account account;
    private User currentUser;
    private ATM atm;
    private TransactionServices transactionServices;
    private AccountServices accountServices;

    /**
     * Account Menu - the menu to deal with a single account
     * @param atm - ATM instance
     * @param account - the account we're dealing with
     */
    public AccountMenu(ATM atm, Account account){
        this.atm = atm;
        this.currentUser = this.atm.getCurrentUser();
        this.account = account;
        this.transactionServices = this.atm.getTransactionServices();
        this.accountServices = this.atm.getAccountServices();
    }

    public void displayMenu() {
        Console.clearScreen();

        String header = getHeader();
        int input = Console.getInput(header, new String[] {"View Transaction History", "Deposit", "Withdrawal", "Close Account", "Transfer", "Back to Main Menu" });
        handleChoice(input);
    }

    public String getHeader() {
        String header = account.getClass().getSimpleName() + " Account #" + account.getAcctNum().toString() + "  Balance: $" + String.format("%,.2f", account.getBalance());
        if (account instanceof Savings) {
            header += "  Interest Rate: " + String.format("%.2f", ((Savings) account).getInterestRate())+"%";
        } else if (account instanceof Investment) {
            header += "  Risk: " + String.format("%d", Math.round(100*((Investment) account).getRisk()))+"/10";
        }
        return header;
    }

    public String getName() {
        return this.name;
    }

    public void handleChoice(int choice) {
        double amount;
        Transaction transaction;
            switch (choice) {
            case 1:
                Console.outputTransactionsWithHeader("Transaction History", transactionServices.getTransactionsForAccount(account));
                break;
            case 2: // deposit
                amount = Console.getCurrency("Deposit amount: ");
                attemptDeposit(amount);
                break;
            case 3: // withdrawal
                amount = Console.getCurrency("Withdrawal amount: ");
                attemptWithdrawal(amount);
                break;
            case 4:
                attemptCloseAccount();
                break;
            case 5:
                new TransferServicesMenu(this.atm, account).displayMenu();
                break;
            case 6:
                break;
        }
    }

    private void attemptCloseAccount()  {
        try {
            if (accountServices.closeAccount(account)) {
                Console.getInput("Account closed; press Enter to continue");
            }
        } catch (BalanceRemainingException e) {
            Console.getInput("Unable to close account - account is not empty; press Enter to continue");
        } catch (ClosedAccountException e) {
            Console.getInput("Error - account is already closed; press Enter to continue");
        } catch (FrozenAccountException e) {
            Console.getInput("Error - account is frozen by OFAC; press Enter to continue");
        }
    }

    private void attemptWithdrawal(double amount) {
        try {
            if (accountServices.accountWithdraw(account, amount)) {
                Console.getInput("Withdrawal successful; press Enter to continue");
            }
        } catch (InsufficientFundsException e) {
            Console.getInput("Error - insufficient funds; press Enter to continue");
        } catch (ClosedAccountException e) {
            Console.getInput("Error - account is closed; press Enter to continue");
        } catch (FrozenAccountException e) {
            Console.getInput("Error - account is frozen by OFAC; press Enter to continue");
        }
    }

    private void attemptDeposit(double amount) {
        try {
            if (accountServices.accountDeposit(account, amount)) {
                Console.getInput("Deposit successful; press Enter to continue");
            }
        } catch (ClosedAccountException e) {
            Console.getInput("Error - account is closed; press Enter to continue");
        } catch (FrozenAccountException e) {
            Console.getInput("Error - account is frozen by OFAC; press Enter to continue");
        }
    }


}
