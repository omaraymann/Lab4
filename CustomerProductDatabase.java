import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerProductDatabase extends Database{
    private ArrayList<CustomerProduct> records=new ArrayList<CustomerProduct>();
    private String filename;

    public CustomerProductDatabase(String filename) {
        super(filename);
        readFromFile();
    }
    public void  readFromFile() {
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
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
    public CustomerProduct  createRecordFrom(String line)
    {
        String [] taken=line.split(",");
        String [] s = taken[2].split("-");
        return  new CustomerProduct(taken[0], taken[1],LocalDate.of(Integer.parseInt(s[2]),Integer.parseInt(s[1]),Integer.parseInt(s[0])));
    }
    public ArrayList<CustomerProduct> returnAllRecords()
    {
        return records;
    }
    public boolean contains(String key)
    {
        return getRecord(key) != null;   //EmployeeUser with id key is found or not;
    }
    public CustomerProduct getRecord(String key)
    {
        for(CustomerProduct record: records)
        {
            if(key.equals(record.getSearchKey()))
            {
                return record;  //returns a reference to the EmployeeUser with id = key
            }
        }
        return null; //EmployeeUser with id key is not found
    }
    public void insertRecord(CustomerProduct record)
    {
        if(record != null&&!contains(record.getSearchKey()))
        {
            records.add(record);
            System.out.println("record is added successfully");
        }
        else
            System.out.println("record might be null or already exists");
    }
    public void deleteRecord(String key)
    {
        CustomerProduct record = getRecord(key);
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
            for(CustomerProduct record : records)
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
