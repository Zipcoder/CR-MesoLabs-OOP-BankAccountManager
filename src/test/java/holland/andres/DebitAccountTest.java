package holland.andres;

import org.junit.Test;

import static org.junit.Assert.*;

public class DebitAccountTest {

    @Test
    public void NoDebitIfBalanceWouldGoNegativeAndOverdraftEnabled () {
        Account account1 = new Account("Tariq","checking","enabled",0);
        DebitAccount debitAccount = new DebitAccount();
        boolean actual = debitAccount.debit(account1, 10);
        assertEquals(false, actual);
    }

    @Test
    public void DebitIfBalanceWouldGoNegativeAndOverdraftDisabled () {
        Account account1 = new Account("Tariq","checking","disabled",0);
        DebitAccount debitAccount = new DebitAccount();
        boolean actual = debitAccount.debit(account1, 10);
        assertEquals(true, actual);
    }

    @Test
    public void DebitOpenAccount () {
        Account account1 = new Account("Tariq","checking","disabled",0);
        DebitAccount debitAccount = new DebitAccount();
        boolean actual = debitAccount.debit(account1, 10);
        assertEquals(true, actual);
    }

    @Test
    public void DebitClosedAccount () {
        Account account1 = new Account("Tariq","checking","disabled",0);
        DebitAccount debitAccount = new DebitAccount();
        account1.setAccountStatus("closed");
        boolean actual = debitAccount.debit(account1, 10);
        assertEquals(false, actual);
    }

    @Test
    public void DebitFrozenAccount () {
        Account account1 = new Account("Tariq","checking","disabled",0);
        DebitAccount debitAccount = new DebitAccount();
        account1.setAccountStatus("frozen");
        boolean actual = debitAccount.debit(account1, 10);
        assertEquals(false, actual);
    }

}