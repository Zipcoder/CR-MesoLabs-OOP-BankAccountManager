package armstrong.alexandra;

import java.io.*;

import static armstrong.alexandra.Status.*;
import static armstrong.alexandra.AccountType.*;
import static armstrong.alexandra.Overdraft.*;

/**
 * Created by alexandraarmstrong on 1/17/17.
 */
public class BankAccount {
    private final static int ROUTERNUMBER = 1234567;
    private static int accountCounter = 0;

    private AccountType accountType;
    private long accountNumber;
    private double balance = 0;
    private String accountHolderName;
    private double interestRate = 1.00d;
    private Status status = CLOSED;
    private Overdraft overdraft = DISABLED;
    //private File record;

    BankAccount(AccountType accountType, String accountHolderName){
        this.accountType = accountType;
        this.accountHolderName = accountHolderName;
        accountCounter++;
        accountNumber = Integer.valueOf(String.valueOf(ROUTERNUMBER) + String.valueOf(accountCounter));
        status = OPEN;
    }

    BankAccount(AccountType accountType, String accountHolderName, Overdraft overdraft){
        this(accountType, accountHolderName);
        this.overdraft = overdraft;
    }

    BankAccount(AccountType accountType, String accountHolderName, double interestRate){
        this(accountType, accountHolderName);
        this.interestRate = interestRate;
    }

    BankAccount(AccountType accountType, String accountHolderName, double interestRate, Overdraft overdraft){
        this(accountType, accountHolderName, overdraft);
        this.interestRate = interestRate;
    }


    public AccountType getAccountType(){
        return accountType;
    }

    public long getAccountNumber(){
        return accountNumber;
    }

    public Double getBalance(){
        if (status == FROZEN) {
            return null;
        } else {
            return balance;
        }
    }

    public String getAccountHolderName(){
        return accountHolderName;
    }

    public double getInterestRate(){
        return interestRate;
    }

    public Status getStatus(){
        return status;
    }

    public Overdraft getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(Overdraft overdraft){
        this.overdraft = overdraft;
    }

    public void setInterestRate(double interestRate){
        this.interestRate = interestRate;
        writeToFileChangeInterestRate();
    }

    protected void setStatus(Status status){
        this.status = status;
        writeToFileChangeStatus();
    }

    public String changeBalance(double amount) {
        if (status == OPEN) {
            if (overdraft == ENABLED) {
                if (amount > 0 || amount > getBalance()) {
                    balance += amount;
                    writeToFileChangeBalance();
                    return "Balance adjusted";
                } else {
                    return "Insufficient Funds";
                }
            } else {
                balance += amount;
                writeToFileChangeBalance();
                return "Balance adjusted";
            }
        } else {
            return "Balance inaccessible";
        }
    }

    public String transferMoneyToOtherAccount(BankAccount otherAccount, double amount) {
        if (accountHolderName.equalsIgnoreCase(otherAccount.accountHolderName)) {
            if (getBalance() > amount || overdraft != DISABLED) {
                changeBalance(-1 * amount);
                otherAccount.changeBalance(amount);
                return "Transfer Successful";
            } else {
                return "Insufficient funds";
            }
        } else {
            return "Permission Denied";
        }
    }

    public void setAccountHolderName(String name){
        if(status != CLOSED){
            this.accountHolderName = name;
            writeToFileChangeName();
        }
    }

    public String closeAccount(){
        if(getBalance() == 0d){
            setStatus(CLOSED);
            return "Account Closed";
        } else {
            return "Please withdraw funds";
        }
    }

    public String changeFreezeStatus(){
        if (status == FROZEN) {
            setStatus(OPEN);
            return "Account unfrozen";
        } else {
            setStatus(FROZEN);
            return "Account frozen";
        }
    }

    private void writeToFileChangeBalance() {
        try {
            PrintStream print = new PrintStream(new BufferedOutputStream(new FileOutputStream("BankAccount.txt", true)));
            print.println("Balance Changed to " + balance + ".");
            print.close();
        } catch (IOException e) {
        }
    }

    private void writeToFileChangeStatus(){
        try {
            PrintStream print = new PrintStream(new BufferedOutputStream(new FileOutputStream("BankAccount.txt", true)));
            print.println("Status changed to " + status + ".");
            print.close();
        } catch (IOException e) {
        }
    }

    private void writeToFileChangeName(){
        try {
            PrintStream print = new PrintStream(new BufferedOutputStream(new FileOutputStream("BankAccount.txt", true)));
            print.println("Name changed to " + accountHolderName + ".");
            print.close();
        } catch (IOException e) {
        }
    }

    private void writeToFileChangeInterestRate(){
        try {
            PrintStream print = new PrintStream(new BufferedOutputStream(new FileOutputStream("BankAccount.txt", true)));
            print.println("Interest rate changed to " + interestRate + ".");
            print.close();
        } catch (IOException e) {
        }
    }

}



