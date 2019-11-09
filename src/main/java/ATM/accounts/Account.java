package ATM.accounts;


import java.util.Random;
import ATM.User;



import ATM.DB;
import ATM.interfaces.Storeable;


abstract public class Account implements Storeable {

    public Double balance;
    public Integer ownerID;
    public Integer acctNum;
    public enum Status {
        OPEN, CLOSED, OFAC
    }
    private Status acctStatus;
    Random random = new Random();




    public Account(Double balance, Integer ownerID, Integer acctNum, Status acctStatus) {

        this.balance = balance;
        this.ownerID = ownerID;
        // TODO: make account number here, via something better than wild-assed guess
        //generateAccountNum();
        this.acctNum = acctNum;
        this.acctStatus = acctStatus;
    }

    public Integer acctNumGenerator () {
        int acctNum = random.nextInt( 98999 + 1) + 1000;
        return acctNum;
    }


    //Checks if the account number is already in the database
//        public Integer checkAcctNumExists (acctNum) {
//        if (acctNum == accountServices.geta{
//            acctNumGenerator();
//        }
//        else {return acctNum};
//    }

//    public void generateAccountNum () {
//        acctNumGenerator();
//        checkAcctNumExists();
//    }


    public Double getBalance(){
        return balance;
    }

    public Integer getOwnerID() {
        return this.ownerID;
    }

    public Integer getAcctNum() {
        return this.acctNum;
    }

    public Status getAcctStatus() {
        return acctStatus;
    }

    public void setAcctStatus(Status acctStatus) {
        this.acctStatus = acctStatus;
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

