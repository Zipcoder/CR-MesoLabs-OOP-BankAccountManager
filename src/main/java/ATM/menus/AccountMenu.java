package ATM.menus;

import ATM.ATM;
import ATM.Console;
import ATM.Menu;
import ATM.User;
import ATM.Transaction;
import ATM.accounts.Account;
import ATM.accounts.Checking;
import ATM.accounts.Investment;
import ATM.accounts.Savings;
import ATM.services.AccountServices;
import ATM.services.TransactionServices;

import java.util.Date;

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
                accountServices.closeAccount(account);
                // TODO: take this code for closeAccount(Account account);
//                if (account.getBalance() == 0) {
//
//                    accountServices.deleteAccountFromDB(account);
//                    transaction = new Transaction(0.0, new Date(), account.getAcctNum(), "Account Closed", false);
//                    transactionServices.saveTransactionToDB(transaction);
//                } else {
//                    Console.println("Account still contains funds. Withdraw or transfer all funds before closing.");
//                    Console.getInput("\nPress Enter");
//                }
                break;
            case 5:
                new TransferServicesMenu(this.atm, account);
                // TODO: take this code for account transfers
//                Console.println("Number of Account to transfer to");
//                int ActToTransferTo = Console.getInteger();
//                String[] actInfo = accountServices.getAccountInfoByID(ActToTransferTo);
//                // 0: accountID 1: ownerID 2: balance 3: type 4: risk/interest/null (type-dependent)
//                Account act = accountServices.getAccountByInfo(actInfo);
//                deposit = Console.getCurrency("Transfer amount");
//
//                if(deposit < account.getBalance()) {
//                    account.deposit(-1 * deposit);
//                    act.deposit(deposit);
//
//                    accountServices.saveAccountToDB(account);
//                    transaction = new Transaction(-1 * deposit, new Date(), account.getAcctNum(), "ATM.ATM Transfer", false);
//                    transactionServices.saveTransactionToDB(transaction);
//
//                    accountServices.saveAccountToDB(act);
//                    transaction = new Transaction(deposit, new Date(), act.getAcctNum(), "ATM.ATM Transfer", true);
//                    transactionServices.saveTransactionToDB(transaction);
//                } else {
//                    Console.println("Insufficient funds in account");
//                }

                break;
            case 6:
                break;
        }
    }

    private void attemptWithdrawal(double amount) {
        if (accountServices.withdraw(account, amount)) {
            Console.getInput("Withdrawal successful; press Enter to continue");
        } else {
        Console.getInput("Insufficient funds; press Enter to continue");
        }
    }

    private void attemptDeposit(double amount) {
        if (accountServices.deposit(account, amount)) {
            Console.getInput("Deposit successful; press Enter to continue");
        } else {
            Console.getInput("Deposit error; press Enter to continue");
        }
    }


}
