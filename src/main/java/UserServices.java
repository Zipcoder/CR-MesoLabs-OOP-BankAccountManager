import java.util.ArrayList;

public class UserServices {

    private DB userDB;

    public UserServices(DB userDB) {
        this.userDB = userDB;
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
        if (rowNum == -1) { // user isn't in DB yet
            this.userDB.addRow(stringRepOfUser);
        } else { // update a found row
            this.userDB.replaceRow(rowNum, stringRepOfUser);
        }
    }
}
