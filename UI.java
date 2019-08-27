import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    public static String promptFor(String message) {
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.printf( message + ": ");
        String str = sc.nextLine();
        if(str.startsWith("exit"))
            System.exit(0);
        return str;
    }
    public static class Cart{
        private List<Item> cartItems;
        public String owner;
        public Cart(){
            cartItems = new ArrayList<Item>();
            owner = "";
        }
        public void printCartItems(){
            for (int i = 0; i < cartItems.size(); i++) {
                Item it = cartItems.get(i);
                System.out.printf("\n"+(i+1)+".\t"+it.getName()+"\tRs. "+it.getPrice()+" x "+it.getQty());
            }
            System.out.println();
        }
        public boolean addItem(Item item, int quantity){
            boolean found = false;
            int totalQty = quantity;
            for (Item it : cartItems) {
                if(it.getName() == item.getName()){
                    totalQty += it.getQty();
                    if(totalQty <= item.getQty())
                        it.setQty(totalQty);
                    found = true;
                    break;
                }
            }
            if(totalQty > item.getQty()){
                System.out.println("We are regreted to inform that we don't have sufficient stock for quantity requested.");
                System.out.println("Skipping...");
                return false;
            }
            else if(!found)
                return cartItems.add(new Item(item.getName(),item.getPrice(),totalQty));
            return true;
        }
        public Item removeItem(int index){
            return cartItems.remove(index);
        }
        public double getCartTotal(){
            double sum = 0;
            for (Item it : cartItems) 
                sum += it.getPrice() * it.getQty();
            return sum;
        }
        public List<Item> getItemList(){
            return cartItems;
        }
    }
    public static void printLine(char ch){
        for (int i = 0; i < 80; i++) 
            System.out.print(ch);
    }
}