import java.util.ArrayList;
import java.util.Date;

public class AccountMenu implements Menu {

    private Console console;
    private String name = "Account Menu";
    private Account account;
    private User user;
    private ATM atm;

    public AccountMenu(ATM atm, User user, Account account){
        this.atm = atm;
        this.user = this.atm.getCurrentUser();
        this.account = account;
    }

    public void displayMenu() {
        console.clearScreen();

        String header = account.getClass().getName() + " Account #" + account.getAcctNum().toString() + "  Balance: $" + String.format("%,.2f", account.getBalance());
        if (account instanceof Savings) {
            header += "  Interest Rate: " + String.format("%.2f", ((Savings) account).getInterestRate())+"%%";
        } else if (account instanceof Investment) {
            header += "  Risk: " + String.format("%d", Math.round(100*((Investment) account).getRisk()))+"/10";
        }
        String input = Console.getInput(header, new String[] {"View Transaction History", "Deposit", "Withdrawal", "Close Account", "Transfer", "Back to Main Menu" });
        handleChoice(Integer.parseInt(input));
    }

    public String getName() {
        return null;
    }

    public void handleChoice(int choice) {
        Double deposit;
        Transaction transaction;
            switch (choice) {
            case 1:
                Console.outputTransactionsWithHeader("Transaction History", transactionServices.getTransactionsForAccount(account));
                break;
            case 2:
                deposit = Console.getCurrency("Deposit amount: ");
                account.deposit(deposit);
                transactionServices.saveAccountToDB(account);
                transaction = new Transaction(deposit, new Date(), account.getAcctNum(), "ATM deposit", true);
                transactionServices.saveTransactionToDB(transaction);
                break;
            case 3:
                deposit = Console.getCurrency("Withdrawal amount: ");
                if (deposit <= account.getBalance()) {
                    account.deposit(-1 * deposit);
                    transactionServices.saveAccountToDB(account);
                    transaction = new Transaction(deposit, new Date(), account.getAcctNum(), "ATM withdrawal", false);
                    transactionServices.saveTransactionToDB(transaction);
                } else {
                    Console.println("Insufficient funds");
                    Console.getInput("\nPress Enter");
                }
                break;
            case 4:

                if (account.getBalance() == 0) {

                    transactionServices.deleteAccountFromDB(account);
                    transaction = new Transaction(0.0, new Date(), account.getAcctNum(), "Account Closed", false);
                    transactionServices.saveTransactionToDB(transaction);
                } else {
                    Console.println("Account still contains funds. Withdraw or transfer all funds before closing.");
                    Console.getInput("\nPress Enter");
                }
                break;
            case 5:

                Console.println("Number of Account to transfer to");
                int ActToTransferTo = Console.getInteger();
                String[] actInfo = accountServices.getAccountInfoByID(ActToTransferTo);
                // 0: accountID 1: ownerID 2: balance 3: type 4: risk/interest/null (type-dependent)
                Account act = accountServices.getAccountByInfo(actInfo);
                deposit = Console.getCurrency("Transfer amount");

                if(deposit < account.getBalance()) {
                    account.deposit(-1 * deposit);
                    act.deposit(deposit);

                    accountServices.saveAccountToDB(account);
                    transaction = new Transaction(-1 * deposit, new Date(), account.getAcctNum(), "ATM Transfer", false);
                    transactionServices.saveTransactionToDB(transaction);

                    accountServices.saveAccountToDB(act);
                    transaction = new Transaction(deposit, new Date(), act.getAcctNum(), "ATM Transfer", true);
                    transactionServices.saveTransactionToDB(transaction);
                } else {
                    Console.println("Insufficient funds in account");
                }

                break;
            case 6:
                break;
        }
    }

    public void addAccount(ArrayList<Account> usrAccounts, Double deposit) {
        String header = "Choose Account Type:";
        String input = Console.getInput(header, new String[] {"Checking", "Savings", "Investment", "Back to Main Menu" });
        Account newAccount;
        Transaction transaction;


        switch (input) {
            case "1":
                newAccount = new Checking(deposit, this.currentUser.getUserID(), (int)(Math.random()*1000));
                this.saveAccountToDB(newAccount);
                usrAccounts.add(newAccount);

                transaction = new Transaction(deposit, new Date(), newAccount.getAcctNum(), "Opened account", true);
                saveTransactionToDB(transaction);
                break;
            case "2":
                Double interestRate = .01 * (1 + Math.floor(deposit/1000));
                Console.println(String.format("Your interest rate: %.2f", interestRate)+"%%");
                newAccount = new Savings(deposit, this.currentUser.getUserID(), (int)(Math.random()*1000), interestRate);
                this.saveAccountToDB(newAccount);
                usrAccounts.add(newAccount);

                transaction = new Transaction(deposit, new Date(), newAccount.getAcctNum(), "Opened account", true);
                saveTransactionToDB(transaction);
                break;
            case "3":
                Console.print("On a scale of 1-10, enter your risk tolerance ");
                int riskInput = Console.getInteger(10);
                Double risk = riskInput * .01;
                newAccount = new Investment(deposit, this.currentUser.getUserID(), (int)(Math.random()*1000), risk);
                this.saveAccountToDB(newAccount);
                usrAccounts.add(newAccount);

                transaction = new Transaction(deposit, new Date(), newAccount.getAcctNum(), "Opened account", true);
                saveTransactionToDB(transaction);
                break;
            case "4":
                break;
        }


    }

}
