import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemStock implements Serializable {
    
    private static final long serialVersionUID = 5796629624496173810L;
    private String StockName;
    private List<Category> Categories;
    private String StockAddress;
    public class Category implements Serializable{
        private static final long serialVersionUID = 1L;
        private String CategoryName;
        private List<Item> items;
        public Category(){
            CategoryName = "Default Category";
            items = new ArrayList<Item>();
        }
        public Category(String name){
            CategoryName = name;
            items = new ArrayList<Item>();
        }
        public String getName(){
            return CategoryName;
        }
        public List<Item> getItemList(){
            return items;
        }
        public void printItemList(){
            for (int i = 0; i < items.size(); i++) {
                Item it = items.get(i);
                System.out.printf("\n"+(i+1)+".\t"+it.getName()+"\n\tRs. "+it.getPrice()+"\tAvailable: "+it.getQty());
            }
            System.out.println();
        }
        
    }
    private void initStock() throws IOException {
        StockName = "Test Stock";
        ObjectInputStream ois;
        try {
            FileInputStream fis = new FileInputStream(StockAddress);
            ois = new ObjectInputStream(fis);
            Categories = (List<Category>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            FileOutputStream f = new FileOutputStream(StockAddress);
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ItemStock(String stockAddress){
        Categories = new ArrayList<Category>();
        StockAddress = stockAddress;
        try {
            initStock();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ItemStock(){
        Categories = new ArrayList<Category>();
        StockAddress = "defaultStock";
    }
    public List<Category> getCategoryList(){
        return Categories;
    }
    public void printCategoryList(){
        for (int i = 0; i < Categories.size(); i++) {
            System.out.printf("\n\t"+(i+1)+". "+Categories.get(i).CategoryName);
        }
        System.out.println();
    }
    public void commitChanges(ItemStock newStock){
        Categories.clear();
        Categories.addAll(newStock.Categories);
        for (int i = 0; i < newStock.Categories.size(); i++) {
            Categories.get(i).items.addAll(newStock.Categories.get(i).items);
        }
    }
    public void addCategory(Category category){
        Categories.add(category);
    }
    public void saveStock() throws IOException{
        FileOutputStream fos = new FileOutputStream(StockAddress);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Categories);
        oos.close();
        fos.close();
    }
    public String getStockName(){
        return StockName;
    }
    public static void main(String[] args) {
        if(args.length == 0)
            return;

        String str = UI.promptFor("Enter Stock Name");
        ItemStock stock = new ItemStock(str);
        int ch=1,i,j;
        while (ch != 0) {

            System.out.printf("\n1. Add Category.\n2. Add Item.\n3. Remove Category.\n4. Remove Item.\n0. Save and Exit.");
            ch = Integer.parseInt(UI.promptFor("Enter Your Choice: "));
            switch (ch) {
                case 1:
                    stock.addCategory(stock.new Category(
                        UI.promptFor("Enter Category Name")
                    ));
                    stock.printCategoryList();
                    break;
                case 2:
                    i=1;
                    stock.printCategoryList();
                    i = Integer.parseInt(UI.promptFor("Select Category of the Item(Press 0 to Add Category)"));
                    if(i == 0)
                        break;
                    str = UI.promptFor("Enter Item Name");
                    double price = Double.parseDouble(UI.promptFor("Enter Price"));
                    int qty = Integer.parseInt(UI.promptFor("Enter Quantity"));
                    stock.getCategoryList().get(i-1).getItemList().add(new Item(str,price,qty));
                    stock.getCategoryList().get(i-1).printItemList();
                    break;
                case 3:
                    i=1;
                    stock.printCategoryList();
                    i = Integer.parseInt(UI.promptFor("Select Category to remove"));
                    stock.getCategoryList().remove(i-1);
                    break;
                case 4:
                    i=1;
                    j=1;
                    stock.printCategoryList();
                    i = Integer.parseInt(UI.promptFor("Select Category of the Item"));
                    stock.getCategoryList().get(i-1).printItemList();
                    j = Integer.parseInt(UI.promptFor("Select Item"));
                    stock.getCategoryList().get(i-1).getItemList().remove(j-1);
                    stock.getCategoryList().get(i-1).printItemList();
                    break;
                case 0:
                    try {
                        stock.saveStock();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                default:
                    break;
            }
        }
    }
}
