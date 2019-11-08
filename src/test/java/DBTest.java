import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

@RunWith(JUnitParamsRunner.class)
public class DBTest {

    @Test
    public void constructorTest() {

        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        try {
            db1 = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String expected = fileName;
        Assert.assertEquals(db1.getFileName(), expected);
        db1.delete();
    }

    @Test
    public void constructorBadRowLengthTest() {

        String fileName = "test.csv";
        DB testDB = null;
        try {
            testDB = new DB(fileName, 7);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer actual = testDB.getRowLength();
        Assert.assertTrue(4 == actual);
    }

    @Test
    public void DBFileNameTest() {
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB testDB = null;
        try {
            testDB = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String actual = testDB.getFileName();
        Assert.assertEquals(testDB.getFileName(), fileName);
        testDB.delete();
    }

    @Test
    public void pathToFileNameTest() {
        String input = "/Users/josh/Desktop/Projects/CR-MacroLabs-OOP-ATM/data/610393892.csv";
        String expected = "610393892.csv";
        Assert.assertEquals(expected, DB.pathToFileName(input));
    }

    @Test
    public void fileNametoPathTest() {
        String input = "610393892.csv";
        String expected = "/Users/josh/Desktop/Projects/CR-MacroLabs-OOP-ATM/data/610393892.csv";
        Assert.assertEquals(expected, DB.fileNameToPath(input, System.getProperty("user.dir")));
    }

    @Test
    public void tempStuff() {
//        String fileName = "test.csv";
//        DB testDB = null;
//        try {
//            testDB = new DB(fileName, 4);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        testDB.addRow(new String[] {"Item 1", "Item 2", "Item 3", "Item 4"});
//        testDB.addRow(new String[] {"Item 1b", "Item 2b", "Item 3b", "Item 4b"});
//        testDB.addRow(new String[] {"Item 1c", "Item 2c", "Item 3c", "Item 4c"});
//        testDB.addRow(new String[] {"Item 1d", "Item 2d", "Item 3d", "Item 4d"});

    }

    @Test
    public void readTestDBTest() {
        String fileName = "test.csv";

        ArrayList<String[]> expected = new ArrayList<>();
        expected.add(new String[] {"Item 1", "Item 2", "Item 3", "Item 4"});
        expected.add(new String[] {"Item 1b", "Item 2b", "Item 3b", "Item 4b"});
        expected.add(new String[] {"Item 1c", "Item 2c", "Item 3c", "Item 4c"});
        expected.add(new String[] {"Item 1d", "Item 2d", "Item 3d", "Item 4d"});

        DB testDB = null;
        try {
            testDB = new DB(fileName, 4);

            ArrayList<String[]> records = testDB.readAllRows();

            for (int i = 0; i < 4; i++) {
                Assert.assertEquals(expected.get(i), records.get(i));
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void printDBTest() {
        String fileName = "test.csv";
        DB testDB = null;
        try {
            testDB = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        testDB.printDB();
    }

    @Test
    public void clearDBTest() {
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        try {
            db1 = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }

        db1.addRow(new String[]{"Sticky", "Icky", "Wicky", "Quicky"});
        db1.clear();

        ArrayList<String[]> records = db1.readAllRows();

        Assert.assertEquals(0,records.size());
        db1.delete();
    }

    @Test
    public void addRowTest() {
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        try {
            db1 = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[] {"Item 1", "Item 2", "Item 3", "Item 4"});
        data.add(new String[] {"Item 1b", "Item 2b", "Item 3b", "Item 4b"});
        data.add(new String[] {"Item 1c", "Item 2c", "Item 3c", "Item 4c"});
        data.add(new String[] {"Item 1d", "Item 2d", "Item 3d", "Item 4d"});

        ArrayList<String[]> records;
        for (int i = 0; i < 4; i++) {
            db1.addRow(data.get(i));
            Assert.assertTrue(i + 1 == db1.length());

            records = db1.readAllRows();

            for (int j = 0; j <= i; j++) {
                //System.out.println(String.format("Rows: %d Testing row: %d", i, j));
                Assert.assertEquals(records.get(i), data.get(i));
            }
        }
        db1.delete();
    }

    @Test
    public void getRowLengthTest() {
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        Integer rowL = random.nextInt(10);
        try {
            db1 = new DB(fileName, rowL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] row = new String[rowL];
        db1.addRow(row);
        Assert.assertTrue(1 == db1.length());

        Assert.assertTrue(rowL == db1.getRowLength());
        db1.delete();
    }

    @Test
    public void getFileNameTest() {
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        Integer rowL = random.nextInt(10);
        try {
            db1 = new DB(fileName, rowL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(fileName == db1.getFileName());
        db1.delete();
    }

    @Test
    public void deleteFileTest() {
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        Integer rowL = random.nextInt(10);
        try {
            db1 = new DB(fileName, rowL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(false == db1.isDeleted());
        db1.delete();
        Assert.assertTrue(true == db1.isDeleted());
    }

    @Test
    public void integrityGoodTest() {
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        try {
            db1 = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[] {"Item 1", "Item 2", "Item 3", "Item 4"});
        data.add(new String[] {"Item 1b", "Item 2b", "Item 3b", "Item 4b"});
        data.add(new String[] {"Item 1c", "Item 2c", "Item 3c", "Item 4c"});
        data.add(new String[] {"Item 1d", "Item 2d", "Item 3d", "Item 4d"});

        ArrayList<String[]> records;
        for (int i = 0; i < 4; i++) {
            db1.addRow(data.get(i));
        }

        Assert.assertEquals(true, db1.checkIntegrity());
        db1.delete();
    }

    @Test
    public void integrityBadRowAddTest() { // check to make sure it won't add an improper length of row
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        try {
            db1 = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[] {"Item 1", "Item 2", "Item 3", "Item 4"});
        data.add(new String[] {"Item 1b", "Item 2b", "Item 3b", "Item 4b"});
        data.add(new String[] {"Item 1c", "Item 2c", "Item 3c"});
        data.add(new String[] {"Item 1d", "Item 2d", "Item 3d", "Item 4d"});

        ArrayList<String[]> records;
        for (int i = 0; i < 4; i++) {
            db1.addRow(data.get(i));
        }

        Assert.assertTrue(3 == db1.readAllRows().size());
        Assert.assertEquals(true, db1.checkIntegrity());
        db1.delete();
    }

    @Test
    public void integrityBadTest() { // check to make sure it won't add an improper length of row

        String fileName = "testBad.csv";

        DB db1 = null;
        try {
            db1 = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(false, db1.checkIntegrity());
    }

    @Test
    public void replaceRowTest() {
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        try {
            db1 = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[] {"Item 1", "Item 2", "Item 3", "Item 4"});
        data.add(new String[] {"Item 1b", "Item 2b", "Item 3b", "Item 4b"});
        data.add(new String[] {"Item 1c", "Item 2c", "Item 3c", "Item 4c"});
        data.add(new String[] {"Item 1d", "Item 2d", "Item 3d", "Item 4d"});

        for (int i = 0; i < 4; i++) {
            db1.addRow(data.get(i));
        }

        String[] replacementRow = new String[] {"Changed Item 1c", "Changed Item 2c", "Changed Item 3c", "Changed Item 4c"};

        db1.replaceRow(2, replacementRow);

        ArrayList<String[]> records = db1.readAllRows();

        for (int i = 0; i < 4; i++) {
            if (i == 2) {
                Assert.assertEquals(replacementRow, records.get(i));
            } else {
                Assert.assertEquals(data.get(i), records.get(i));
            }
        }
        db1.delete();
    }

    @Test
    public void deleteRowTest() {
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        try {
            db1 = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[] {"Item 1", "Item 2", "Item 3", "Item 4"});
        data.add(new String[] {"Item 1b", "Item 2b", "Item 3b", "Item 4b"});
        data.add(new String[] {"Item 1c", "Item 2c", "Item 3c", "Item 4c"});
        data.add(new String[] {"Item 1d", "Item 2d", "Item 3d", "Item 4d"});
        data.add(new String[] {"Item 1e", "Item 2e", "Item 3e", "Item 4e"});

        for (int i = 0; i < data.size(); i++) {
            db1.addRow(data.get(i));
        }

        db1.deleteRow(2);

        ArrayList<String[]> records = db1.readAllRows();

        for (int i = 0; i < 4; i++) {
            if (i >= 2) {
                Assert.assertEquals(data.get(i+1), records.get(i));
            } else {
                Assert.assertEquals(data.get(i), records.get(i));
            }
        }
        db1.delete();
    }

    @Test
    public void readRowTest() {
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        try {
            db1 = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[] {"Item 1", "Item 2", "Item 3", "Item 4"});
        data.add(new String[] {"Item 1b", "Item 2b", "Item 3b", "Item 4b"});
        data.add(new String[] {"Item 1c", "Item 2c", "Item 3c", "Item 4c"});
        data.add(new String[] {"Item 1d", "Item 2d", "Item 3d", "Item 4d"});
        data.add(new String[] {"Item 1e", "Item 2e", "Item 3e", "Item 4e"});

        for (int i = 0; i < data.size(); i++) {
            db1.addRow(data.get(i));
        }

        for (int i = 0; i < 4; i++) {
            Assert.assertEquals(data.get(i), db1.readRow(i));
        }
        db1.delete();
    }

    @Test
    public void serializeTest() {
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        try {
            db1 = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[] {"Item 1", "Item 2", "Item 3", "Item 4"});
        data.add(new String[] {"Item 1b", "Item 2b", "Item 3b", "Item 4b"});
        data.add(new String[] {"Item 1c", "Item 2c", "Item 3c", "Item 4c"});
        data.add(new String[] {"Item 1d", "Item 2d", "Item 3d", "Item 4d"});
        data.add(new String[] {"Item 1e", "Item 2e", "Item 3e", "Item 4e"});

        for (int i = 0; i < data.size(); i++) {
            db1.addRow(data.get(i));
        }

        for (int i = 0; i < 4; i++) {
            Assert.assertEquals(String.join("/",data.get(i)), db1.serialize(i));
        }
        db1.delete();
    }

    @Test
    public void staticSerializeTest() {
        String[] row = new String[] {"Item 1b", "Item 2b", "Item 3b", "Item 4b"};
        Assert.assertEquals(String.join("/",row), DB.serialize(row));
    }

    @Test
    public void partialSerializeTest() {
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        try {
            db1 = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[] {"Item 1", "Item 2", "Item 3", "Item 4"});
        data.add(new String[] {"Item 1b", "Item 2b", "Item 3b", "Item 4b"});
        data.add(new String[] {"Item 1c", "Item 2c", "Item 3c", "Item 4c"});
        data.add(new String[] {"Item 1d", "Item 2d", "Item 3d", "Item 4d"});
        data.add(new String[] {"Item 1e", "Item 2e", "Item 3e", "Item 4e"});

        for (int i = 0; i < data.size(); i++) {
            db1.addRow(data.get(i));
        }

        Assert.assertEquals("Item 1d/Item 2d/Item 4d", db1.partialSerialize(3,new int[] {0,1,3}));
        Assert.assertEquals("Item 3e", db1.partialSerialize(4,new int[] {2}));
        Assert.assertEquals("Item 2e/Item 3e", db1.partialSerialize(4,new int[] {1,2}));

        db1.delete();
    }

    @Test
    public void partialStaticSerializeTest() {
        String[] row = new String[] {"Item 1b", "Item 2b", "Item 3b", "Item 4b"};
        int[] fields = {1,3};
        Assert.assertEquals("Item 2b/Item 4b", DB.partialSerialize(row, fields));
    }

    @Test
    public void findWholeRowTest() {
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        try {
            db1 = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[] {"Item 1", "Item 2", "Item 3", "Item 4"});
        data.add(new String[] {"Item 1b", "Item 2b", "Item 3b", "Item 4b"});
        data.add(new String[] {"Item 1c", "Item 2c", "Item 3c", "Item 4c"});
        data.add(new String[] {"Item 1d", "Item 2d", "Item 3d", "Item 4d"});
        data.add(new String[] {"Item 1e", "Item 2e", "Item 3e", "Item 4e"});

        for (int i = 0; i < data.size(); i++) {
            db1.addRow(data.get(i));
        }

        for (int i = 0; i < data.size(); i++) {
            Assert.assertEquals(i, db1.findWholeRow(data.get(i)));
        }
        db1.delete();
    }

    @Test
    public void findPartialRowTest() {
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        try {
            db1 = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[] {"Item 1", "Item 2", "Item 3", "Item 4"});
        data.add(new String[] {"Item 1b", "Item 2b", "Item 3b", "Item 4b"});
        data.add(new String[] {"Item 1c", "Item 2c", "Item 3c", "Item 4c"});
        data.add(new String[] {"Item 1d", "Item 2d", "Item 3d", "Item 4d"});
        data.add(new String[] {"Item 1e", "Item 2e", "Item 3e", "Item 4e"});

        for (int i = 0; i < data.size(); i++) {
            db1.addRow(data.get(i));
        }

        Assert.assertEquals(3, db1.findPartialRow(new String[] {"Item 1d", "Item 2d", "Item 4d"}, new int[] {0,1,3}));
        Assert.assertEquals(1, db1.findPartialRow(new String[] {"Item 3b"}, new int[] {2}));
        Assert.assertEquals(-1, db1.findPartialRow(new String[] {"Item 3sdsdasdasdb"}, new int[] {2}));
        Assert.assertEquals(4, db1.findPartialRow(new String[] {"Item 1e", "Item 2e", "Item 3e", "Item 4e"}, new int[] {0,1,2,3}));

        db1.delete();
    }

    @Test
    public void findPartialRowMultipleTest() {
        Random random = new Random();
        String fileName = Integer.toString(Math.abs(random.nextInt())) + ".csv";

        DB db1 = null;
        try {
            db1 = new DB(fileName, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[] {"Item 1", "Item", "Item 3", "Item 4"});
        data.add(new String[] {"Item 1b", "Item 2b", "Item 3b", "Item 4b"});
        data.add(new String[] {"Item 1c", "Item 2c", "Item 3c", "Item 4c"});
        data.add(new String[] {"Item 1b", "Item", "Item 3b", "Item 4d"});
        data.add(new String[] {"Item 1e", "Item", "Item 3e", "Item 4e"});

        for (int i = 0; i < data.size(); i++) {
            db1.addRow(data.get(i));
        }

        Assert.assertEquals(1, db1.findPartialRowMultiple(new String[] {"Item 1b", "Item 3b"}, new int[] {0,2})[0]);
        Assert.assertEquals(3, db1.findPartialRowMultiple(new String[] {"Item 1b", "Item 3b"}, new int[] {0,2})[1]);

        Assert.assertEquals(0, db1.findPartialRowMultiple(new String[] {"Item"}, new int[] {1})[0]);
        Assert.assertEquals(3, db1.findPartialRowMultiple(new String[] {"Item"}, new int[] {1})[1]);
        Assert.assertEquals(4, db1.findPartialRowMultiple(new String[] {"Item"}, new int[] {1})[2]);

        Assert.assertEquals((int)0, (int)db1.findPartialRowMultiple(new String[] {"Iteasdm"}, new int[] {1}).length);

        db1.delete();
    }
}