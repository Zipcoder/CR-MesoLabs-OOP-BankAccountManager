package ATM.services;

import ATM.ATM;
import ATM.DB;
import ATM.User;
import ATM.Console;

import java.util.ArrayList;

public class UserServices {

    private DB userDB;
    private ATM atm;
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserID() {
        return userID;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    private String password;
    private Integer userID;
    private Integer cardNumber;

    public UserServices(DB userDB, ATM atm) {
        this.userDB = userDB;
        this.atm = atm;
    }

    public int getMaxUserNumber() {
        ArrayList<String[]> userInfo = new ArrayList<>();
        userInfo = this.userDB.readAllRows();
        int maxID = 0;
        for (String[] user : userInfo) {
            if (Integer.parseInt(user[0]) > maxID) {
                maxID = Integer.parseInt(user[0]);
            }
        }
        return maxID;
    }

    public Integer getUserRowByID (Integer ID) {
        return this.userDB.findPartialRow(new String[]{ID.toString()}, new int[]{0});
    }

    public String [] getUserInfoByID (Integer ID) {
        int rowNumOfUser = this.userDB.findPartialRow(new String[] {ID.toString()}, new int[] {0});
        return this.userDB.readRow(rowNumOfUser);
    }

    public String [] getUserInfoByCardNum (Integer cardNum) {
        int rowNumOfUser = this.userDB.findPartialRow(new String[] {cardNum.toString()}, new int[] {3});
        return this.userDB.readRow(rowNumOfUser);
    }

    public void saveUserToDB(User user) {
        String[] stringRepOfUser = user.toStringArray();
        int userID = user.getUserID();
        int rowNum = getUserRowByID(userID);
        if (rowNum == -1) { // user isn't in ATM.DB yet
            this.userDB.addRow(stringRepOfUser);
        } else { // update a found row
            this.userDB.replaceRow(rowNum, stringRepOfUser);
        }
    }
    public void clearUserDB(){
        this.userDB.clear();
    }

    public int getUserDBLength(){
        return userDB.length();
    }

    public User authenticate() {
        //Read ATM.User's card
        Console.println("Card Number:");
        int cardNum = Console.getInteger();

        // find user in ATM.DB
        String[] userInfo = getUserInfoByCardNum(cardNum);
        if (userInfo == null){
            return null;
        }
        // check PW
        String password = Console.getInput("Enter Password: ");
        if(password.equals(userInfo[4])) {
            // 0: ID 1: Last Name 2: First Name 3: cardNum 4: PW
            this.atm.setCurrentUser(new User(userInfo[2], userInfo[1], userInfo[4], Integer.parseInt(userInfo[0]), Integer.parseInt(userInfo[3])));
            return this.atm.getCurrentUser();
        } else {
            return null;
        }
    }

    public User createNewUser(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userID = genUserID();
        this.cardNumber = genCardNum();
        User user = new User(firstName, lastName, password, userID, cardNumber);
        saveUserToDB(user);
        Console.getInput(String.format("Your card number is %d.\nPlease write this down somewhere. You will need it to log in later.\n[press return to continue]", cardNumber));
        return user;
    }

    public Integer genUserID(){
        Integer newUserID;
        newUserID = getMaxUserNumber() + 1;
        return newUserID;
    }

    public Integer genCardNum() {
        String numString = "";
        for (int i = 0; i < 8; i++) {
            Integer num;
            if(i == 0 || i == 7) {
                num = (int)(Math.random() * 9 + 1);
            } else {
                num = (int)(Math.random() * 10);
            }
            numString += num.toString();
        }
        return Integer.parseInt(numString);
    }

}
