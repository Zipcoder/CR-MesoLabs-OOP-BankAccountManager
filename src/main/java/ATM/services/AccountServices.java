package ATM.services;

import ATM.ATM;
import ATM.DB;
import ATM.Exceptions.BalanceRemainingException;
import ATM.Exceptions.FrozenAccountException;
import ATM.Exceptions.InsufficientFundsException;
import ATM.Transaction;
import ATM.User;
import ATM.accounts.Account;
import ATM.accounts.Checking;
import ATM.accounts.Investment;
import ATM.accounts.Savings;
import ATM.Console;

import java.util.ArrayList;
import java.util.Date;

public class AccountServices {

    // 0: accountID 1: ownerID 2: balance 3: type 4: risk/interest/null (type-dependent) 5: status (OPEN/CLOSED/OFAC)
    private DB accountDB;
    private ATM atm;
    private TransactionServices transactionServices;
    private Account accountType;

    public AccountServices(DB accountDB, ATM atm) {
        this.accountDB = accountDB;
        this.atm = atm;
        this.transactionServices = atm.getTransactionServices();
    }

    public void addAccount(ArrayList<Account> usrAccounts, Double deposit, User currentUser) {
        String header = "Choose Account Type:";
        int input = Console.getInput(header, new String[]{"Checking", "Savings", "Investment", "Back to Main Menu"});
        Account newAccount;
        Transaction transaction;


        switch (input) {
            case 1:
                newAccount = new Checking(deposit, currentUser.getUserID(), (int) (Math.random() * 1000), Account.Status.valueOf("OPEN"));
                this.saveAccountToDB(newAccount);
                usrAccounts.add(newAccount);

                transaction = new Transaction(deposit, new Date(), newAccount.getAcctNum(), "Opened account", true);
                transactionServices.saveTransactionToDB(transaction);
                break;
            case 2:
                Double interestRate = .01 * (1 + Math.floor(deposit / 1000));
                Console.println(String.format("Your interest rate: %.2f", interestRate) + "%%");
                newAccount = new Savings(deposit, currentUser.getUserID(), (int) (Math.random() * 1000), interestRate, Account.Status.valueOf("OPEN"));
                this.saveAccountToDB(newAccount);
                usrAccounts.add(newAccount);

                transaction = new Transaction(deposit, new Date(), newAccount.getAcctNum(), "Opened account", true);
                this.transactionServices.saveTransactionToDB(transaction);
                break;
            case 3:
                Console.print("On a scale of 1-10, enter your risk tolerance ");
                int riskInput = Console.getInteger(10);
                Double risk = riskInput * .01;
                newAccount = new Investment(deposit, currentUser.getUserID(), (int) (Math.random() * 1000), risk, Account.Status.valueOf("OPEN"));
                this.saveAccountToDB(newAccount);
                usrAccounts.add(newAccount);

                transaction = new Transaction(deposit, new Date(), newAccount.getAcctNum(), "Opened account", true);
                this.transactionServices.saveTransactionToDB(transaction);
                break;
            case 4:
                break;
        }
    }

    public int getMaxAccountNumber() {
        ArrayList<String[]> accountInfo = new ArrayList<>();
        accountInfo = this.accountDB.readAllRows();
        int maxID = 0;
        for (String[] account : accountInfo) {
            if (Integer.parseInt(account[0]) > maxID) {
                maxID = Integer.parseInt(account[0]);
            }
        }
        return maxID;
    }

    public int[] getAccountRowsByUser(User user) {
        int[] recordRowNums;
        recordRowNums = this.accountDB.findPartialRowMultiple(new String[]{user.getUserID().toString()}, new int[]{1});

        return recordRowNums;
    }

    public String[] getAccountInfoByRow(int rowNum) {
        return this.accountDB.readRow(rowNum);
    }

