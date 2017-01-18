package battin.preston.AccessControl;

import java.util.ArrayList;

/**
 * Created by prestonbattin on 1/17/17.
 */
public class Account {


    private String accountType, holdersName, status, overDraftProtection;
    private  int checkingAccountNumber, savingAccountNumber;
    private static int totalSavingAccount, totalCheckingAccounts;
    private double balance, rate;
    private ArrayList<Double> withdrawals = new ArrayList<>(), deposits = new ArrayList<>(),
            interestRates = new ArrayList<>(), transfers = new ArrayList<>();
    private ArrayList<String> statuses = new ArrayList<>(), names = new ArrayList<>();


    protected Account() {
    }

    protected Account(String type, String name) {
        if (type.equals("Checking")) {
            this.checkingAccountNumber = totalCheckingAccounts;
            this.accountType = type;
            totalCheckingAccounts++;
        } else if (type.equals("Savings")) {
            this.savingAccountNumber = totalSavingAccount;
            this.accountType = type;
            totalSavingAccount++;
        }

        this.holdersName = name;
    }


    protected int getAccountNumber(){

        return this.savingAccountNumber;

    }

    protected void creditAccount(double credit){

        this.balance += credit;
        deposits.add(credit);
        System.out.println("Account successfully credited +" + credit);
    }

    protected void debitAccount(double debit){

        this.balance -= debit;
        withdrawals.add(-debit);
        System.out.println("Account successfully debited -" + debit);
    }

    protected ArrayList<Double> getDeposits(){

        return deposits;
    }

    protected ArrayList<Double> getWithdrawls(){

        return withdrawals;
    }

    protected void setAccountHoldersName(String name){

        this.holdersName = name;
        this.names.add(name);
    }

    protected ArrayList<Double> getTransfers(){

        return transfers;
    }

    protected void makeTransfer(Account receiving, double money){

        if((this.holdersName.equals(receiving.holdersName)) && (this.balance > money)){
            this.balance -= money;
            receiving.balance += money;
            this.transfers.add(-money);
            receiving.transfers.add(money);
        } else
            System.out.println("Not enough money to transfer.");
    }

    protected ArrayList<String> getStatuses(){

        return statuses;
    }

    protected ArrayList<String> getNames(){

        return names;
    }

    protected ArrayList<Double> getInterestRates(){

        return interestRates;
    }


    protected void setInterestRates(double rate){

        this.rate = rate;
        this.interestRates.add(rate);
    }

    protected void setStatus(String status) {

        if (status.equals("Open")) {
            this.status = status;
        }

        else if (status.equals("Closed")) {
            this.status = status;
        }

        else {
            this.status = "OFAC Freeze";
        }

        this.statuses.add(status);
    }

    protected String getStatus() {

        return this.status;
    }

    protected void setOverDraftProtection(String set) {

        this.overDraftProtection = set;
    }

    protected String getOverDraftProtection() {

        return this.overDraftProtection;
    }

    protected double getRate() {

        return this.rate;
    }

    protected double getBalance() {

        return this.balance;
    }

    protected String getHoldersName() {

        return this.holdersName;
    }

    protected void setRate() {

        if(this.accountType.equals("Savings")) {
            this.rate = .06;
            this.interestRates.add(.06);
        }
        else
            this.rate = .05;
        this.interestRates.add(.05);

    }

}














