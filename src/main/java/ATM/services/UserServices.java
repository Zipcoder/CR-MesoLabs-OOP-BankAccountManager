package ATM.services;

import ATM.ATM;
import ATM.DB;
import ATM.User;

import java.util.ArrayList;

public class UserServices {

    private DB userDB;
    private ATM atm;

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
}
