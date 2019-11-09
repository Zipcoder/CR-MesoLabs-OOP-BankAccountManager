package ATM.menus;

import ATM.ATM;
import ATM.Console;
import ATM.interfaces.Menu;
import ATM.services.UserServices;

public class UserMenu implements Menu {

    private Console console;
    private String name = "ATM.User ATM.interfaces.Menu";
    private ATM atm;
    private UserServices userServices;

    public UserMenu(ATM atm){
        this.atm = atm;
        this.userServices = this.atm.getUserServices();
    }

    public void displayMenu() {
        String header = "Welcome to ZipCode National Bank";
        int input = Console.getInput(header, new String[] {"Insert Card", "Open an Account"});
        handleChoice(input);
    }

    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                //userServices.authenticate();
                if (this.atm.getCurrentUser() == null) {
                    return;
                }
                break;
            case 2:
                new NewUserMenu(this.atm);
                break;
        }
    }

    public String getName() {
        return null;
    }

}
