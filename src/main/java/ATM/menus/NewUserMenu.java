package ATM.menus;

import ATM.ATM;
import ATM.interfaces.Menu;
import ATM.Console;
import ATM.services.TransactionServices;
import ATM.services.UserServices;

public class NewUserMenu implements Menu {

    private String name = "New User Menu";
    private ATM atm;
    private UserServices userServices;
    private TransactionServices transactionServices;

    public NewUserMenu(ATM atm){
        this.atm = atm;
        this.userServices = this.atm.getUserServices();
        this.transactionServices = this.atm.getTransactionServices();
    }

    // needs input - no test
    public void displayMenu() {
        String firstName = Console.getInput("Enter Your First Name: ");
        String lastName = Console.getInput("Enter Your Last Name: ");
        String password = Console.getInput("Choose Your Password: ");

        createNewUser(firstName, lastName, password);
    }

    public void createNewUser(String firstName, String lastName, String password) {
        if (firstName.equals("") || lastName.equals("") || password.equals("")){
            Console.getInput("Names and passwords cannot be empty. [press Enter to retry]");
            displayMenu();
        } else {
            this.atm.setCurrentUser(userServices.createNewUser(firstName, lastName, password));
            Console.getInput(String.format("Your card number is %d.\nPlease write this down somewhere. You will need it to log in later.\n[press return to continue]", this.atm.getCurrentUser().getCardNumber()));
        }
    }

    public void handleChoice(int choice) {

    }

    @Override
    public String getName() {
        return this.name;
    }
}
