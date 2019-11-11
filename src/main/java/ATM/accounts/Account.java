package ATM.accounts;

import java.util.Random;

import ATM.DB;
import ATM.interfaces.Storeable;

abstract public class Account implements Storeable {

    private Double balance;
    private Integer ownerID;
    private Integer acctNum;
    public enum Status {
        OPEN, CLOSED, OFAC
    }
    private Status acctStatus;
    Random random = new Random();

    public Account(Double balance, Integer ownerID, Integer acctNum, Status acctStatus) {

        this.balance = balance;
        this.ownerID = ownerID;
        this.acctNum = acctNum;
        this.acctStatus = acctStatus;
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

    public void setBalance(Double balance) {
        this.balance = balance;
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
        if (this.balance >= amount) {
            this.balance -= amount;
        }
    }

    public Boolean equals(Account account) {
        return DB.serialize(this.toStringArray()).equals(DB.serialize(account.toStringArray()));
    }
}

