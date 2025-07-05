import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {
    private String name;
    private double balance;
    private Map<Product,Integer> cart=new HashMap<>();
    Customer(String name , double balance){
        this.name=name;
        this.balance=balance;
    }
    public String getName(){
        return name;
    }
    public void add_balance(double balance){
        this.balance+=balance;
    }
    public double getBalance(){
        return balance;
    }
    public void pay(double price){
        balance-=price;
    }
    public Map<Product, Integer> getCart() {
        return cart;
    }
    public void add(Product p, int qty) {
        if(qty<=0){
            System.out.println("Can't put quantity <= 0");
        }
        else if(p.getQuantity()<qty){
            System.out.println("Error: "+p.getQuantity()+" only in stock for "+p.getName());
        }
        else {
            cart.put(p, qty);
        }
    }
    public void remove(Product p) {
        cart.remove(p);
    }
    public void update_qty(Product p, int qty) {
        if(qty==0){
            cart.remove(p);
        }
        else if(qty<0){
            System.out.println("Can't put quantity less than 0");
        }
        else if(p.getQuantity()<qty){
            System.out.println("Error: "+p.getQuantity()+" only in stock for "+p.getName());
        }
        else{
            cart.put(p, qty);
        }
    }


    public void checkout(){
        if(cart.isEmpty()){
            System.out.println("Error: Cart is empty");
            return;
        }
        for (Product p : cart.keySet()) {
            if(p.expired()){
                System.out.println("Error: "+p.getName()+"is expired");
                return;
            }
        }
        List<Shippable>Shippables=new ArrayList<>();
        double sub_total=0;
        for(Product p : cart.keySet()) {
            if (p.isShippable()) {
                Shippables.add(p);
            }
            sub_total += p.getPrice()*cart.get(p);
        }
        double shipping_cost=shipping_service(Shippables);
        double Total_price=sub_total+shipping_cost;

        if(Total_price>balance){
            System.out.println("Error:Your balance is insufficient");
            System.out.println("Balance: "+balance);
            System.out.println("Total: "+(sub_total+shipping_cost));
            return;
        }
        System.out.println();
        System.out.println("        ** Checkout receipt **");
        System.out.println("Quantity    Product     Unit Price     Total Price");
        for(Product p : cart.keySet()){
            int qty=cart.get(p);
            double price=p.getPrice();
            System.out.println(qty + "X          " + p.getName() + "        " + price + "$          "+ price * qty + "$");
        }
        System.out.println("----------------------");
        System.out.println("Subtotal: "+sub_total);
        System.out.println("Shipping: "+shipping_cost);
        System.out.println("Total: "+Total_price);
        pay(Total_price);
        cart.clear();
    }

    private double shipping_service(List<Shippable> shippables){
        System.out.println("        ** Shipment notice **");
        System.out.println("Quantity    Product        Unit Weight     Total Weight");
        double total_weight=0;
        for (Shippable item : shippables) {
            int qty = cart.get(item);   // to be accessed only once
            double weight = item.getWeight();            // to be accessed only once
            System.out.println(qty + "X          " + item.getName() + "        " + weight + "g          "+ weight * qty + "g");
            total_weight += weight * qty;
        }
        System.out.println();
        total_weight/=1000;
        System.out.println("Total package weight: " + total_weight + "Kg");
        System.out.println("Shipping cost: $10 per kilogram.");
        //double shipping_cost = total_weight*10;
        return total_weight*10;
    }
}
