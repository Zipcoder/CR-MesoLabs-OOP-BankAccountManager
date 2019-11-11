package ATM.menus;

import ATM.ATM;
import ATM.Console;
import ATM.interfaces.Menu;
import ATM.services.AccountServices;
import ATM.services.UserServices;

public class UserMenu implements Menu {

    private String name = "User Menu";
    private ATM atm;
    private UserServices userServices;
    private AccountServices accountServices;

    public UserMenu(ATM atm){
        this.atm = atm;
        this.userServices = this.atm.getUserServices();
        this.accountServices = this.atm.getAccountServices();
    }

    // needs input - no test
    public void displayMenu() {
        String header = "Welcome to ZipCode National Bank";
        int input = Console.getInput(header, new String[] {"Insert Card", "Open an Account"});
        handleChoice(input);
    }

    // needs input - no test
    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                userServices.authenticate();
                if (this.atm.getCurrentUser() == null) {
                    Console.getInput("Authentication error - press Enter to try again");
                    displayMenu();
                    return;
                } else {
                    accountMaintenance();
                }
                break;
            case 2:
                new NewUserMenu(this.atm).displayMenu();
                break;
        }
    }

    // underlying methods tested
    public void accountMaintenance() {
        accountServices.interestRateChange();
        accountServices.applyInterest();
        accountServices.applyReturns();
    }

    public String getName() {
        return this.name;
    }

}
