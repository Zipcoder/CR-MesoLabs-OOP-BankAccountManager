import java.util.Date;

public class TransferServicesMenu {


    //TransferServices transferServices = new TransferServices(currentUser, userDB, transactionDB, accountDB);
//
//    public void accountMenu(Account account) {
//        String header = account.getClass().getName() + " Account #" + account.getAcctNum().toString() + "  Balance: $" + String.format("%,.2f", account.getBalance());
//        if (account instanceof Savings) {
//            header += "  Interest Rate: " + String.format("%.2f", ((Savings) account).getInterestRate())+"%%";
//        } else if (account instanceof Investment) {
//            header += "  Risk: " + String.format("%d", Math.round(100*((Investment) account).getRisk()))+"/10";
//        }
//        String input = Console.getInput(header, new String[] {"View Transaction History", "Deposit", "Withdrawal", "Close Account", "Transfer", "Back to Main Menu" });
//
//        Double deposit;
//        Transaction transaction;
//        switch (input) {
//            case "1":
//                Console.outputTransactionsWithHeader("Transaction History", getTransactionsForAccount(account));
//                break;
//            case "2":
//                deposit = Console.getCurrency("Deposit amount: ");
//                account.deposit(deposit);
//                saveAccountToDB(account);
//                transaction = new Transaction(deposit, new Date(), account.getAcctNum(), "ATM deposit", true);
//                saveTransactionToDB(transaction);
//                break;
//            case "3":
//                deposit = Console.getCurrency("Withdrawal amount: ");
//                if (deposit <= account.getBalance()) {
//                    account.deposit(-1 * deposit);
//                    saveAccountToDB(account);
//                    transaction = new Transaction(deposit, new Date(), account.getAcctNum(), "ATM withdrawal", false);
//                    saveTransactionToDB(transaction);
//                } else {
//                    Console.println("Insufficient funds");
//                    Console.getInput("\nPress Enter");
//                }
//                break;
//            case "4":
//
//                if (account.getBalance() == 0) {
//
//                    deleteAccountFromDB(account);
//                    transaction = new Transaction(0.0, new Date(), account.getAcctNum(), "Account Closed", false);
//                    saveTransactionToDB(transaction);
//                } else {
//                    Console.println("Account still contains funds. Withdraw or transfer all funds before closing.");
//                    Console.getInput("\nPress Enter");
//                }
//                break;
//            case "5":
//
//                Console.println("Number of Account to transfer to");
//                int ActToTransferTo = Console.getInteger();
//                String[] actInfo = getAccountInfoByID(ActToTransferTo);
//                // 0: accountID 1: ownerID 2: balance 3: type 4: risk/interest/null (type-dependent)
//                Account act = getAccountByInfo(actInfo);
//                deposit = Console.getCurrency("Transfer amount");
//
//                if(deposit < account.getBalance()) {
//                    account.deposit(-1 * deposit);
//                    act.deposit(deposit);
//
//                    saveAccountToDB(account);
//                    transaction = new Transaction(-1 * deposit, new Date(), account.getAcctNum(), "ATM Transfer", false);
//                    saveTransactionToDB(transaction);
//
//                    saveAccountToDB(act);
//                    transaction = new Transaction(deposit, new Date(), act.getAcctNum(), "ATM Transfer", true);
//                    saveTransactionToDB(transaction);
//                } else {
//                    Console.println("Insufficient funds in account");
//                }
//
//                break;
//            case "6":
//                break;
//        }
//    }



}
