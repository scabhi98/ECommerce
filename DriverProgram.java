import java.util.*;
/**
 * DriverProgram
 */
public class DriverProgram {
    public static void main(String[] args) {
        ItemStock stock1 = new ItemStock();     //Initiate a Stock
        Scanner sc = new Scanner(System.in);
        List<ItemStock.Category> catList = stock1.getCategoryList();    //Get Category list for the stock
        int i=0;
        for (ItemStock.Category cat : catList) {       //For each category in Category List
            i++;
            System.out.println(i+ ". "+cat.getName());      //Print the Category
        }
        System.out.println("Enter your Choice: ");
        int choice = sc.nextInt();                          //Input for Category Selection
        System.out.println("Items under this Category: ");
        ItemStock.Category selectedCategory = catList.get(choice - 1);  //Selected Category extraction
        i = 0;
        for (Item item : selectedCategory.getItemList()) {      //For each item in Selected Category
            i++;
            System.out.println(i+ ". "+item.getName()+" : Rs. "+item.getPrice());   //Print its Name and Price
        }
        sc.close();
    }
}