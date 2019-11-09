import java.util.ArrayList;
import java.util.Date;

public class TransferServices {

    private User currentUser;

//    private DB userDB;          // 0: ID 1: Last Name 2: First Name 3: cardNum 4: PW
//    private DB transactionDB;   // 0: credit/debit 1: accountID 2: amount (signed) 3: timeStamp 4: description
//    private DB accountDB;       // 0: accountID 1: ownerID 2: balance 3: type 4: risk/interest/null (type-dependent)
    private String acctStatus;
    private ATM atm;



    public TransferServices(User currentUser) {
        this.currentUser = currentUser;
    }




    public Double getBalance(){
        return balance;
    }

    public Integer getOwnerID() {
        return this.ownerID;
    }

    public Integer getAcctNum() {
        return this.acctNum;
    }


    public void deposit(Double amount){
        this.balance += amount;
        String bal = String.format("%.2f",this.balance);
        this.balance = Double.parseDouble(bal);
    }

    public void withdraw(Double amount){
        if (this.balance > amount) {
            this.balance -= amount;
        }
    }
    public Boolean equals(Account account) {
        return DB.serialize(this.toStringArray()).equals(DB.serialize(account.toStringArray()));
    }

}



//
//             Console.println("Number of Account to transfer to");
//    int ActToTransferTo = Console.getInteger();
//    String[] actInfo = atm.getAccountInfoByID(ActToTransferTo);
//    // 0: accountID 1: ownerID 2: balance 3: type 4: risk/interest/null (type-dependent)
//    Account act = getAccountByInfo(actInfo);
//    deposit = Console.getCurrency("Transfer amount");
//
//                if(deposit < account.getBalance()) {
//        account.deposit(-1 * deposit);
//        act.deposit(deposit);
//
//        saveAccountToDB(account);
//        transaction = new Transaction(-1 * deposit, new Date(), account.getAcctNum(), "ATM Transfer", false);
//        saveTransactionToDB(transaction);
//
//        saveAccountToDB(act);
//        transaction = new Transaction(deposit, new Date(), act.getAcctNum(), "ATM Transfer", true);
//        saveTransactionToDB(transaction);
//    } else {
//        Console.println("Insufficient funds in account");
//    }
//
//                break;










    //ACCOUNT SERVICES


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

//    // account instance from info (pre-existing account)
//    public Account getAccountByInfo (String[] info) {
//        if (info[3].equals("Checking")) {
//            return new Checking(Double.parseDouble(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]));
//        } else if (info[3].equals("Savings")) {
//            return new Savings(Double.parseDouble(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]), Double.parseDouble(info[4]));
//        } else if (info[3].equals("Investment")) {
//            return new Investment(Double.parseDouble(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]), Double.parseDouble(info[4]));
//        }
//        return null;
//    }

    // AL of accounts for a user
//    public ArrayList<Account> getAccountsForUser(User user) {
//        int[] rows = getAccountRowsByUser(user);
//        ArrayList<Account> accounts = new ArrayList<>();
//        for (int row : rows) {
//            accounts.add(getAccountByInfo(getAccountInfoByRow(row)));
//        }
//        return accounts;
//    }




