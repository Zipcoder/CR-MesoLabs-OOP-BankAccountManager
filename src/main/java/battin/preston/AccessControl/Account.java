package battin.preston.AccessControl;

import java.util.ArrayList;

/**
 * Created by prestonbattin on 1/17/17.
 */
public class Account {


    protected String accountType, holdersName, status, overDraftProtection;
    protected  int checkingAccountNumber, savingAccountNumber;
    protected static int totalSavingAccount, totalCheckingAccounts;
    protected double balance, rate;
    protected ArrayList<Double> withdrawals = new ArrayList<>(), deposits = new ArrayList<>(),
            interestRates = new ArrayList<>(), transfers = new ArrayList<>();
    protected ArrayList<String> statuses = new ArrayList<>(), names = new ArrayList<>();


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


    public int getAccountNumber(){

        return this.savingAccountNumber;

    }

    public void creditAccount(double credit){

        this.balance += credit;
        deposits.add(credit);
        System.out.println("Account successfully credited +" + credit);
    }

    public void debitAccount(double debit){

        this.balance -= debit;
        withdrawals.add(-debit);
        System.out.println("Account successfully debited -" + debit);
    }

    public ArrayList<Double> getDeposits(){

        return deposits;
    }

    public ArrayList<Double> getWithdrawls(){

        return withdrawals;
    }

    public void setAccountHoldersName(String name){

        this.holdersName = name;
        this.names.add(name);
    }

    public ArrayList<Double> getTransfers(){

        return transfers;
    }

    public void makeTransfer(Account receiving, double money){

        if((this.holdersName.equals(receiving.holdersName)) && (this.balance > money)){
            this.balance -= money;
            receiving.balance += money;
            this.transfers.add(-money);
            receiving.transfers.add(money);
        } else
            System.out.println("Not enough money to transfer.");
    }

    public ArrayList<String> getStatuses(){

        return statuses;
    }

    public ArrayList<String> getNames(){

        return names;
    }

    public ArrayList<Double> getInterestRates(){

        return interestRates;
    }


    public void setInterestRates(double rate){

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














