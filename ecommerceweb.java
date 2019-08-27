import java.util.ArrayList;
import java.util.List;
import java.util.*;
class ecommerceweb {
	public String productName;
	public int price;
	public int quantity;
	public int bill=0;
	public char ex;
	Scanner inp=new Scanner(System.in);
	public ecommerceweb() {}
	public ecommerceweb(String productName,int price,int quantity) {
		this.productName=productName;
		this.price=price;
		this.quantity=quantity;
	}
	public String get_productName() {
		return productName;
	}
	public void set_productName(String productName) {
		this.productName=productName;
	}
	public int get_price() {
		return price;
	}
	public void set_price(int price) {
		this.price=price;
	}
	public int get_quantity() {
		return quantity;
	}
	public void set_quantity(int quantity) {
		this.quantity=quantity;
	}
	public String toString() {
		return "productName : "+productName+"\t price : "+price+"\t quantity :"+quantity;
	}
	void catalogFill(ArrayList<product> catalog){
		catalog.add(0, new product("jeans",500,0));
		catalog.add(1, new product("Shirts",300,0));
		catalog.add(2, new product("joggers",200,0));
		catalog.add(3, new product("t-shirts",400,0));
		catalog.add(4, new product("Shoes",700,0));
		catalog.add(5, new product("kurta",200,0));
		catalog.add(6, new product("boxer",150,0));
		catalog.add(7, new product("socks",90,0));
		catalog.add(8, new product("hand carchief",50,0));
		catalog.add(9, new product("inner ware",200,0));
	}
	void printCatalog(ArrayList<product> catalog){
		for(int p=0;p<catalog.size();p++){
			System.out.println(p+"."+catalog.get(p));
		}
	}
	void add2cart(ArrayList<product> catalog,ArrayList<product> cart) {
		System.out.println("Enter a choice:");
		int ch=inp.nextInt();
		System.out.println("enter the quantity:");
		int q=inp.nextInt();
		//catalog.get(ch).quantity=q;*/
		cart.add(catalog.get(ch));
		if(cart.isEmpty()==false) {
			cart.get(ch).quantity=q;
		}
	}
	void viewCart(ArrayList<product> cart) {
		if(cart.isEmpty()==false) {
			System.out.println("CART:");
			for (product i:cart) {
				System.out.println(i);
			}
		}
		else {
			System.out.println("YOUR CART IS EMPTY ! START SHOPPING !");
		}
	}
	void billAmount(ArrayList<product> cart) {
		if(cart.isEmpty()==false) {
			int len=cart.size();
			for(int x=0;x<len;x++){
				bill+=cart.get(x).price * cart.get(x).quantity;
			}
			System.out.println("Your Bill Amount is :"+bill);
			cart.clear();
		}
		else {
			System.out.println("YOUR CART IS EMPTY ! START SHOPPING !");
		}
	}
	void conditionCheck() {
		System.out.println("Do you want to continue ? Type 'y' to continue,'n' to exit");
        ex=inp.next().charAt(0);
        if(ex=='n' || ex=='N' || ex=='y' || ex=='Y') {
        	if(ex=='n' || ex=='N') {
        		System.out.println("THANK YOU FOR SHOPPING WITH US");
        		System.exit(0);
        	}
    	}
        else{
        	System.out.println("INVALID CHOICE ! WE ARE SORRY TO TERMINATE !");
        }
	}
	public static void main(String[] args) {
		ecommerceweb e=new ecommerceweb();
		ArrayList<product> catalog=new ArrayList<>();
		ArrayList<product> cart=new ArrayList<>();
		e.catalogFill(catalog);
		do{
		System.out.println("Enter a choice:");
		System.out.println("\n 1. OPEN THE CATALOG \n 2. VIEW THE CART \n 3. CHECK OUT \n 4. Exit");
		int choice=e.inp.nextInt();
		switch(choice) {
			case 1:
				e.printCatalog(catalog);
				e.add2cart(catalog,cart);
				break;
			case 2:
				e.viewCart(cart);
				break;
			case 3:
				e.billAmount(cart);
				break;
			case 4:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Choice !");
                break;
		}
		e.conditionCheck();
        } while (e.ex=='y' || e.ex=='Y');
        e.inp.close();
    }

}