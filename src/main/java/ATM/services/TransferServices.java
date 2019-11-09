package ATM.services;

import ATM.accounts.Account;

import java.util.ArrayList;
import java.util.Date;

public class TransferServices {


//    private ATM.DB userDB;          // 0: ID 1: Last Name 2: First Name 3: cardNum 4: PW
//    private ATM.DB transactionDB;   // 0: credit/debit 1: accountID 2: amount (signed) 3: timeStamp 4: description
//    private ATM.DB accountDB;       // 0: accountID 1: ownerID 2: balance 3: type 4: risk/interest/null (type-dependent)

    //Instance Fields
    Account account;
    private User currentUser;
    private String acctStatus;
    private ATM atm;

    enum Status {
        OPEN, CLOSED, OFAC
    }

    //Constructor
    public TransferServices(ATM atm, Account account) {
        this.currentUser = currentUser;
        this.atm = atm;
        this.account = account;
    }
}


//
//    public String acctFrozen (){
//        if (Account.getAcctStatus() == Status.OPAC {
//            return "THIS ACCOUNT IS FROZEN!  PLEASE TRY AGAIN.";
//        }
//        //transferMenu();
//    }
//
//
//
//    public String getAccntInfo(){
//        return atm.getAccountInfoByID();
//    }
//
//    public Integer getOwnerID() {
//        return this.atm.ownerID;
//    }
//
//    public Integer getAcctNum() {
//        return this.acct.acctNum;
//    }
//
//
//    public void deposit(Double amount){
//        this.balance += amount;
//        String bal = String.format("%.2f",this.balance);
//        this.balance = Double.parseDouble(bal);
//    }
//
//    public void withdraw(Double amount){
//        if (this.balance > amount) {
//            this.balance -= amount;
//        }
//    }
//    public Boolean equals(Account account) {
//        return ATM.DB.serialize(this.toStringArray()).equals(ATM.DB.serialize(account.toStringArray()));
//    }
//
//}



//
//             ATM.Console.println("Number of accounts.Account to transfer to");
//    int ActToTransferTo = ATM.Console.getInteger();
//    String[] actInfo = atm.getAccountInfoByID(ActToTransferTo);
//    // 0: accountID 1: ownerID 2: balance 3: type 4: risk/interest/null (type-dependent)
//    accounts.Account act = getAccountByInfo(actInfo);
//    deposit = ATM.Console.getCurrency("Transfer amount");
//
//                if(deposit < account.getBalance()) {
//        account.deposit(-1 * deposit);
//        act.deposit(deposit);
//
//        saveAccountToDB(account);
//        transaction = new ATM.Transaction(-1 * deposit, new Date(), account.getAcctNum(), "ATM.ATM Transfer", false);
//        saveTransactionToDB(transaction);
//
//        saveAccountToDB(act);
//        transaction = new ATM.Transaction(deposit, new Date(), act.getAcctNum(), "ATM.ATM Transfer", true);
//        saveTransactionToDB(transaction);
//    } else {
//        ATM.Console.println("Insufficient funds in account");
//    }
//
//                break;










    //ACCOUNT SERVICES


//    public int[] getAccountRowsByUser (ATM.User user) {
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
//    public accounts.Account getAccountByInfo (String[] info) {
//        if (info[3].equals("accounts.Checking")) {
//            return new accounts.Checking(Double.parseDouble(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]));
//        } else if (info[3].equals("accounts.Savings")) {
//            return new accounts.Savings(Double.parseDouble(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]), Double.parseDouble(info[4]));
//        } else if (info[3].equals("accounts.Investment")) {
//            return new accounts.Investment(Double.parseDouble(info[2]), Integer.parseInt(info[1]), Integer.parseInt(info[0]), Double.parseDouble(info[4]));
//        }
//        return null;
//    }

    // AL of accounts for a user
//    public ArrayList<accounts.Account> getAccountsForUser(ATM.User user) {
//        int[] rows = getAccountRowsByUser(user);
//        ArrayList<accounts.Account> accounts = new ArrayList<>();
//        for (int row : rows) {
//            accounts.add(getAccountByInfo(getAccountInfoByRow(row)));
//        }
//        return accounts;
//    }




