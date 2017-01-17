package battin.preston.AccessControl;

import java.util.ArrayList;

/**
 * Created by prestonbattin on 1/17/17.
 */
public class Checking extends Account {

    private ArrayList<Double> withdrawals = new ArrayList<>(), deposits = new ArrayList<>(),
            transfers = new ArrayList<>(), statuses = new ArrayList<>(),
            names = new ArrayList<>(), interestRates = new ArrayList<>();


    public Checking(String type, String name){
        super(type, name);
    }

    public int getAccountNumber(){

         return this.checkingAccountNumber;

    }

    public double getCheckingBalance(){

       return this.balance;
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







}
