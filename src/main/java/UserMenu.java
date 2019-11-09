import java.util.ArrayList;

public class UserMenu implements Menu{

    private Console console;
    private String name = "User Menu";
    private ATM atm;

    public UserMenu(ATM atm){
        this.atm = atm;
    }

    public void displayMenu() {
        String header = "Welcome to ZipCode National Bank";
        String input = Console.getInput(header, new String[] {"Insert Card", "Open an Account"});
        handleChoice(Integer.parseInt(input));
    }

    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                this.authenticate();
                if (this.currentUser == null) {
                    return;
                }
                break;
            case 2:
                this.newUserMenu();
                break;
        }
    }

    public String getName() {
        return null;
    }

}
