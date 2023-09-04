package menu;
import java.sql.*;
import java.util.Scanner;
interface Imenu {
	void createMenu(String itemName,String category,double price, String counterId);
	void displayMenu();
	/*To Update the menu: it will cHeck the itemId of the menu and will update
	 *The Database with the updated item Name,category and Price. */
	void updateMenu(int itemId,String itemName,String category,double price);
	void deleateMenu(int itemId);
}

public class Menu_Management implements Imenu {
	
	static PreparedStatement ps;
	public static Connection getConnect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/foodcourt", "root", "root");
		}
	
	//For Id of item in Menu
	private int itemId;
	//For Name of item in Menu
    private String itemName;
    //For Category of item in Menu
    private String category;
    //For Price of item in Menu
    private double price;
    //For Counter ID
    private String counterId;
    
    //Constructor of Menu_Management    
    public Menu_Management() {
    	
    }
    public Menu_Management(int itemId, String itemName, String category, double price, String counterId) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.counterId = counterId;
    }

	@Override
	public void createMenu(String itemName, String category, double price, String counterId) {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("INSERT INTO menu (itemName,category,price,counterId) values (?,?,?,?);");
//			ps.setString(1, this.itemName);
//			ps.setString(2, this.category);
//			ps.setDouble(3, this.price);
//			ps.setString(4, this.counterId);
			ps.setString(1, itemName);
			ps.setString(2, category);
			ps.setDouble(3, price);
			ps.setString(4, counterId);
			
			int temp = ps.executeUpdate();
			System.out.println("Record inserted Successfully");
			System.out.println(temp+" row/s affected");
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void displayMenu() {
		// TODO Auto-generated method stub
		Connection con;
		try {
			con = getConnect();
			PreparedStatement ps = con.prepareStatement("select * from menu");
			ResultSet rs = ps.executeQuery();
			System.out.println("Displaying all details");
			while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +rs.getString(3)+ " " +rs.getDouble(4)+ " " + rs.getString(5));
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateMenu(int itemId, String itemName, String category, double price) {
		// TODO Auto-generated method stub
		Connection con;
		try {
			con = getConnect();
			String str="update menu set itemName=?,category=?,price=? where itemId=?";
			PreparedStatement ps = con.prepareStatement(str);
			ps.setString(1,itemName);
			ps.setString(2,category);
			ps.setDouble(3,price);
			ps.setInt(4,itemId);
			int x=ps.executeUpdate();
			if(x==1)
			{
				System.out.println("updated data successfully");
			}
			else {
				System.out.println("not found");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleateMenu(int itemId) {
		// TODO Auto-generated method stub
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("delete from menu where itemId=? ");
			ps.setInt(1, itemId);
			int temp = ps.executeUpdate();
			if (temp == 1) {
				System.out.println("Deleted the Record Successfully");
			}
			else {
				System.out.println("Data Not Found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getCounterId() {
		return counterId;
	}

	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}

	public static void main(String[] args) {
//		Menu_Management m = new Menu_Management();
		Menu_Management m = new Menu_Management(201,"burger","veg",176,"c1");
		Scanner sc = new Scanner(System.in);
		int choice;
		System.out.println("Enter your choice:");
		do {
			System.out.println("1.Insert the Menu");
			System.out.println("2.Displaying the Menu");
			System.out.println("3.Updating the Menu");
			System.out.println("4.Delete the Menu");
			System.out.println("5.Exit");
			choice = sc.nextInt();
			switch (choice) {
				case 1:
					//createRecord();
					System.out.println();
					m.createMenu("Pizza", "veg", 300, "c1");
					System.out.println();
					break;
				case 2:
					//displayRecord();
					System.out.println();
					m.displayMenu();
					System.out.println();
					break;
				case 3:
					//updateRecord();
					m.updateMenu(106, "churma", "non veg", 220);
					break;
				case 4:
					//deleteRecord();
					m.deleateMenu(104);
					break;
				case 5:
					System.exit(0);
				default:
					System.out.println("INVALID CHOICE -> Exiting..");
					System.exit(0);
					}
			} while (choice != 5);

	}

}
