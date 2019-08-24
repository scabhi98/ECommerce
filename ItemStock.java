import java.util.*;

public class ItemStock {
    
    private String StockName;
    private List<Category> Categories;
    private String StockAddress;
    public class Category{
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
    }
    private void initStock(){
        // Scanner sc = new Scanner(System.in);
        StockName = "Test Stock";
        Category cat = new Category("Electronics");
        Item i = new Item("mi TV M1090i",23330.30);
            System.out.println(i.getName());
        cat.items.add(0,i);
        cat.items.add(new Item("LG TV L390i",24330.30));
        cat.items.add(new Item("LG TV 345L",26543.30));
        Categories.add(cat);
        
        Category cat2 = new Category("Books");
        cat2.items.add(0,new Item("Data Structure and Algorithms",430.30));
        cat2.items.add(new Item("Let Us C",330.30));
        Categories.add(cat2);
        
        // sc.close();
    }
    public ItemStock(String stockAddress){
        Categories = new ArrayList<Category>();
        StockAddress = stockAddress;
        initStock();
    }
    public ItemStock(){
        Categories = new ArrayList<Category>();
        StockAddress = "defaultStock";
        initStock();
    }
    public List<Category> getCategoryList(){
        return Categories;
    }
}
