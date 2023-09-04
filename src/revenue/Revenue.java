package revenue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Revenue {
	
	static PreparedStatement ps;
	public static Connection getConnect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/foodcourt", "root", "root");
		}
	
	//Called by admin It is total food court revenue 
	public void DisplayTotalRevenue() {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("select sum(totalPrice) from orders");
			ResultSet rs = ps.executeQuery();
			rs.next();
			int TotalRevenue = rs.getInt(1);
			System.out.println("Total Revenue of Food Court is "+TotalRevenue);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}
	// called to give revenue counter wise
	public void DisplayTotalRevenueCounterWise() {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("SELECT\r\n"
					+ "    m.counterId,\r\n"
					+ "    SUM(oi.quantity * m.price) AS total_revenue_per_counter\r\n"
					+ "FROM\r\n"
					+ "    menu m\r\n"
					+ "JOIN\r\n"
					+ "    OrderItem oi ON m.itemId = oi.itemId\r\n"
					+ "JOIN\r\n"
					+ "    orders o ON oi.orderId = o.orderId\r\n"
					+ "GROUP BY\r\n"
					+ "    m.counterId\r\n"
					+ "ORDER BY\r\n"
					+ "    m.counterId;");
			ResultSet rs = ps.executeQuery();
			System.out.println("Counter  " + " Total revenue");
			while(rs.next()) {
				System.out.println(rs.getString(1)+ "	  "+ rs.getInt(2));
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Revenue re = new Revenue();
		re.DisplayTotalRevenue();
		re.DisplayTotalRevenueCounterWise();

	}

}
