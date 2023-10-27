public class Product implements  Record{
    private String productId,productName,manufacturerName,supplierName;
    private int quantity;
    private float price;

    public Product(String productId, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String lineRepresentation()
    {
        return productId+","+productName+","+manufacturerName+","+supplierName+","+quantity+","+price;
    }
    @Override
    public String getSearchKey()
    {
        return productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price =price;
    }
}
