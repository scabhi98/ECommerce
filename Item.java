public class Item {
    private String Name;
    private double Price;
    
    public Item(){
        Name = "";
        Price = 0.0;
    }
    public Item(String name, double price){
        Name = name;
        Price = price;
    }
    public String getName(){
        return Name;
    }
    public double getPrice(){
        return Price;
    }
    public void setName(String name){
        Name = name;
    }
    public void setPrice(double price){
        Price = price;
    }
}