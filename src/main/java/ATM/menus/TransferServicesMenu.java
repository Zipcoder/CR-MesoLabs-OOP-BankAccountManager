package ATM.menus;

import java.util.Date;

public class TransferServicesMenu {


    //services.TransferServices transferServices = new services.TransferServices(currentUser, userDB, transactionDB, accountDB);
//
//    public void accountMenu(accounts.Account account) {
//        String header = account.getClass().getName() + " accounts.Account #" + account.getAcctNum().toString() + "  Balance: $" + String.format("%,.2f", account.getBalance());
//        if (account instanceof accounts.Savings) {
//            header += "  Interest Rate: " + String.format("%.2f", ((accounts.Savings) account).getInterestRate())+"%%";
//        } else if (account instanceof accounts.Investment) {
//            header += "  Risk: " + String.format("%d", Math.round(100*((accounts.Investment) account).getRisk()))+"/10";
//        }
//        String input = ATM.Console.getInput(header, new String[] {"View ATM.Transaction History", "Deposit", "Withdrawal", "Close accounts.Account", "Transfer", "Back to ATM.Main ATM.interfaces.Menu" });
//
//        Double deposit;
//        ATM.Transaction transaction;
//        switch (input) {
//            case "1":
//                ATM.Console.outputTransactionsWithHeader("ATM.Transaction History", getTransactionsForAccount(account));
//                break;
//            case "2":
//                deposit = ATM.Console.getCurrency("Deposit amount: ");
//                account.deposit(deposit);
//                saveAccountToDB(account);
//                transaction = new ATM.Transaction(deposit, new Date(), account.getAcctNum(), "ATM.ATM deposit", true);
//                saveTransactionToDB(transaction);
//                break;
//            case "3":
//                deposit = ATM.Console.getCurrency("Withdrawal amount: ");
//                if (deposit <= account.getBalance()) {
//                    account.deposit(-1 * deposit);
//                    saveAccountToDB(account);
//                    transaction = new ATM.Transaction(deposit, new Date(), account.getAcctNum(), "ATM.ATM withdrawal", false);
//                    saveTransactionToDB(transaction);
//                } else {
//                    ATM.Console.println("Insufficient funds");
//                    ATM.Console.getInput("\nPress Enter");
//                }
//                break;
//            case "4":
//
//                if (account.getBalance() == 0) {
//
//                    deleteAccountFromDB(account);
//                    transaction = new ATM.Transaction(0.0, new Date(), account.getAcctNum(), "accounts.Account Closed", false);
//                    saveTransactionToDB(transaction);
//                } else {
//                    ATM.Console.println("accounts.Account still contains funds. Withdraw or transfer all funds before closing.");
//                    ATM.Console.getInput("\nPress Enter");
//                }
//                break;
//            case "5":
//
//                ATM.Console.println("Number of accounts.Account to transfer to");
//                int ActToTransferTo = ATM.Console.getInteger();
//                String[] actInfo = getAccountInfoByID(ActToTransferTo);
//                // 0: accountID 1: ownerID 2: balance 3: type 4: risk/interest/null (type-dependent)
//                accounts.Account act = getAccountByInfo(actInfo);
//                deposit = ATM.Console.getCurrency("Transfer amount");
//
//                if(deposit < account.getBalance()) {
//                    account.deposit(-1 * deposit);
//                    act.deposit(deposit);
//
//                    saveAccountToDB(account);
//                    transaction = new ATM.Transaction(-1 * deposit, new Date(), account.getAcctNum(), "ATM.ATM Transfer", false);
//                    saveTransactionToDB(transaction);
//
//                    saveAccountToDB(act);
//                    transaction = new ATM.Transaction(deposit, new Date(), act.getAcctNum(), "ATM.ATM Transfer", true);
//                    saveTransactionToDB(transaction);
//                } else {
//                    ATM.Console.println("Insufficient funds in account");
//                }
//
//                break;
//            case "6":
//                break;
//        }
//    }



}
