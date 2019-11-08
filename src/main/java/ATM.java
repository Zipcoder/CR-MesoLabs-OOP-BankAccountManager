import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ATM {

    private User currentUser;

    private DB userDB;          // 0: ID 1: Last Name 2: First Name 3: cardNum 4: PW
    private DB transactionDB;   // 0: credit/debit 1: accountID 2: amount (signed) 3: timeStamp 4: description
    private DB accountDB;       // 0: accountID 1: ownerID 2: balance 3: type 4: risk/interest/null (type-dependent)

    public ATM(String userDBName, String accountDBName, String transactionDBName) {
        this.currentUser = null;
        try {
            this.userDB = new DB(userDBName, 5);
            this.transactionDB = new DB(transactionDBName, 5);
            this.accountDB = new DB(accountDBName, 5);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    // load database info from disk
    public void loadDBs() {
//        // find accounts, create instances
//        ArrayList<String[]> accountsInfo = getAccountInfoByUser(this.currentUser);
//        ArrayList<Account> accounts = new ArrayList<>();
//        for (String[] acctInfo : accountsInfo) {
//            accounts.add(new Account(...));
//        }
//        //
    }


    public void authenticate() {
        //Read User's card
        Console.println("Card Number:");
        int cardNum = Console.getInteger();

        // find user in DB
        String[] userInfo = this.getUserInfoByCardNum(cardNum);
        if (userInfo == null){
            this.authenticate();
        }
        // check PW
        String password = Console.getInput("Enter Password: ");
        if(password.equals(userInfo[4])) {
            // 0: ID 1: Last Name 2: First Name 3: cardNum 4: PW
            currentUser = new User(userInfo[2], userInfo[1], userInfo[4], Integer.parseInt(userInfo[0]), Integer.parseInt(userInfo[3]));
        } else {
            this.authenticate();
        }
    }

    // add new user - called by getUser()
    public User newUser() {

        String firstName = Console.getInput("Enter Your First Name: ");
        String lastName = Console.getInput("Enter Your Last Name: ");
        String password = Console.getInput("Choose Your Password: ");

        Integer cardNumber = User.genCardNum();
        Console.println("Your Card Number: " + cardNumber + "\n");

        Integer userID = (int) (Math.random() * 1000);


        User newUser = new User(firstName, lastName, password, userID, cardNumber);
        currentUser = newUser;
        this.saveUserToDB(currentUser);

        return newUser;
    }

    // log in user - don't return until you do
    public void getUser() {
        String header = "Welcome to ZipCode National Bank";
        String input = Console.getInput(header, new String[] {"Insert Card", "Open an Account"});

        switch (input) {
            case "1":
                this.authenticate();
                if (this.currentUser == null) {
                    return;
                }
                break;
            case "2":
                this.newUser();
                break;
        }
    }

    // deal with the user's choices
    public void userMenu() {
        String header = "ZCNB Main Menu";

        ArrayList<String> choices = new ArrayList<>();
        choices.add("Transaction History");
        choices.add("Add Account");

        String nextAcctChoice;
        ArrayList<Account> usrAccts = getAccountsForUser(currentUser);
        for (int i = 0; i < usrAccts.size(); i++) {
            nextAcctChoice = String.format("%s #%d ($%,.2f)", usrAccts.get(i).getClass().getName(), usrAccts.get(i).getAcctNum(), usrAccts.get(i).getBalance());
            choices.add(nextAcctChoice);
        }

        choices.add("Log Out");

        String input = Console.getInput(header, choices.toArray(new String[choices.size()]));

        if (input.equals(Integer.toString(choices.size()))) {
            serviceLoop(); //not ... great, but it'll do for now
        } else if (input.equals("1")) {
            Console.outputTransactionsWithHeader("Transaction History", getTransactionsForUser(this.currentUser));
        } else if (input.equals("2")) {
            Double deposit = Console.getCurrency("Initial deposit amount for this account: ");
            addAccount(usrAccts, deposit);
        } else {
            accountMenu(usrAccts.get(Integer.parseInt(input) - 3));
        }

        userMenu();
    }

    public void addAccount(ArrayList<Account> usrAccounts, Double deposit) {
        String header = "Choose Account Type:";
        String input = Console.getInput(header, new String[] {"Checking", "Savings", "Investment", "Back to Main Menu" });
        Account newAccount;
        Transaction transaction;


        switch (input) {
            case "1":
                newAccount = new Checking(deposit, this.currentUser.getUserID(), (int)(Math.random()*1000));
                this.saveAccountToDB(newAccount);
                usrAccounts.add(newAccount);

                transaction = new Transaction(deposit, new Date(), newAccount.getAcctNum(), "Opened account", true);
                saveTransactionToDB(transaction);
                break;
            case "2":
                Double interestRate = .01 * (1 + Math.floor(deposit/1000));
                Console.println(String.format("Your interest rate: %.2f", interestRate)+"%%");
                newAccount = new Savings(deposit, this.currentUser.getUserID(), (int)(Math.random()*1000), interestRate);
                this.saveAccountToDB(newAccount);
                usrAccounts.add(newAccount);

                transaction = new Transaction(deposit, new Date(), newAccount.getAcctNum(), "Opened account", true);
                saveTransactionToDB(transaction);
                break;
            case "3":
                Console.print("On a scale of 1-10, enter your risk tolerance ");
                int riskInput = Console.getInteger(10);
                Double risk = riskInput * .01;
                newAccount = new Investment(deposit, this.currentUser.getUserID(), (int)(Math.random()*1000), risk);
                this.saveAccountToDB(newAccount);
                usrAccounts.add(newAccount);

                transaction = new Transaction(deposit, new Date(), newAccount.getAcctNum(), "Opened account", true);
                saveTransactionToDB(transaction);
                break;
            case "4":
                break;
        }


    }

    public void accountMenu(Account account) {
        String header = account.getClass().getName() + " Account #" + account.getAcctNum().toString() + "  Balance: $" + String.format("%,.2f", account.getBalance());
        if (account instanceof Savings) {
            header += "  Interest Rate: " + String.format("%.2f", ((Savings) account).getInterestRate())+"%%";
        } else if (account instanceof Investment) {
            header += "  Risk: " + String.format("%d", Math.round(100*((Investment) account).getRisk()))+"/10";
        }
        String input = Console.getInput(header, new String[] {"View Transaction History", "Deposit", "Withdrawal", "Close Account", "Transfer", "Back to Main Menu" });

        Double deposit;
        Transaction transaction;
        switch (input) {
            case "1":
                Console.outputTransactionsWithHeader("Transaction History", getTransactionsForAccount(account));
                break;
            case "2":
                deposit = Console.getCurrency("Deposit amount: ");
                account.deposit(deposit);
                saveAccountToDB(account);
                transaction = new Transaction(deposit, new Date(), account.getAcctNum(), "ATM deposit", true);
                saveTransactionToDB(transaction);
                break;
            case "3":
                deposit = Console.getCurrency("Withdrawal amount: ");
                if (deposit <= account.getBalance()) {
                    account.deposit(-1 * deposit);
                    saveAccountToDB(account);
                    transaction = new Transaction(deposit, new Date(), account.getAcctNum(), "ATM withdrawal", false);
                    saveTransactionToDB(transaction);
                } else {
                    Console.println("Insufficient funds");
                    Console.getInput("\nPress Enter");
                }
                break;
            case "4":

                if (account.getBalance() == 0) {

                    deleteAccountFromDB(account);
                    transaction = new Transaction(0.0, new Date(), account.getAcctNum(), "Account Closed", false);
                    saveTransactionToDB(transaction);
                } else {
                    Console.println("Account still contains funds. Withdraw or transfer all funds before closing.");
                    Console.getInput("\nPress Enter");
                }
                break;
            case "5":

                Console.println("Number of Account to transfer to");
                int ActToTransferTo = Console.getInteger();
                String[] actInfo = getAccountInfoByID(ActToTransferTo);
                // 0: accountID 1: ownerID 2: balance 3: type 4: risk/interest/null (type-dependent)
                Account act = getAccountByInfo(actInfo);
                deposit = Console.getCurrency("Transfer amount");

                if(deposit < account.getBalance()) {
                    account.deposit(-1 * deposit);
                    act.deposit(deposit);

                    saveAccountToDB(account);
                    transaction = new Transaction(-1 * deposit, new Date(), account.getAcctNum(), "ATM Transfer", false);
                    saveTransactionToDB(transaction);

                    saveAccountToDB(act);
                    transaction = new Transaction(deposit, new Date(), act.getAcctNum(), "ATM Transfer", true);
                    saveTransactionToDB(transaction);
                } else {
                    Console.println("Insufficient funds in account");
                }

                break;
            case "6":
                break;
        }
    }


    public void serviceLoop() {
        // authenticate a user (or die trying)
        // only returns null if the magic secret exit code is called

        getUser();
        applyInterest();
        applyReturns();

        loadDBs();

        userMenu();

        logOut();

        serviceLoop();
    }

    public void applyInterest() {
        ArrayList<Account> userAccounts = getAccountsForUser(this.currentUser);
        for (Account account : userAccounts) {
            if (account instanceof Savings) {
                calcInterest(account);
            }
        }
    }

    public void calcInterest(Account account) {
        Double interest = ((Savings) account).getInterestRate() * account.getBalance()/100;
        account.deposit(interest);
        saveAccountToDB(account);
        Transaction transaction = new Transaction(Double.parseDouble(String.format("%.2f",interest)), new Date(), account.getAcctNum(), "Interest earned", true);
        saveTransactionToDB(transaction);
    }

    public void applyReturns() {
        ArrayList<Account> userAccounts = getAccountsForUser(this.currentUser);
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
        saveAccountToDB(account);
        Boolean isCredit = (earnings > 0);
        Transaction transaction = new Transaction(Double.parseDouble(String.format("%.2f",earnings)), new Date(), account.getAcctNum(), "Investment returns", isCredit);
        saveTransactionToDB(transaction);
    }


    // log out user
    public void logOut() {
        saveDBs();
        this.currentUser = null;
    }

    // save DBs to disk
    public void saveDBs() {
//        // write the pending transaction queue
//        for (Transaction transaction : this.currentUser.pendingTransactions) {
//            transactionDB.addRow(transaction.toStringArray());
//        }
//        // write the accounts
//        int row;
//        for (Account account : this.currentUser.accounts) {
//            // find account row, replace it
//            row =
//            this.accountDB.replaceRow(accountDB.findPartialRow(), account.toString());
//
//        }
    }

//    public void showTransactions(Transaction[] transactions) {
//        String[][] rows = new String[5][transactions.length];
//        for (int i = 0; i < transactions.length; i++) {
//            rows[i] = transactions[i].toStringArray();
//        }
//        Console.outputTransactionsWithHeader("Transaction History", rows);
//    }


    /*  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    * DB interaction methods for the ATM
    *
    * We should create a storage class or generic methods in the DB class or something in the interface, but...
     */ ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getUserCount() {
        return this.userDB.length();
    }
    
    //find accounts by owner id (to then be used by constructor)
    public int[] getAccountRowsByUser (User user) {
        int [] recordRowNums;
        recordRowNums = this.accountDB.findPartialRowMultiple(new String[] {user.getUserID().toString()}, new int[] {1});

        return recordRowNums;
    }

    // get string representation of one account
    public String[] getAccountInfoByRow (int rowNum) {
        return this.accountDB.readRow(rowNum);
    }

    // account instance from info (pre-existing account)
    public Account getAccountByInfo (String[] info) {
        if (info[3].equals("Checking")) {
            return new Checking(Double.parseDouble(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]));
        } else if (info[3].equals("Savings")) {
            return new Savings(Double.parseDouble(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]), Double.parseDouble(info[4]));
        } else if (info[3].equals("Investment")) {
            return new Investment(Double.parseDouble(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]), Double.parseDouble(info[4]));
        }
        return null;
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

    public int getMaxUserNumber() {
        ArrayList<String[]> userInfo = new ArrayList<>();
        userInfo = this.userDB.readAllRows();
        int maxID = 0;
        for (String[] user : userInfo) {
            if (Integer.parseInt(user[0]) > maxID) {
                maxID = Integer.parseInt(user[0]);
            }
        }
        return maxID;
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

    //find user row by id
    public Integer getUserRowByID (Integer ID) {
        return this.userDB.findPartialRow(new String[]{ID.toString()}, new int[]{0});
    }

    //find user info by id (helper for constructor)
    public String [] getUserInfoByID (Integer ID) {
        int rowNumOfUser = this.userDB.findPartialRow(new String[] {ID.toString()}, new int[] {0});
        return this.userDB.readRow(rowNumOfUser);
    }

    //find user info by card number (helper for constructor)
    public String [] getUserInfoByCardNum (Integer cardNum) {
        int rowNumOfUser = this.userDB.findPartialRow(new String[] {cardNum.toString()}, new int[] {3});
        return this.userDB.readRow(rowNumOfUser);
    }

    //find account row by id
    public Integer getAccountRowByID (Integer ID) {
        return this.accountDB.findPartialRow(new String[]{ID.toString()}, new int[]{0});
    }

    //find account info by id (helper for constructor)
    public String [] getAccountInfoByID (Integer ID) {
        int rowNumOfAccount = this.accountDB.findPartialRow(new String[] {ID.toString()}, new int[] {0});
        return this.accountDB.readRow(rowNumOfAccount);
    }

    public void saveUserToDB(User user) {
        String[] stringRepOfUser = user.toStringArray();
        int userID = user.getUserID();
        int rowNum = getUserRowByID(userID);
        if (rowNum == -1) { // user isn't in DB yet
            this.userDB.addRow(stringRepOfUser);
        } else { // update a found row
            this.userDB.replaceRow(rowNum, stringRepOfUser);
        }
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

    public int[] getTransactionRowsByUser (User user) {
        int[] accountRows =  getAccountRowsByUser(user);
        ArrayList<Integer> accountNums = new ArrayList<>();
        for (int row : accountRows) {
            accountNums.add(Integer.parseInt(getAccountInfoByRow(row)[0]));
        }

        ArrayList<Integer> rows = new ArrayList<>();
//        int [] recordRowNums = null;
//        for (int accountNum : accountNums) {
//            recordRowNums = this.transactionDB.findPartialRowMultiple(new String[]{Integer.toString(accountNum)}, new int[]{1});
//
//        }
        ArrayList<String[]> transData = transactionDB.readAllRows();

        for (int i = 0; i < transData.size(); i++) {
            for (int acctNum : accountNums) {
                if ((int) Integer.parseInt(transData.get(i)[1]) == acctNum) {
                    rows.add(i);
                }
            }
        }

        int[] results = new int[rows.size()];
        for (int i = 0; i < rows.size(); i++) {
            results[i] = rows.get(i);
        }

        return results;
    }

    public int[] getTransactionRowsByAccount (Account account) {
        return this.transactionDB.findPartialRowMultiple(new String[]{Integer.toString(account.getAcctNum())}, new int[]{1});
    }

    // get string array representation of one transaction
    public String[] getTransactionInfoByRow (int rowNum) {
        return this.transactionDB.readRow(rowNum);
    }

    public ArrayList<Transaction> getTransactionsForUser(User user) {
        return getTransactionsForRows(getTransactionRowsByUser(user));
    }

    public ArrayList<Transaction> getTransactionsForAccount(Account account) {
        return getTransactionsForRows(getTransactionRowsByAccount(account));
    }

    public ArrayList<Transaction> getTransactionsForRows(int[] rows) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        String[] info = new String[5];
        for (int row : rows) {
            info = getTransactionInfoByRow(row);
            try {
                transactions.add(new Transaction(
                        Double.parseDouble(info[2]),
                        new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(info[3]),
                        Integer.parseInt(info[1]),
                        info[4],
                        info[0].equals("credit")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return transactions;
    }



    public void savePendingTransactionsToDB(ArrayList<Transaction> pendingTransactions) {
        for (Transaction transaction : pendingTransactions) {
            this.transactionDB.addRow(transaction.toStringArray());
        }
    }

    public void saveTransactionToDB(Transaction transaction) {
        this.transactionDB.addRow(transaction.toStringArray());
    }

    /*  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * End DB interaction methods for the ATM
     */ ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
