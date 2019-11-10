package ATM;

import ATM.interfaces.Storeable;

import java.util.ArrayList;

public class User implements Storeable {

    private String firstName;
    private String lastName;
    private String password;
    private Integer userID;
    private Integer cardNumber;

    public User(String firstName, String lastName, String password, Integer userID, Integer cardNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userID = userID;
        this.cardNumber = cardNumber;
    }

    public static Integer genCardNum() {
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

    public Integer getUserID() {
        return userID;
    }

    @Override
    public String[] toStringArray() {
        String[] result = new String[] {
                this.userID.toString(),
                this.lastName,
                this.firstName,
                this.cardNumber.toString(),
                this.password
        };
        return result;
    }

}
