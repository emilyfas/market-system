package storage;

public class Product {
    private String productId;
    private String name;
    private String category;
    private int stock;
    private double price;

    public Product(String productId, String name, String category, int stock, double price) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    protected void addToStock(int n) {

    }

    protected void removeFromStock(int n) {

    }
}
