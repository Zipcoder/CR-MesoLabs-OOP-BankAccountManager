package holland.andres;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChangeNameTest {

    @Test
    public void ChangeNameFromTariqToProfessorT () {
        Account account = new Account("Tariq","checking","disabled",0);
        ChangeName changeName = new ChangeName();
        changeName.change(account, "Professor T.");
        String expected = "Professor T.";
        String actual = account.getAccountName();
        assertEquals(expected, actual);
    }

    @Test
    public void dontChangeName () {
        Account account = new Account("Tariq","checking","disabled",0);
        String expected = "Tariq";
        String actual = account.getAccountName();
        assertEquals(expected, actual);
    }
}