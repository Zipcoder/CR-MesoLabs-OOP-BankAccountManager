package ATM.menus;

import ATM.ATM;
import ATM.interfaces.Menu;
import ATM.Console;
import ATM.services.TransactionServices;
import ATM.services.UserServices;

public class NewUserMenu implements Menu {

    private String name = "User Menu";
    private ATM atm;
    private UserServices userServices;
    private TransactionServices transactionServices;

    public NewUserMenu(ATM atm){
        this.atm = atm;
        this.userServices = this.atm.getUserServices();
        this.transactionServices = this.atm.getTransactionServices();
    }

    public void displayMenu() {
        String firstName = Console.getInput("Enter Your First Name: ");
        String lastName = Console.getInput("Enter Your Last Name: ");
        String password = Console.getInput("Choose Your Password: ");
        //pass this to buildANewUser
        //this.atm.setCurrentUser(userServices.createNewUser(firstName,lastName,password));
    }

    public void handleChoice(int choice) {

    }

    @Override
    public String getName() {
        return this.name;
    }
}
