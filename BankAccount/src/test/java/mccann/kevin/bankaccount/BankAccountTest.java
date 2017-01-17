package mccann.kevin.bankaccount;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevinmccann on 1/17/17.
 */
public class BankAccountTest {
    @Test
    public void getAcctBalance(){
        BankAccount BA = new BankAccount('C', "44445555", "John Jacob Schmidt");
        BA.setAcctBalance(33.3);
        double expected = 33.3;
        double actual = BA.getAcctBalance();
        assertEquals("33.3 is expected",expected, actual, 0);
    }

}