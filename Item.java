import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 8288994592435695535L;
    private String Name;
    private double Price;
    private int qty;
    
    public Item(){
        Name = "";
        Price = 0.0;
    }
    public Item(String name, double price, int qty){
        Name = name;
        Price = price;
        this.qty = qty;
    }
    public String getName(){
        return Name;
    }
    public double getPrice(){
        return Price;
    }
    public int getQty(){
        return qty;
    }
    public void setName(String name){
        Name = name;
    }
    public void setPrice(double price){
        Price = price;
    }
    public void setQty(int Qty){
        qty = Qty;
    }
    public void reduceQty(int qty){
        this.qty -= qty;
    }
}