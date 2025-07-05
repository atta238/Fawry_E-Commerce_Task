import java.time.LocalDate;

public class Product implements Shippable {
    private String name;
    private double price;
    private int quantity;
    private double weight=0;
    private boolean shippable;
    private boolean expirable;
    private LocalDate expiryDate=null;
    public Product(String name, double price, int quantity,boolean shippable, boolean expirable) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.shippable = shippable;
        this.expirable = expirable;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public double getWeight() {
        return weight;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setWeight(double weight) {
        if(shippable) {
            this.weight = weight;
        }
    }
    public void setExpiryDate(LocalDate expiryDate) {
        if (expirable) {
            this.expiryDate = expiryDate;
        }
    }
    public boolean expired() {
        return expirable && expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }

    public boolean isShippable() {
        return shippable;
    }
}
