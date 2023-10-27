import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductDatabase {
    private ArrayList<Product> records=new ArrayList<Product>();
    private String filename;

    public ProductDatabase(String filename) {
        this.filename = filename;
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
    public Product createRecordFrom(String line)
    {
        String [] taken=line.split(",");
        Product  user = new Product(taken[0], taken[1], taken[2], taken[3], Integer.parseInt(taken[4]), Float.parseFloat(taken[5]));
        return user;
    }
    public ArrayList<Product> returnAllRecords()
    {
        return records;
    }
    public boolean contains(String key)
    {
        return getRecord(key) != null;   //EmployeeUser with id key is found or not;
    }
    public Product getRecord(String key)
    {
        for(Product record: records)
        {
            if(key.equals(record.getSearchKey()))
            {
                return record;  //returns a reference to the EmployeeUser with id = key
            }
        }
        return null; //EmployeeUser with id key is not found
    }
    public void insertRecord(Product record)
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
        Product record = getRecord(key);
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
            for(Product record : records)
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
