package order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

import admin.Counter;

public class Orders {
	
	private int orderId;
    private int customerId;
    private Date orderDate;
    private Time orderTime;
    private double totalPrice;
    
    Scanner sc=new Scanner(System.in);
    
    public Orders() {
		super();
	}

	public Orders(int orderId, int customerId, Date orderDate, Time orderTime, double totalPrice) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.totalPrice = totalPrice;
	}
    
    static PreparedStatement ps;
	public static Connection getConnect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/foodcourt", "root", "root");
		}
	
	public void orderFromMenu(int cusId) {
		Counter counter = new Counter();
		counter.displayCounterForCustomer();
		String toStoreCounter = sc.next();
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("SELECT itemId,itemName,category,price FROM menu WHERE counterId = ?");
			ps.setString(1, toStoreCounter);
			ResultSet rs = ps.executeQuery();
			System.out.println("...Menu...");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " "+ rs.getString(2) + " " + rs.getString(3) + " "+ rs.getDouble(4));
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("...Wrong Entry...");
		}
		Orders orderTableUpdate = new Orders();
		int orderId = orderTableUpdate.PlaceOrderDummy(cusId);
		double totalMoney;
		// ................................................
		System.out.println("...Enter the Item Id...");
		int toStoreItemId = sc.nextInt();
		System.out.println("...Enter Quantity...");
		int quantity = sc.nextInt();
		Order_items orderItem = new Order_items();
		orderItem.PlaceItem(orderId, toStoreItemId, quantity);
		System.out.println();
		System.out.println(".....your cart.....");
		totalMoney = orderItem.DisplayItem(orderId);
		System.out.println("......................................................");
		while(true) {
			System.out.println("Press 0 To place order \n1. To Add order Items\n2. To Deleate Order Items");
			int Choise = sc.nextInt();
//			System.out.println("...Enter the Item Id...");
//			int toStoreItemId = sc.nextInt();
//			System.out.println("...Enter Quantity...");
//			int quantity = sc.nextInt();
//			Order_items orderItem = new Order_items();
//			orderItem.PlaceItem(orderId, toStoreItemId, quantity);
//			System.out.println();
//			System.out.println(".....your cart.....");
//			double totalMoney = orderItem.DisplayItem(orderId);
//			System.out.println("......................................................");
			//---------------------------------------------------------
//			System.out.println("Press 0 To place order \n1. To Add order Items\n2. To Deleate Order Items");
//			int Choise = sc.nextInt();
			if (Choise==0) {
//				System.out.println("...Order Placed...");
				orderTableUpdate.PlaceOrderFinal(orderId, totalMoney);
				//update the orders table here
				break;
			}
			else if(Choise==1) {
				System.out.println("...Enter the Item Id...");
				int toStoreItemId1 = sc.nextInt();
				System.out.println("...Enter Quantity...");
				int quantity1 = sc.nextInt();
//				Order_items orderItem = new Order_items();
				orderItem.PlaceItem(orderId, toStoreItemId1, quantity1);
				System.out.println();
				System.out.println(".....your cart.....");
				totalMoney = orderItem.DisplayItem(orderId);
				System.out.println("......................................................");
//				continue;
			}
			else if(Choise==2) {
//				orderItem.DeleateItem(toStoreItemId);
//				orderItem id get and do this
				System.out.println("...Enter the id to delete order...");
				int toDelItem = sc.nextInt();
				orderItem.DeleateItem(toDelItem);
				System.out.println(".....your cart.....");
				totalMoney = orderItem.DisplayItem(orderId);
				System.out.println("......................................................");
			}
			else {
				System.out.println("...Wrong Entry...");
			}
			
		}
	}

	public void PlaceOrderFinal(int orderId,double totalMoney) {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("UPDATE Orders SET orderDate = CURDATE(), orderTime = CURTIME(), totalPrice = ? WHERE orderId = ?;");
			ps.setDouble(1, totalMoney);
			ps.setInt(2, orderId);
			int temp = ps.executeUpdate();
			if (temp ==1) {
				System.out.println("...Order Placed Successfully...");
			}
			else {
				System.out.println("...Order is not placed try again...");
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
    }
	

	public int PlaceOrderDummy(int customerId) {
		int orderId = 0;
		try {
			// Change this
			
			
			int total = 0;
			
			
			// change this
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("INSERT INTO orders (customerId,orderDate,orderTime,totalPrice) VALUES (?, CURDATE(), CURTIME(),?)");
			ps.setInt(1, customerId);
			ps.setInt(2, total);
			int temp = ps.executeUpdate();
			PreparedStatement ps1 = con.prepareStatement("SELECT LAST_INSERT_ID() AS orderId;");
			ResultSet rs = ps1.executeQuery();
			rs.next();
			orderId = rs.getInt(1);
//			if (temp ==1) {
//				System.out.println("...Order Placed Successfully...");
//			}
//			else {
//				System.out.println("...Order is not placed try again...");
//			}
			ps.close();
			ps1.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return orderId;
    }
	public void DeleateOrder(int orderId) {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("delete from orders where orderId=? ");
			ps.setInt(1, orderId);
			int temp = ps.executeUpdate();
			if (temp == 1) {
				System.out.println("...Deleted the Order...");
			}
			else {
				System.out.println("...Data Not Found...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}

	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public Time getOrderTime() {
		return orderTime;
	}


	public void setOrderTime(Time orderTime) {
		this.orderTime = orderTime;
	}


	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Orders test1 = new Orders();
		test1.orderFromMenu(80394);
	}
	

}
