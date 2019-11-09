package accounts;

import interfaces.Storeable;

abstract public class Account implements Storeable {

    public Double balance;
    public Integer ownerID;
    public Integer acctNum;
    enum Status {
        OPEN, CLOSED, OFAC
    }
    private Status acctStatus;

    public Account(Double balance, Integer ownerID, Integer acctNum) {
        this.balance = balance;
        this.ownerID = ownerID;
        // TODO: make account number here, via something better than wild-assed guess

        this.acctNum = acctNum;
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

