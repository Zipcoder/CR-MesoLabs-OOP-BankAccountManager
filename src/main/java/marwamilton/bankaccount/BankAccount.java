package marwamilton.bankaccount;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mkulima on 1/17/17.
 */
public class BankAccount {

    // fields
    private String acType;
    private int number;
    private static int numberOfAccounts;
    private double balance;
    private double rate;
    private String holdersName;
    private double interestRate;
    private boolean OFACFreeze;
    private boolean accountOpen;
    private String overdraftPrevention;
    private String[] oneTransaction;
    protected ArrayList<String> allTransactions;

    // constructors
    BankAccount(String acType, int number, String holdersName){
        this.acType = acType;
        this.number = number;
        OFACFreeze = false;
        this.balance = 0.0;
        this.rate = 0.0;
        this.OFACFreeze = false;
        this.accountOpen = true;
        this.holdersName = holdersName;
        this.rate = acType.equals("SAV")? 0.06 : 0.05;
        oneTransaction = new String[]{"Transaction", "Rate","Balance", "OFACFreeze", "AC Open", "Holders Name"};
        allTransactions = new ArrayList<String>(3);
        allTransactions.add(Arrays.toString(oneTransaction));
    }

    BankAccount(String acType, int number, String holdersName, double CIBrate){
        this(acType, number, holdersName);
        this.rate = this.acType.equals("CIB") ? CIBrate : this.rate;
    }

    protected String getHoldersName(){
        return this.holdersName;
    }

    protected double getBalance(){
        return OFACFreeze || !accountOpen ? 0.0 : this.balance;
    }

    protected String getAcType() {
        return this.acType;
    }

    protected void setRate(double rateCIB){
        this.rate = this.acType.equals("CIB") ? rateCIB : this.rate;
        this.recordTransaction("rateChange");
    }

    protected double getRate(){
        return this.rate;
    }

    protected boolean credit(double deposit){
        if(!OFACFreeze && accountOpen) {
            this.balance += deposit;
            this.recordTransaction("Credit");
            return true;
        } else {
            this.recordTransaction("Debit");
            return false;
        }
    }

    protected boolean debit(double withdraw, boolean OFACFreeze){
        if(!OFACFreeze && accountOpen && balance>=0){
            this.balance -= withdraw;
            this.recordTransaction("Debit");
            return  true;
        } else {
            this.recordTransaction("Debit");
            return  false;
        }
    }

    private boolean positiveBalance(){
        return getBalance()>0 ? true : false;
    }

    private boolean positiveBalance(double debit){
        return getBalance() - debit > 0 ? true : false;
    }

    protected boolean transferFunds(BankAccount receiving, double transferAmount){
        if(positiveBalance(transferAmount)) {
            this.debit(transferAmount, OFACFreeze);
            receiving.credit(transferAmount);
            this.recordTransaction("transfer successful");
            return true;
        } else{
            this.recordTransaction("transfer failed");
            return false;
        }
    }

    private void recordTransaction(String transaction){
        oneTransaction = new String[]{transaction, Double.toString(this.rate), Double.toString(this.balance), Boolean.toString(this.OFACFreeze), Boolean.toString(this.accountOpen), getHoldersName()};
        allTransactions.add(Arrays.toString(oneTransaction));
    }




}

