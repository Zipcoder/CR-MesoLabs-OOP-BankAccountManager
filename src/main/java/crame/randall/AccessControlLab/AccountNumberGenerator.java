package crame.randall.AccessControlLab;

/**
 * Created by randallcrame on 1/17/17.
 */
class AccountNumberGenerator {
    static long accountNumberGenerated = 360000000;

    static long createAccountNumber(){
        return accountNumberGenerated+=2;

    }



}
