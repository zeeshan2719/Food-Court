package order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

public class Order_items {
	
	public Order_items() {
		super();
	}
	private int orderItemId;
	private int orderId;
	private int ItemId;
    private int quantity;
    
    Scanner sc= new Scanner(System.in);

	public Order_items(int orderItemId, int orderId, int itemId, int quantity) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		ItemId = itemId;
		this.quantity = quantity;
	}
	static PreparedStatement ps;
	public static Connection getConnect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/foodcourt", "root", "root");
		}
	
	public void PlaceItem(int orderId,int ItemId,int quantity) {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("INSERT INTO OrderItem (orderId,itemId,quantity) VALUES (?,?,?)");
			ps.setInt(1, orderId);
			ps.setInt(2, ItemId);
			ps.setInt(3, quantity);
			int temp = ps.executeUpdate();
			if (temp ==1) {
				System.out.println("...Item Added to Cart...");
			}
			else {
				System.out.println("...Item Not Added...");
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}
	
	public void DeleateItem(int Id) {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("delete from OrderItem where orderItemId=?");
			ps.setInt(1, Id);
			int temp = ps.executeUpdate();
			if (temp == 1) {
				System.out.println("...Deleted the Item...");
			}
			else {
				System.out.println("...Data Not Found...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}
	// Display menu name and quantity
	public double DisplayItem(int orderId) {
		double totalMoney = 0;
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("SELECT OI.orderItemId, M.itemName, M.category, OI.quantity,(M.price * OI.quantity) \r\n"
														+ "FROM Menu M JOIN OrderItem OI ON M.itemId = OI.itemId WHERE orderId=?;");
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)+ " "+ rs.getInt(4)+" "+ rs.getInt(5));
				totalMoney += rs.getInt(5);
			}
//			System.out.println("...Select The Counter You want to Order...");
			ps.close();
			con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return totalMoney;
	}
	
	public void updateQuantity(Order_items obj) {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("update OrderItem set quantity=? where orderItemId=?");
			System.out.println("Enter the new Quantity: ");
			int quantity = sc.nextInt();
			ps.setInt(1, quantity);
			ps.setInt(2, this.orderItemId);
			int temp = ps.executeUpdate();
			if (temp == 1) {
				System.out.println("...Quantity Updated...");
			}
			else {
				System.out.println("...Data Not Found...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}



	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getItemId() {
		return ItemId;
	}

	public void setItemId(int itemId) {
		ItemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
