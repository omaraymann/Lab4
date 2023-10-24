import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Assist {
    abstract void  readFromFile();
    abstract EmployeeUser createRecordFrom(String line);
    abstract ArrayList<EmployeeUser> returnAllRecords();
    abstract boolean contains(String key);
    abstract EmployeeUser getRecord(String key);
    abstract void insertRecord(EmployeeUser record);
    abstract void deleteRecord(String key);
    abstract void SaveToFile();
}
public class EmployeeUserDatabase extends Assist {
    private String filename;
    private  ArrayList <EmployeeUser> records=new ArrayList<EmployeeUser>();
    private EmployeeUser user;
    public EmployeeUserDatabase(String filename) {
        this.filename = filename;
        readFromFile();
    }
    public void  readFromFile()
    {
        try {
            File file = new File( filename );
            Scanner myReader = new Scanner( file );
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                records.add(createRecordFrom(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public EmployeeUser createRecordFrom(String line)
    {
        String [] taken=line.split(",");
        user = new EmployeeUser(taken[0],taken[1],taken[2],taken[3],taken[4]);
        return user;
    }
    public ArrayList<EmployeeUser> returnAllRecords()
    {
        return records;
    }
    public boolean contains(String key)
    {
        return getRecord(key) != null;
    }
    public EmployeeUser getRecord(String key)
    {
        for(EmployeeUser record: records)
        {
            if(key.equalsIgnoreCase(record.getSearchKey()))
            {
                return record;  //returns a reference to the EmployeeUser with id = key
            }
        }
        return null; //EmployeeUser with id key is not found
    }
    public void insertRecord(EmployeeUser record)
    {
        if(record != null && !contains(record.getSearchKey()))
        {
            records.add(record);
        }
    }
    public void deleteRecord(String key)
    {
        EmployeeUser record = getRecord(key);
        if(record != null)
        {
            records.remove(record);
        }
    }
    public void SaveToFile()
    {
        int recordsCapacity = records.size();
        try( FileWriter file = new FileWriter(filename) )
        {
            for(EmployeeUser record : records)
            {
                if(records.indexOf(record) == recordsCapacity -1 )
                {
                    file.write(record.lineRepresentation());
                }
                else
                {
                    file.write(record.lineRepresentation() + "\n");
                }
            }
        }catch (IOException e){
            System.out.println("An error occurred.");
        }
    }
}
