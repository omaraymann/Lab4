import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Database {
    private String filename;
    private ArrayList<Record> records=new ArrayList<Record>();
    private Record user;
    public Database(String filename) {
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
    abstract Record createRecordFrom(String line);
    public ArrayList<Record> returnAllRecords()
    {
        return records;
    }
    public boolean contains(String key)
    {
        return getRecord(key) != null;
    }
    public Record getRecord(String key)
    {
        for(Record record: records)
        {
            if(key.equals(record.getSearchKey()))
            {
                return record;  //returns a reference to the EmployeeUser with id = key
            }
        }
        return null; //EmployeeUser with id key is not found
    }
    public void insertRecord(Record record)
    {
        if(record != null && !contains(record.getSearchKey()))
        {
            records.add(record);
        }
    }
    public void deleteRecord(String key)
    {
        Record record = getRecord(key);
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
            for(Record record : records)
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
