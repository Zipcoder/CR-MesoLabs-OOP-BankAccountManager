package holland.andres;

import org.junit.Test;

import static org.junit.Assert.*;

public class CloseAccountTest {
    @Test
    public void CloseAccount () {
        Account account = new Account("name","checking","disabled",0);
        CloseAccount closeAccount = new CloseAccount();
        closeAccount.close(account);
        String expected = "closed";
        String actual = account.getAccountStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void  DefaultAccountStatus() {
        Account account = new Account("name","checking","disabled",0);
        String expected = "open";
        String actual = account.getAccountStatus();
        assertEquals(expected, actual);
    }
}