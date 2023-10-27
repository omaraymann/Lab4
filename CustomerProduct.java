import java.time.LocalDate;

public class CustomerProduct implements Record{
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    public CustomerProduct(String customerSSN,String productID,LocalDate purchaseDate){
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
    }
    public String getCustomerSSN(){
        return customerSSN;
    }
    public String getProductID(){
        return productID;
    }
    public LocalDate getPurchaseDate(){
        return purchaseDate;
    }
    public void setCustomerSSN(String customerSSN) {
        this.customerSSN = customerSSN;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    @Override
    public String lineRepresentation(){
        return customerSSN + "," + productID + "," + purchaseDate.getDayOfMonth() + "-"
                + purchaseDate.getMonthValue() + "-" + purchaseDate.getYear();
    }
    @Override
    public String getSearchKey(){
        return customerSSN + "," + productID + "," + purchaseDate.getDayOfMonth() + "-"
                + purchaseDate.getMonthValue() + "-" + purchaseDate.getYear();
    }
}
