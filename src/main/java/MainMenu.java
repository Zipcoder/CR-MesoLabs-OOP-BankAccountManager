import java.util.ArrayList;

public class MainMenu implements Menu{

    private Console console;
    private String name = "User Menu";
    private ATM atm;

    public MainMenu(ATM atm){
        this.atm = atm;
    }

    public void displayMenu() {
        String header = "ZCNB Main Menu";
        //maybe Younger Bank and Trust (YBT)
        //logo is giant ASCII of Kris' face

        ArrayList<String> choices = new ArrayList<>();
        choices.add("Transaction History");
        choices.add("Add Account");

        String nextAcctChoice;
        ArrayList<Account> usrAccts = accountServices.getAccountsForUser(currentUser);
        for (int i = 0; i < usrAccts.size(); i++) {
            nextAcctChoice = String.format("%s #%d ($%,.2f)", usrAccts.get(i).getClass().getName(), usrAccts.get(i).getAcctNum(), usrAccts.get(i).getBalance());
            choices.add(nextAcctChoice);
        }

        choices.add("Log Out");

        String input = Console.getInput(header, choices.toArray(new String[choices.size()]));
    }

    public void handleChoice(int input) {
        if (input.equals(Integer.toString(input.size()))) {
            serviceLoop(); //not ... great, but it'll do for now
        } else if (input.equals(1)) {
            Console.outputTransactionsWithHeader("Transaction History", transactionServices.getTransactionsForUser(this.currentUser));
        } else if (input.equals(2)) {
            Double deposit = Console.getCurrency("Initial deposit amount for this account: ");
            accountServices.addAccount(usrAccts, deposit);
        } else {
            AccountMenu.displayMenu(usrAccts.get(Integer.parseInt(input) - 3));
        }

        userMenu();
    }

    @Override
    public String getName() {
        return null;
    }
}
