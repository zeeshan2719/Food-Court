package person;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Customer extends Users {

	public Customer(int id, String name, String contactNumber, String password) {
		super(id, name, contactNumber, password);
	}
	
	public Customer(int id) {
		super(id);
	}
	
	public Customer object(int id) {
		Customer c1 = null;
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("select * from customer where cusId=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c1= new Customer(id ,rs.getString(2),rs.getString(3),rs.getString(4));
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return c1;
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		signUp();
//		Customer c1= new Customer(321 ,"soro","6203567418","zeeshan");
//		c1.updatePassword(c1);
//		c1.updatePhoneNum(c1);
//		c1.login(321, "zeeshan");
//	}
	
	public void signUp() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter id//name//contact number//password//re-enter password");
		int customer_id = sc.nextInt();
		sc.nextLine();
		String name = sc.nextLine();
		String contactNumber = sc.nextLine();
		String password = sc.nextLine();
		String rePassword = sc.nextLine();
		if(password.equals(rePassword)) {
//			System.out.println("...user created...");
			//call create customer
			createCustomer(customer_id, name, contactNumber, password);
		} else {
			System.out.println("...TRY AGAIN...");
		}
	}
	
	public static void createCustomer(int cus_id, String cus_name, String cus_phone, String cus_pass ) {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("INSERT INTO customer values (?,?,?,?);");
			ps.setInt(1, cus_id);
			ps.setString(2, cus_name);
			ps.setString(3, cus_phone);
			ps.setString(4, cus_pass);
			int temp = ps.executeUpdate();
			if (temp ==1) {
				System.out.println("...Customer Created Successfully...");
				System.out.println(temp+" row/s affected");
			}
			else {
				System.out.println("...Customer creation failed...");
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}

	@Override
	public boolean login(int id,String password) {
		try {
			Connection con = getConnect();
			String str="SELECT * FROM customer WHERE cusId =?;";
			PreparedStatement ps = con.prepareStatement(str);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
//			System.out.println(rs.getString(4));
//			System.out.println(rs);
//			System.out.println(rs.wasNull());
			if(rs.wasNull()) {
				System.out.println("...Please do Sign UP first...");
//				signUp();
				return false;
			} 
			else {
				if(password.equals(rs.getString(4))) {
					System.out.println("...login sucessfull...");
					return true;
				}
				else {
					System.out.println("...Wrong Password...");
					return false;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
//			System.out.println(e);
			System.out.println("...Please do Sign UP first...");
			return false;
		}
	}

	@Override
	public boolean updatePassword(Users obj) {
		try {
			Connection con = getConnect();
			String str="update customer set cuspass=? where cusId=?";
			Scanner sc =new Scanner(System.in);
			System.out.println("Enter New Password : ");
			String New_password = sc.next();
			PreparedStatement ps = con.prepareStatement(str);
			ps.setString(1,New_password);
			ps.setInt(2,this.Id);
			int x=ps.executeUpdate();
			if(x==1)
			{
				System.out.println("...updated Password...");
				return true;
			}
			else {
				System.out.println("...Sorry cannot update password...");
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			return false;
			
		}
	}

	@Override
	public boolean updatePhoneNum(Users obj) {
		try {
			Connection con = getConnect();
			String str="update customer set cusPhone=? where cusId=?";
			PreparedStatement ps = con.prepareStatement(str);
			System.out.println("Enter New Phone Number : ");
			Scanner sc =new Scanner(System.in);
			String New_number = sc.next();
			ps.setString(1,New_number);
			ps.setInt(2,this.Id);
			int x=ps.executeUpdate();
			if(x==1)
			{
				System.out.println("...updated Phone Number...");
				return true;
			}
			else {
				System.out.println("...Sorry cannot update Phone Number...");
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			return false;
			
		}
	}

}
