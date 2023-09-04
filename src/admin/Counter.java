package admin;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import person.Staff;

interface ICounter{
	void createCounter(String c_id,String c_name,int c_staff);
	void displayCounter();
	void updateCounter(String c_id,String c_name,int c_staff);
	void deleateCounter(String c_id);
}
public class Counter implements ICounter{
	private String c_id;
    private String c_name;
    private int c_staff;
    
    static PreparedStatement ps;
	public static Connection getConnect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/foodcourt", "root", "root");
		}
	
	public Counter() {
		super();
	}
	
	public Counter(String c_id, String c_name, int c_staff) {
		super();
		this.c_id = c_id;
		this.c_name = c_name;
		this.c_staff = c_staff;
	}
	
	public Counter counterObject(String id) {
		Counter c1 = null;
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("select * from counter where c_id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c1= new Counter(rs.getString(1),rs.getString(2),rs.getInt(3));
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return c1;
	}

	@Override
	public void createCounter(String c_id, String c_name, int c_staff) {
		Connection con;
		try {
			con = getConnect();
			PreparedStatement ps = con.prepareStatement("INSERT INTO counter (c_id,c_name,c_staff) values (?,?,?);");
//			ps.setString(1, c_id);
//			ps.setString(2, c_name);
//			ps.setInt(3, c_staff);
			ps.setString(1, this.c_id);
			ps.setString(2, this.c_name);
			ps.setInt(3, this.c_staff);
			int temp = ps.executeUpdate();
			if (temp ==1) {
				System.out.println("...Counter Created Successfully...");
//				System.out.println(temp+" row/s affected");
			}
			else {
				System.out.println("...Counter creation failed...");
			}
			ps.close();
			con.close();
			System.out.println("3");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
			System.out.println(e);
//			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLException");
			System.out.println(e);
//			e.printStackTrace();
		}
		
	}



	@Override
	public void displayCounter() {
		// TODO Auto-generated method stub
		Connection con;
		try {
			con = getConnect();
			PreparedStatement ps = con.prepareStatement("select * from counter");
			ResultSet rs = ps.executeQuery();
			System.out.println("Displaying all details");
			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " +rs.getInt(3));
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
//			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
//			e.printStackTrace();
		}
	}
	
	public void displayCounterForCustomer() {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("select c_id,c_name from counter");
			ResultSet rs = ps.executeQuery();
//			System.out.println("...Select The Counter You want to Order...");
			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}
			System.out.println("...Select The Counter You want to Order...");
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}



	@Override
	public void updateCounter(String c_id, String c_name, int c_staff) {
		// TODO Auto-generated method stub
		try {
			Connection con = getConnect();
			String str="update counter set c_name=?,c_staff=? where c_id=?";
			PreparedStatement ps = con.prepareStatement(str);
			ps.setString(1,c_name);
			ps.setInt(2,c_staff);
			ps.setString(3,c_id);
			int x=ps.executeUpdate();
			if(x==1)
			{
				System.out.println("...updated Counter data...");
			}
			else {
				System.out.println("...Error...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
//			e.printStackTrace();
		}
	}
	
	public void updateCounterName(String c_id, String c_name) {
		try {
			Connection con = getConnect();
			String str="update counter set c_name=? where c_id=?";
			PreparedStatement ps = con.prepareStatement(str);
			ps.setString(1,c_name);
			ps.setString(2,c_id);
			int x=ps.executeUpdate();
			if(x==1)
			{
				System.out.println("...updated Counter Name...");
			}
			else {
				System.out.println("...Error...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}
	
	public void updateCounterStaff(String c_id, int c_staff) {
		try {
			Connection con = getConnect();
			String str="update counter set c_staff=? where c_id=?";
			PreparedStatement ps = con.prepareStatement(str);
			ps.setInt(1,c_staff);
			ps.setString(2,c_id);
			int x=ps.executeUpdate();
			if(x==1)
			{
				System.out.println("...updated Counter Staff...");
			}
			else {
				System.out.println("...Error...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}

	@Override
	public void deleateCounter(String c_id) {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("delete from counter where c_id=? ");
			ps.setString(1, c_id);
			int temp = ps.executeUpdate();
			if (temp == 1) {
				System.out.println("Deleted the Record Successfully");
			}
			else {
				System.out.println("Data Not Found");
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}



	public String getC_id() {
		return c_id;
	}



	public void setC_id(String c_id) {
		this.c_id = c_id;
	}



	public String getC_name() {
		return c_name;
	}



	public void setC_name(String c_name) {
		this.c_name = c_name;
	}



	public int getC_staff() {
		return c_staff;
	}



	public void setC_staff(int c_staff) {
		this.c_staff = c_staff;
	}



	@Override
	public String toString() {
		return "Counter [c_id=" + c_id + ", c_name=" + c_name + ", c_staff=" + c_staff + "]";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Counter c = new Counter("c3","ashoke vatica",2);
//		c.createCounter(null, null, 0);
		c.displayCounter();
		System.out.println("..........");
		c.updateCounter(null, null, 0);
		c.displayCounter();
//		c.deleateCounter("c2");
//		c.updateCounter("c3", "vatika", 5);
	}
}
