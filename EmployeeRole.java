import java.time.LocalDate;

public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;
    public EmployeeRole(){
        productsDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomerProducts.txt");
    }
    public void addProduct(String productID, String productName, String manufacturerName, String supplierName,int Quantity,float price){
        productsDatabase.insertRecord(new Product(productID,productName,manufacturerName,supplierName,Quantity,price));
        //productsDatabase.SaveToFile();
    }
    public Product[] getListOfProducts(){
        int size = productsDatabase.returnAllRecords().size();
        Product[] products = new Product[size];
        for(int i=0; i< size; i++)
        {
            products[i] = productsDatabase.returnAllRecords().get(i);
        }
        return products;
    }
    public CustomerProduct[] getListOfPurchasingOperations(){
        int size = customerProductDatabase.returnAllRecords().size();
        CustomerProduct[] customerProducts = new CustomerProduct[size];
        for(int i=0; i< size; i++)
        {
            customerProducts[i] = customerProductDatabase.returnAllRecords().get(i);
        }
        return customerProducts;
    }
    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate){
        int quantity = productsDatabase.getRecord(productID).getQuantity();
        if(quantity == 0 || !productsDatabase.contains(productID))
            return false;
        else
        {
            CustomerProduct customerProduct = new CustomerProduct(customerSSN,productID,purchaseDate);
            productsDatabase.getRecord(productID).setQuantity(quantity - 1);
            customerProductDatabase.insertRecord(customerProduct);
            //customerProductDatabase.SaveToFile();
            return true;
        }
    }
    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate,LocalDate returnDate){
        CustomerProduct customerProduct = new CustomerProduct(customerSSN,productID,purchaseDate);
        if(purchaseDate.isAfter(returnDate) || !productsDatabase.contains(productID) || !customerProductDatabase.contains(customerProduct.getSearchKey()) || returnDate.compareTo(purchaseDate) > 14)
            return -1;
        else
        {
            int quantity = productsDatabase.getRecord(productID).getQuantity();
            productsDatabase.getRecord(productID).setQuantity(quantity + 1);
            customerProductDatabase.deleteRecord(customerProduct.getSearchKey());
            //customerProductDatabase.SaveToFile();
            return productsDatabase.getRecord(productID).getPrice();
        }
    }
    public void logout(){
        customerProductDatabase.SaveToFile();
        productsDatabase.SaveToFile();
    }

}
