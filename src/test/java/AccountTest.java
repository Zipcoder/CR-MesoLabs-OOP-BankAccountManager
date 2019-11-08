import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void getBalance() {
        // Given
        Account account = new Checking(0.0, 3,3);
        Double expected = 0.0;


        // Then
        Double actual = account.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void deposit_test() {
        // Given
        Account account = new Checking(0.0, 3,3);
        Double expected = 40.0;

        //When
        account.deposit(40.0);

        // Then
        Double actual = account.getBalance();
        assertEquals(expected, actual);

    }

    @Test
    public void withdraw_test() {
        // Given
        Account account = new Checking(80.0, 3,3);
        Double expected = 40.0;

        //When
        account.withdraw(40.0);

        // Then
        Double actual = account.getBalance();
        assertEquals(expected, actual);
    }


    @Test
    public void getAcctHist() {
    }

    @Test
    public void getOwnerID() {
        // Given
        Account account = new Checking(0.0, 3,3);
        Integer expected = 3;


        // Then
        Integer actual = account.getOwnerID();
        assertEquals(expected, actual);
    }

    @Test
    public void getAcctNum() {
        // Given
        Account account = new Checking(0.0, 3,3);
        Integer expected = 3;


        // Then
        Integer actual = account.getAcctNum();
        assertEquals(expected, actual);

    }


    @Test
    public void setRisk() {
        // Given
        Investment account = new Investment(80000.0, 3,3, 0.09);
        Double expected = 0.9;

        account.setRisk(0.9);

        // Then
        Double actual = account.getRisk();
        assertEquals(expected, actual);
    }
}
