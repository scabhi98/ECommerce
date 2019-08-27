import java.io.IOException;
import java.util.*;
/**
 * DriverProgram
 */
public class DriverProgram {
    public static void main(String[] args) {
        ItemStock stock1 = new ItemStock("Stock1");     //Initiate a Stock
        ItemStock modifiedStock = new ItemStock("Stock1");
        List<ItemStock.Category> catList = stock1.getCategoryList();    //Get Category list for the stock
        int choice = 1;
        UI.Cart activeCart = new UI.Cart();
        activeCart.owner = UI.promptFor("Enter Your Name");
        System.out.println("\t\tWelcome To APKart "+activeCart.owner+ "!");
        while(choice != 0){
            UI.printLine('*');
            if(activeCart.getCartTotal() == 0)
                System.out.println("\nHey! Your cart should not be empty...");
            else{
                if(UI.promptFor("Do You want to Check Out (yes/no)").startsWith("yes")){
                    stock1.commitChanges(modifiedStock);
                    try {
                        stock1.saveStock();
                    } catch (IOException e) {
                        e.printStackTrace();
					}
                    UI.printLine('*');
                    activeCart.printCartItems();
                    UI.printLine('*');
                    System.out.println("\nTotal Amount Payable: " + activeCart.getCartTotal());
                    UI.printLine('*');
                    System.out.println();
                    System.exit(0);
                }
            }
            stock1.printCategoryList();
            int selectedCategoryIndex = Integer.parseInt(UI.promptFor("Choose Category to proceed")) - 1;
            catList.get(selectedCategoryIndex).printItemList();
            System.out.println("\n\t0. To Skip.");
            int selectedItemIndex = Integer.parseInt(UI.promptFor("Choose Item to proceed")) - 1;
            if(selectedItemIndex < 0)
                continue;
            int quantityOfItem = Integer.parseInt(UI.promptFor("Enter Quantity to add in your cart"));
            Item selectedItem = catList.get(selectedCategoryIndex).getItemList().get(selectedItemIndex);
            System.out.println("Selected Item: " + selectedItem.getName() + " x " + quantityOfItem 
            + " Costs: " + (quantityOfItem * selectedItem.getPrice()));
            if(UI.promptFor("Press yes to Add in Cart: ").startsWith("yes"))
                if(activeCart.addItem(selectedItem, quantityOfItem)){
                    System.out.println("Item Added Successfully...");
                    modifiedStock.getCategoryList().get(selectedCategoryIndex).getItemList()
                        .get(selectedItemIndex).reduceQty(quantityOfItem);
                }
                else
                    System.out.println("Item Add Failed...");
            UI.printLine('=');
            activeCart.printCartItems();
        }
    }
}