import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer1=new Customer("John Doe",1000);

        Product cheese=new Product("Cheese",100,50,true,true);
        cheese.setWeight(200);
        cheese.setExpiryDate(LocalDate.of(2025, 7, 10));
        Product TV=new Product("TV",10000,20,true,false);
        TV.setWeight(5000);
        Product biscuits=new Product("Biscuits",10,100,true,true);
        biscuits.setWeight(700);
        biscuits.setExpiryDate(LocalDate.of(2025, 8, 23));
        Product scratch_cards=new Product("Scratch Cards",25,200,false,false);

        customer1.add(cheese,2);
        customer1.add(biscuits,1);
        customer1.add(scratch_cards,2);
        customer1.checkout();
    }
}