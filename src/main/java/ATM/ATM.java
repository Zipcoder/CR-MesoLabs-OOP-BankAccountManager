package ATM;

import ATM.accounts.Account;
import ATM.accounts.Checking;
import ATM.accounts.Investment;
import ATM.accounts.Savings;
import ATM.menus.MainMenu;
import ATM.menus.NewUserMenu;
import ATM.menus.UserMenu;
import ATM.services.AccountServices;
import ATM.services.TransactionServices;
import ATM.services.TransferServices;
import ATM.services.UserServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ATM {

    private User currentUser;

    private DB userDB;          // 0: ID 1: Last Name 2: First Name 3: cardNum 4: PW
    private DB transactionDB;   // 0: credit/debit 1: accountID 2: amount (signed) 3: timeStamp 4: description
    private DB accountDB;       // 0: accountID 1: ownerID 2: balance 3: type 4: risk/interest/null (type-dependent)

    private UserServices userServices;
    private TransactionServices transactionServices;
    private AccountServices accountServices;


    public ATM(String userDBName, String accountDBName, String transactionDBName) {
        this.currentUser = null;
        try {
            this.userDB = new DB(userDBName, 5);
            this.transactionDB = new DB(transactionDBName, 5);
            this.accountDB = new DB(accountDBName, 6);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.userServices = new UserServices(userDB, this);
        this.transactionServices = new TransactionServices(transactionDB, this);
        this.accountServices = new AccountServices(accountDB, this);
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public DB getUserDB() {
        return this.userDB;
    }

    public DB getTransactionDB() {
        return this.transactionDB;
    }

    public DB getAccountDB() {
        return this.accountDB;
    }

    public UserServices getUserServices() {
        return userServices;
    }

    public TransactionServices getTransactionServices() {
        return transactionServices;
    }

    public AccountServices getAccountServices() {
        return accountServices;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    public void serviceLoop() {
        this.transactionServices.linkServices();
        this.accountServices.linkServices();
        //this.userServices.linkServices();

        new UserMenu(this).displayMenu();

        interestRateChange();
        applyInterest();
        applyReturns();

        new MainMenu(this).displayMenu();

        logOut();

        serviceLoop();
    }

    public void applyInterest() {
        ArrayList<Account> userAccounts = accountServices.getAccountsForUser(this.currentUser);
        for (Account account : userAccounts) {
            if (account instanceof Savings) {
                calcInterest(account);
            }
        }
    }

    public void interestRateChange() {
        ArrayList<Account> userAccounts = accountServices.getAccountsForUser(this.currentUser);
        Random random = new Random();

        for (Account account : userAccounts) {
            if (account instanceof Savings) {
                if (random.nextInt(5) >= 4) {
                    double newRate = getNewRate(random, (Savings) account);
                    setNewInterestRate(account, newRate);
                }
            }
        }
    }

    private double getNewRate(Random random, Savings account) {
        double newRate = account.getInterestRate() - .05 + .01 * random.nextInt(11);
        if (newRate <= 0.0) {
            newRate = 0.01;
        }
        return newRate;
    }

    private void setNewInterestRate(Account account, double newRate) {
        ((Savings) account).setInterestRate(newRate);
        accountServices.saveAccountToDB(account);
        Transaction transaction = new Transaction(Double.parseDouble(String.format("%.2f",account.getBalance())), new Date(), account.getAcctNum(), String.format("Interest rate changed to 0%.2f",newRate), true);
        transactionServices.saveTransactionToDB(transaction);
    }


    public void calcInterest(Account account) {
        Double interest = ((Savings) account).getInterestRate() * account.getBalance()/100;
        account.deposit(interest);
        accountServices.saveAccountToDB(account);
        Transaction transaction = new Transaction(Double.parseDouble(String.format("%.2f",interest)), new Date(), account.getAcctNum(), "Interest earned", true);
        transactionServices.saveTransactionToDB(transaction);
    }

    public void applyReturns() {
        ArrayList<Account> userAccounts = accountServices.getAccountsForUser(this.currentUser);
        for (Account account : userAccounts) {
            if (account instanceof Investment) {
                calcReturns(account);
            }
        }
    }

    public void calcReturns(Account account) {
        Double multiplier = ((Investment) account).getRisk() * (2 * Math.random() - .8);
        Double earnings =  Math.round((multiplier * account.getBalance()*100d))/100d;
        account.deposit(earnings);
        accountServices.saveAccountToDB(account);
        Boolean isCredit = (earnings > 0);
        Transaction transaction = new Transaction(Double.parseDouble(String.format("%.2f",earnings)), new Date(), account.getAcctNum(), "Investment returns", isCredit);
        transactionServices.saveTransactionToDB(transaction);
    }


    // log out user
    public void logOut() {
        //saveDBs();
        this.currentUser = null;
    }


    /*  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    * DB interaction methods for the ATM
    *
    * We should create a storage class or generic methods in the ATM.DB class or something in the interface, but...
     */ ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public int getUserCount() {
//        return this.userDB.length();
//    }
//
//    //find accounts by owner id (to then be used by constructor)
//    public int[] getAccountRowsByUser (User user) {
//        int [] recordRowNums;
//        recordRowNums = this.accountDB.findPartialRowMultiple(new String[] {user.getUserID().toString()}, new int[] {1});
//
//        return recordRowNums;
//    }
//
//    // get string representation of one account
//    public String[] getAccountInfoByRow (int rowNum) {
//        return this.accountDB.readRow(rowNum);
//    }
//
//    // account instance from info (pre-existing account)
//    public Account getAccountByInfo (String[] info) {
//        if (info[3].equals("accounts.Checking")) {
//            return new Checking(Double.parseDouble(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]));
//        } else if (info[3].equals("accounts.Savings")) {
//            return new Savings(Double.parseDouble(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]), Double.parseDouble(info[4]));
//        } else if (info[3].equals("accounts.Investment")) {
//            return new Investment(Double.parseDouble(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]), Double.parseDouble(info[4]));
//        }
//        return null;
//    }
//
//    // AL of accounts for a user
//    public ArrayList<Account> getAccountsForUser(User user) {
//        int[] rows = getAccountRowsByUser(user);
//        ArrayList<Account> accounts = new ArrayList<>();
//        for (int row : rows) {
//            accounts.add(getAccountByInfo(getAccountInfoByRow(row)));
//        }
//        return accounts;
//    }
//
//    public int getMaxUserNumber() {
//        ArrayList<String[]> userInfo = new ArrayList<>();
//        userInfo = this.userDB.readAllRows();
//        int maxID = 0;
//        for (String[] user : userInfo) {
//            if (Integer.parseInt(user[0]) > maxID) {
//                maxID = Integer.parseInt(user[0]);
//            }
//        }
//        return maxID;
//    }
//
//    public int getMaxAccountNumber() {
//        ArrayList<String[]> accountInfo = new ArrayList<>();
//        accountInfo = this.accountDB.readAllRows();
//        int maxID = 0;
//        for (String[] account : accountInfo) {
//            if (Integer.parseInt(account[0]) > maxID) {
//                maxID = Integer.parseInt(account[0]);
//            }
//        }
//        return maxID;
//    }
//
//    //find user row by id
//    public Integer getUserRowByID (Integer ID) {
//        return this.userDB.findPartialRow(new String[]{ID.toString()}, new int[]{0});
//    }
//
//    //find user info by id (helper for constructor)
//    public String [] getUserInfoByID (Integer ID) {
//        int rowNumOfUser = this.userDB.findPartialRow(new String[] {ID.toString()}, new int[] {0});
//        return this.userDB.readRow(rowNumOfUser);
//    }
//
//    //find user info by card number (helper for constructor)
//    public String [] getUserInfoByCardNum (Integer cardNum) {
//        int rowNumOfUser = this.userDB.findPartialRow(new String[] {cardNum.toString()}, new int[] {3});
//        return this.userDB.readRow(rowNumOfUser);
//    }
//
//    //find account row by id
//    public Integer getAccountRowByID (Integer ID) {
//        return this.accountDB.findPartialRow(new String[]{ID.toString()}, new int[]{0});
//    }
//
//    //find account info by id (helper for constructor)
//    public String [] getAccountInfoByID (Integer ID) {
//        int rowNumOfAccount = this.accountDB.findPartialRow(new String[] {ID.toString()}, new int[] {0});
//        return this.accountDB.readRow(rowNumOfAccount);
//    }
//
//    public void saveUserToDB(User user) {
//        String[] stringRepOfUser = user.toStringArray();
//        int userID = user.getUserID();
//        int rowNum = getUserRowByID(userID);
//        if (rowNum == -1) { // user isn't in ATM.DB yet
//            this.userDB.addRow(stringRepOfUser);
//        } else { // update a found row
//            this.userDB.replaceRow(rowNum, stringRepOfUser);
//        }
//    }
//
//    public void saveAccountToDB(Account account) {
//        String[] stringRepOfAccount = account.toStringArray();
//        int accountNum = account.getAcctNum();
//        int rowNum = getAccountRowByID(accountNum);
//        if (rowNum == -1) { // account isn't in ATM.DB yet
//            this.accountDB.addRow(stringRepOfAccount);
//        } else { // update a found row
//            this.accountDB.replaceRow(rowNum, stringRepOfAccount);
//        }
//    }
//
//    public void deleteAccountFromDB(Account account) {
//        String[] stringRepOfAccount = account.toStringArray();
//        int accountNum = account.getAcctNum();
//        int rowNum = getAccountRowByID(accountNum);
//        if (rowNum == -1) { // account isn't in ATM.DB yet
//            this.accountDB.addRow(stringRepOfAccount);
//            return;
//        } else { // update a found row
//            this.accountDB.deleteRow(rowNum);
//        }
//    }
//
//    public int[] getTransactionRowsByUser (User user) {
//        int[] accountRows =  getAccountRowsByUser(user);
//        ArrayList<Integer> accountNums = new ArrayList<>();
//        for (int row : accountRows) {
//            accountNums.add(Integer.parseInt(getAccountInfoByRow(row)[0]));
//        }
//
//        ArrayList<Integer> rows = new ArrayList<>();
////        int [] recordRowNums = null;
////        for (int accountNum : accountNums) {
////            recordRowNums = this.transactionDB.findPartialRowMultiple(new String[]{Integer.toString(accountNum)}, new int[]{1});
////
////        }
//        ArrayList<String[]> transData = transactionDB.readAllRows();
//
//        for (int i = 0; i < transData.size(); i++) {
//            for (int acctNum : accountNums) {
//                if ((int) Integer.parseInt(transData.get(i)[1]) == acctNum) {
//                    rows.add(i);
//                }
//            }
//        }
//
//        int[] results = new int[rows.size()];
//        for (int i = 0; i < rows.size(); i++) {
//            results[i] = rows.get(i);
//        }
//
//        return results;
//    }
//
//    public int[] getTransactionRowsByAccount (Account account) {
//        return this.transactionDB.findPartialRowMultiple(new String[]{Integer.toString(account.getAcctNum())}, new int[]{1});
//    }
//
//    // get string array representation of one transaction
//    public String[] getTransactionInfoByRow (int rowNum) {
//        return this.transactionDB.readRow(rowNum);
//    }
//
//    public ArrayList<Transaction> getTransactionsForUser(User user) {
//        return getTransactionsForRows(getTransactionRowsByUser(user));
//    }
//
//    public ArrayList<Transaction> getTransactionsForAccount(Account account) {
//        return getTransactionsForRows(getTransactionRowsByAccount(account));
//    }
//
//    public ArrayList<Transaction> getTransactionsForRows(int[] rows) {
//        ArrayList<Transaction> transactions = new ArrayList<>();
//        String[] info = new String[5];
//        for (int row : rows) {
//            info = getTransactionInfoByRow(row);
//            try {
//                transactions.add(new Transaction(
//                        Double.parseDouble(info[2]),
//                        new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(info[3]),
//                        Integer.parseInt(info[1]),
//                        info[4],
//                        info[0].equals("credit")));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return transactions;
//    }
//
//
//
//    public void savePendingTransactionsToDB(ArrayList<Transaction> pendingTransactions) {
//        for (Transaction transaction : pendingTransactions) {
//            this.transactionDB.addRow(transaction.toStringArray());
//        }
//    }
//
//    public void saveTransactionToDB(Transaction transaction) {
//        this.transactionDB.addRow(transaction.toStringArray());
//    }

    /*  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * End DB interaction methods for the ATM
     */ ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
