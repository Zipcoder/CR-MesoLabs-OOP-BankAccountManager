package battin.preston.AccessControl;

import java.util.ArrayList;

/**
 * Created by prestonbattin on 1/17/17.
 */
public class Account {


    protected String accountType, holdersName, status, overDraftProtection;
    protected  int checkingAccountNumber, savingAccountNumber;
    protected double balance, rate;
    static protected int totalCheckingAccounts = 0, totalSavingAccount = 0;
    protected ArrayList<Double> transfers = new ArrayList<>();
    protected ArrayList<Double> withdrawals = new ArrayList<>(), deposits = new ArrayList<>(),
            interestRates = new ArrayList<>();
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















}
