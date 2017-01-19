package holland.andres;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void EachAccountHasUniqueID () {
        Account account1 = new Account("Tariq","checking","disabled",0);
        Account account2 = new Account("Uncle Phil","savings","enabled",5);
        int account1Number = account1.getAccountNumber();
        int account2Number = account2.getAccountNumber();
        boolean actual = (account1Number == account2Number);
        assertEquals(false, actual);
    }

    @Test
    public void ChangeNameIfAccountOpen () {
        Account account1 = new Account("Tariq","checking","disabled",0);
        account1.setAccountName("Uncle Phil");
        String expected = "Uncle Phil";
        String actual = account1.getAccountName();
        assertEquals(expected, actual);
    }

    @Test
    public void ChangeNameIfAccountClosed () {
        Account account1 = new Account("Tariq","checking","disabled",0);
        account1.setAccountStatus("closed");
        account1.setAccountName("Uncle Phil");
        String expected = "Tariq";
        String actual = account1.getAccountName();
        assertEquals(expected, actual);
    }

}