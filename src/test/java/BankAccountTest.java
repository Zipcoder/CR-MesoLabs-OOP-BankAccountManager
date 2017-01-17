import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gerrodmozeik on 1/17/17.
 */
public class BankAccountTest {

    @Test
    public void setAccountTypeTEST() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountType(AccountType.SAVINGS);
        AccountType expected = AccountType.SAVINGS, actual = AccountType.SAVINGS;
        assertEquals(expected, actual);
    }

    @Test
    public void changeAccountHolderNameForClosedAccountTest() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.changeAccountHolderName("BOB");
        bankAccount.setAccountStatus(AccountStatus.CLOSED);
        bankAccount.changeAccountHolderName("STEVE");
        String expected = "BOB", actual = bankAccount.getAccountHolderName();
        assertEquals(expected, actual);
    }

    @Test
    public void changeAccountHolderNameForFrozenAccountTest() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.changeAccountHolderName("BOB");
        bankAccount.setAccountStatus(AccountStatus.FROZEN);
        bankAccount.changeAccountHolderName("STEVE");
        String expected = "BOB", actual = bankAccount.getAccountHolderName();
        assertEquals(expected, actual);
    }

    @Test
    public void changeAccountHolderNameForOpenAccountTest() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.changeAccountHolderName("BOB");
        bankAccount.changeAccountHolderName("STEVE");
        String expected = "STEVE", actual = bankAccount.getAccountHolderName();
        assertEquals(expected, actual);
    }

    @Test
    public void setAccountStatus() {

    }

    @Test
    public void getBalance(){

    }

    @Test
    public void creditAccount() {

    }

    @Test
    public void debitAccount() {

    }

    @Test
    public void writeToLog() {

    }

}