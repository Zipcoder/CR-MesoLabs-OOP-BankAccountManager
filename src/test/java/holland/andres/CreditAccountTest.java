package holland.andres;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreditAccountTest {

    @Test
    public void CreditOpenAccount () {
        Account account1 = new Account("Tariq","checking","disabled",0);
        CreditAccount creditAccount = new CreditAccount();
        boolean actual = creditAccount.credit(account1, 10);
        assertEquals(true, actual);
    }

    @Test
    public void CreditClosedAccount () {
        Account account1 = new Account("Tariq","checking","disabled",0);
        CreditAccount creditAccount = new CreditAccount();
        account1.setAccountStatus("closed");
        boolean actual = creditAccount.credit(account1, 10);
        assertEquals(false, actual);
    }

    @Test
    public void CreditFrozenAccount () {
        Account account1 = new Account("Tariq","checking","disabled",0);
        CreditAccount creditAccount = new CreditAccount();
        account1.setAccountStatus("frozen");
        boolean actual = creditAccount.credit(account1, 10);
        assertEquals(false, actual);
    }



}