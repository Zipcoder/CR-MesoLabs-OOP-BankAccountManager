import java.util.Random;

abstract public class Account implements Storeable {

    public Double balance;
    public Integer ownerID;
    public Integer acctNum;
    enum Status {
        OPEN, CLOSED, OFAC
    }
    private Status acctStatus;
    Random random = new Random();

    //Constructor
    public Account(Double balance, Integer ownerID, Integer acctNum) {
        this.balance = balance;
        this.ownerID = ownerID;
        // TODO: make account number here, via something better than wild-assed guess
        //generateAccountNum();
        this.acctNum = acctNum;
    }

    public Integer acctNumGenerator () {
        int acctNum = random.nextInt(99999+1);
        return acctNum;
    }


    //Checks if the account number is already in the database
    //    public Integer checkAcctNumExists (acctNum) {
//        if (acctNum == CHECK ACCT # AGAIINST USER DB){
//            acctNumGenerator();
//        }
    //    else {return acctNum};
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