    // account instance from info (pre-existing account)
    public Account getAccountByInfo(String[] info) {
        if (info[3].equals("Checking")) {
            return new Checking(Double.parseDouble(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]), Account.Status.valueOf(info[5]));
        } else if (info[3].equals("Savings")) {
            return new Savings(Double.parseDouble(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]), Double.parseDouble(info[4]), Account.Status.valueOf(info[5]));
        } else if (info[3].equals("Investment")) {
            return new Investment(Double.parseDouble(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]), Double.parseDouble(info[4]), Account.Status.valueOf(info[5]));
        }
        return null;
    }

    //find account row by id
    public Integer getAccountRowByID(Integer ID) {
        return this.accountDB.findPartialRow(new String[]{ID.toString()}, new int[]{0});
    }

    //find account info by id (helper for constructor)
    public String[] getAccountInfoByID(Integer ID) {
        int rowNumOfAccount = this.accountDB.findPartialRow(new String[]{ID.toString()}, new int[]{0});
        return this.accountDB.readRow(rowNumOfAccount);
    }

    // AL of accounts for a user
    public ArrayList<Account> getAccountsForUser(User user) {
        int[] rows = getAccountRowsByUser(user);
        ArrayList<Account> accounts = new ArrayList<>();
        for (int row : rows) {
            accounts.add(getAccountByInfo(getAccountInfoByRow(row)));
        }
        return accounts;
    }

    public void saveAccountToDB(Account account) {
        String[] stringRepOfAccount = account.toStringArray();
        int accountNum = account.getAcctNum();
        int rowNum = getAccountRowByID(accountNum);
        if (rowNum == -1) { // account isn't in DB yet
            this.accountDB.addRow(stringRepOfAccount);
        } else { // update a found row
            this.accountDB.replaceRow(rowNum, stringRepOfAccount);
        }
    }

    public DB getAccountDB() {
        return this.accountDB;
    }

    public int getAccountDBLength() {
        return accountDB.length();
    }

    public void clearAccountDB() {
        accountDB.clear();
    }

    public void deleteAccountFromDB(Account account) {
        String[] stringRepOfAccount = account.toStringArray();
        int accountNum = account.getAcctNum();
        int rowNum = getAccountRowByID(accountNum);
        if (rowNum == -1) { // account isn't in DB yet
            this.accountDB.addRow(stringRepOfAccount);
            return;
        } else { // update a found row
            this.accountDB.deleteRow(rowNum);
        }
    }


    public Boolean closeAccount(Account account) throws BalanceRemainingException, FrozenAccountException, InsufficientFundsException {
        boolean status = true;
        if (account.getBalance() == 0) {
            deleteAccountFromDB(account);
            Transaction transaction = new Transaction(0.0, new Date(), account.getAcctNum(), "Account Closed", false);
            transactionServices.saveTransactionToDB(transaction);
            status = true;
        } else {
            Console.println("Account still contains funds. Withdraw or transfer all funds before closing.");
            Console.getInput("\nPress Enter");
            status = false;
        }
        return status;
    }


    public Boolean accountDeposit(Account account, double amount) throws BalanceRemainingException, FrozenAccountException {
        saveAccountToDB(account);
        Transaction transaction = new Transaction(amount, new Date(), account.getAcctNum(), "ATM deposit", true);
        transactionServices.saveTransactionToDB(transaction);
        saveAccountToDB(account);
        return false;
    }


    public Boolean accountWithdraw(Account account, double amount) throws BalanceRemainingException, FrozenAccountException, InsufficientFundsException {
        amount = Console.getCurrency("Withdrawal amount: ");
        if (amount <= account.getBalance()) {
            account.deposit(-1 * amount);
            saveAccountToDB(account);
            Transaction transaction = new Transaction(amount, new Date(), account.getAcctNum(), "ATM withdrawal", false);
            transactionServices.saveTransactionToDB(transaction);
        } else {
            Console.println("Insufficient funds");
            Console.getInput("\nPress Enter");
        }
        return false;

/*    account.deposit(deposit);

            saveAccountToDB(account);

            transaction =new

            Transaction(deposit, new Date(),account.

            getAcctNum(), "ATM deposit",true);

            saveTransactionToDB(transaction);
            break;
            case"3":
            deposit =Console.getCurrency("Withdrawal amount: ");
            if(deposit <=account.getBalance())

            {
            account.deposit(-1 * deposit);
            saveAccountToDB(account);
            transaction = new Transaction(deposit, new Date(), account.getAcctNum(), "ATM withdrawal", false);
            saveTransactionToDB(transaction);
            } else

            {
            Console.println("Insufficient funds");
            Console.getInput("\nPress Enter");
            }*/
    }
}



