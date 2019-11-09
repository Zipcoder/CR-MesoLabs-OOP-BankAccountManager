public class NewUserMenu {

    private Console console;
    private String name = "User Menu";
    private ATM atm;
    String firstName;
    String lastName;
    String password;

    public NewUserMenu(ATM atm){
        this.atm = atm;
    }

    public void displayMenu() {
        firstName = Console.getInput("Enter Your First Name: ");
        lastName = Console.getInput("Enter Your Last Name: ");
        password = Console.getInput("Choose Your Password: ");
        //pass this to buildANewUser
        buildANewUser(firstName,lastName,password);
        UserMenu();
    }

//call the constructor
    public void buildANewUser(String firstName, String lastName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        Integer cardNumber = User.genCardNum();
        Console.println("Your Card Number: " + cardNumber + "\n");

        Integer userID = (int) (Math.random() * 1000);
        //make this like a phone number with specific sections of the account number signifying different things
        //for example, a code for acct type, a code for initials, and a random generated code as the suffix
        //also, verify that it doesn't match any other account number in the database

        UserServices.createNewUser(firstName, lastName, password, cardNumber, userID);
    }

}
