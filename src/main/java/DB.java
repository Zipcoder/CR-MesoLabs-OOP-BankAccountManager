import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DB {

    private String fileName;
    private String path;
    private Integer rowLength;
    private Boolean deleted;

    /**
     * DB object constructor
     *
     * Creates a DB object - connects to an existing file or creates an empty file
     *
     * @param fileName String: desired csv filename (with extension)
     * @param rowLength Integer: the length of each row in the DB; if it conflicts with data in an existing file, will be overridden
     * @throws IOException
     */
    public DB(String fileName, Integer rowLength) throws IOException {
        this.fileName = fileName;
        this.path = fileNameToPath(this.fileName, System.getProperty("user.dir"));
        this.rowLength = rowLength;
        this.deleted = false;

        try { // look for an existing file with that name
            Reader reader = Files.newBufferedReader(Paths.get(this.path));
            CSVReader csvReader = new CSVReader(reader);

            // get all records at once, use that rowLength to override given one, if nec.
            List<String[]> records = csvReader.readAll();
            if (records.size() > 0) {
                 this.rowLength = records.get(0).length;
            }

        }
        catch(NoSuchFileException e) { // make a new file, if one doesn't exist in place
            File file = new File(this.path);
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * isDeleted
     *
     * Returns true if the DB file has been deleted - called in many methods to prevent trying to access deleted file
     *
     * @return Boolean: deleted status
     */
    public Boolean isDeleted() {
        return deleted;
    }

    /**
     * getFileName
     *
     * Returns the file name of the DB (not the full path)
     *
     * @return String fileName
     */
    public String getFileName() {
        if (!this.deleted) {
            return this.fileName;
        } else {
            return null;
        }
    }

    /**
     * pathToFileName (static)
     *
     * removes last / and everything before from a string, to get the filename
     *
     * @param Path String: the path to some file
     * @return String: fileName
     */
    public static String pathToFileName (String Path) {
        String[] splitter = Path.split("/");
        return splitter[splitter.length - 1];
    }

    /**
     * fileNameToPath (static)
     *
     * given a file name, append the path to the data folder
     *
     * @param FN String: the file name
     * @param PWD String: present working directory String
     * @return
     */
    public static String fileNameToPath (String FN, String PWD) {
        return PWD + "/data/" + FN;
    }

    /**
     * length
     *
     * Determine the length of this DB
     *
     * @return Integer: length
     */
    public Integer length() {
        if (!this.deleted) {
            return readAllRows().size();
        } else {
            return null;
        }
    }

    /**
     * getRowLength
     *
     * Return the length of rows in this DB
     *
     * @return Integer: rowLength
     */
    public Integer getRowLength() {
        if (!this.deleted) {
            return this.rowLength;
        } else {
            return null;
        }
    }

    /**
     * checkIntegrity
     *
     * Return true if the DB has rows all of the same length, which is the rowLength property
     *
     * @return Boolean
     */
    public Boolean checkIntegrity() {
        if (!this.deleted) {
            ArrayList<String[]> records = readAllRows();

            for (String[] row : records) {
                if (row.length != this.rowLength) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * addRow
     *
     * Input a String[] that is a new row to add; if it is the proper length, it is added to the DB
     *
     * @param row String[]: the row to be input
     */
    public void addRow(String[] row) {
        if (!this.deleted) {
            try {
                File file = new File(this.path);
                FileWriter outputfile = new FileWriter(file, true);

                // create CSVWriter object filewriter object as parameter
                CSVWriter writer = new CSVWriter(outputfile);
                if (row.length == this.rowLength) { // only write if this row is the correct length
                    writer.writeNext(row);
                }

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * replaceRow
     *
     * Replaces a row (identified by index) with an input String[]; must be correct size array
     *
     * @param rowNum int: which row to replace
     * @param replacement String[]: the row with which to replace it
     */
    public void replaceRow(int rowNum, String[] replacement) {
        ArrayList<String[]> records = null;
        if (!this.deleted && rowNum < length() && replacement.length == this.rowLength) {
            records = readAllRows();
            records.set(rowNum, replacement);
        }
        clear();
        for (String[] row : records) {
            addRow(row);
        }
    }

    /**
     * deleteRow
     *
     * Delete an existing row, by index
     *
     * @param rowNum int: the row index to be deleted
     */
    public void deleteRow(int rowNum) {
        ArrayList<String[]> records = null;
        if (!this.deleted && rowNum < length()) {
            records = readAllRows();
            records.remove(rowNum);
        }
        clear();
        for (String[] row : records) {
            addRow(row);
        }
    }

    /**
     * readRow (by index)
     *
     * Return the ith row
     *
     * @param rowNum int: the row number
     * @return String[] row: the desired row
     */
    public String[] readRow(int rowNum) {
        ArrayList<String[]> records = null;
        if (!this.deleted && rowNum < length() && rowNum >= 0) {
            records = readAllRows();
            return records.get(rowNum);
        } else if (rowNum == -1) {
            return null;
        }
        return new String[this.rowLength];
    }

    /**
     * findWholeRow (by row)
     *
     * Returns the index of an entire row which is input
     *
     * @param row String[]: the row to find
     * @return int rowNum: the index of that row
     */
    public int findWholeRow (String[] row) {
        if (!this.deleted) {
            String[] expected;
            for (int i = 0; i < this.length(); i++) {
                if (this.serialize(row).equals(this.serialize(i))) {
                    return i;
                }
            }
            return -1;
        } else {
            return -1;
        }
    }

    /**
     * findPartialRow
     *
     * Find a row's index, given a set of field values and numbers
     *
     * @param fragment String[]: an array of field values to find within a row
     * @param fields int[]: the field positions that correspond with those values
     * @return int rowNum: the index of the row
     */
    public int findPartialRow (String[] fragment, int[] fields) {
        if (!this.deleted) {
            String signature = serialize(fragment);
            for (int i = 0; i < this.length(); i++) {
                if (signature.equals(this.partialSerialize(i,fields))) {
                    return i;
                }
            }
            return -1;
        } else {
            return -1;
        }
    }

    /**
     * findPartialRowMultiple
     *
     * Find a set of row indices, given a set of field values and numbers to match.
     * Matches first result, if multiple are present
     *
     * @param fragment String[]: an array of field values to find within a row
     * @param fields int[]: the field positions that correspond with those values
     * @return int[] rowNums: the indices of the rows
     */
    public int[] findPartialRowMultiple (String[] fragment, int[] fields) {
        if (!this.deleted) {
            String signature = serialize(fragment);
            ArrayList<Integer> matchedRows = new ArrayList<Integer>();
            for (int i = 0; i < this.length(); i++) {
                if (signature.equals(this.partialSerialize(i,fields))) {
                    matchedRows.add(i);
                }
            }

            if (matchedRows.size() > 0) {
                int[] result = new int[matchedRows.size()];
                for (int i = 0; i < matchedRows.size(); i++ ) {
                    result[i] = matchedRows.get(i);
                }
                return result;
            } else {
                return new int[0];
            }
        } else {
            return null;
        }
    }

    /**
     * serialize (object)
     *
     * Take any row, by index, and return a string representation
     *
     * @param rowNum int: the row to serialize
     * @return String: serial representation
     */
    public String serialize(int rowNum) {
        if (!this.deleted) {
            return DB.serialize(this.readRow(rowNum));
        } else {
            return null;
        }
    }

    /**
     * partialSerialize (object)
     *
     * Turn part of a row into a string representation
     *
     * @param rowNum int: the row to serialize
     * @param fields int[]: which fields from the row to serialize
     * @return String: serialized representation of those fields
     */
    public String partialSerialize(int rowNum, int[] fields) {
        if (!this.deleted) {
            return DB.partialSerialize(this.readRow(rowNum),fields);
        } else {
            return null;
        }
    }

    /**
     * serialize (static)
     *
     * Take any String [] and return a string representation
     *
     * @param row String[]: the 'row' to serialize
     * @return String: serial representation
     */
    public static String serialize(String[] row) {
        return String.join("/", row);
    }

    /**
     * partialSerialize (static)
     *
     * Turn part of a String[] into a string representation
     *
     * @param fragment String[]: an array of field values to serialize
     * @param fields int[]: the field positions that correspond with those values
     * @return String: serialized representation of those fields
     */
    public static String partialSerialize(String[] fragment, int[] fields) {
        String[] partialArray = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            partialArray[i] = fragment[fields[i]];
        }
        return String.join("/", partialArray);
    }

    /**
     * readAllRows
     *
     * Return an ArrayList of all of the rows in the DB
     *
     * @return ArrayList of String[] arrays: the data
     */
    public ArrayList<String[]> readAllRows() {
        if (!this.deleted) {
            try { // look for an existing file with that name
                Reader reader = Files.newBufferedReader(Paths.get(this.path));
                CSVReader csvReader = new CSVReader(reader);

                // get all records at once
                List<String[]> records = csvReader.readAll();
                // loop through records
                return new ArrayList<String[]>(records);

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }

    }

    /**
     * printDB
     *
     * For debugging: print out the whole DB
     *
     */
    public void printDB() {
        if (!this.deleted) {
            for (String[] row : readAllRows()) {
                System.out.println(serialize(row));
            }
        }
    }

    /**
     * clear
     *
     * Method to clear the DB
     *
     */
    public void clear() {
        if (!this.deleted) {
            try {
                File file = new File(this.path);
                FileWriter outputfile = new FileWriter(file);

                // create CSVWriter object filewriter object as parameter
                CSVWriter writer = new CSVWriter(outputfile);

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * delete
     *
     * Method to delete the DB file
     *
     */
    public void delete() {

        File file = new File(this.path);
        file.delete();
        this.deleted = true;

    }


}
