package person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Staff extends Users{

	public Staff(int id, String name, String contactNumber, String password, String role) {
		super(id, name, contactNumber, password,role);
		// TODO Auto-generated constructor stub
	}

	public Staff(int id) {
		super(id);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		createStaff(102,"staff2","0987654321", "sta2");
//		Staff s1= new Staff(102,"staff2","0987654321", "sta2","staff");
//		s1.login(102, "sta2");
//		s1.updatePassword(s1);
//		checkEmployeeRole(101);
	}
	public String checkEmployeeRole(int id) {
		String role = null;
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("SELECT empRole FROM fcemployee WHERE empId =?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			role= rs.getString(1);
//			System.out.println(role);
//			System.out.println(rs.getString(1));
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
//		System.out.println(role);
		return role;
	}
	
	public Staff staffObject(int id) {
		Staff s1 = null;
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("select * from fcemployee where empId=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s1= new Staff(rs.getInt(1) ,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return s1;
	}
	
	public void createStaff(int st_id, String st_name, String st_phone, String st_pass) {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("INSERT INTO fcemployee VALUES (?,?,?,?,?);");
			ps.setInt(1, st_id);
			ps.setString(2, st_name);
			ps.setString(3, st_phone);
			ps.setString(4, st_pass);
			ps.setString(5, "staff");
			int temp = ps.executeUpdate();
			if (temp ==1) {
				System.out.println("...Staff Created Successfully...");
//				System.out.println(temp+" row/s affected");
			}
			else {
				System.out.println("...Staff Creation failed...");
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}
	public void deleateStaff(int id) {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("delete from fcemployee where empId=? ");
			ps.setInt(1, id);
			int temp = ps.executeUpdate();
			if (temp == 1) {
				System.out.println("...Deleted the Staff...");
			}
			else {
				System.out.println("...Data Not Found...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}
	public void viewStaff() {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("SELECT empId,empName,empNumber FROM fcemployee where empRole='staff'");
			ResultSet rs = ps.executeQuery();
			System.out.println("Displaying all the Staff");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +rs.getString(3));
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean login(int id, String pass) {
		try {
			Connection con = getConnect();
			String str="SELECT * FROM fcemployee WHERE empId =?;";
			PreparedStatement ps = con.prepareStatement(str);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
//			System.out.println(rs.wasNull());
			if(rs.wasNull()) {
				System.out.println("...Invalid Entry...");
//				signUp();
				return false;
			} 
			else {
				if(pass.equals(rs.getString(4))) {
					System.out.println("...login sucessfull...");
					return true;
				}
				else {
					System.out.println("...Wrong Password...");
					return false;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			System.out.println("...Invalid Entry...");
			return false;
		}
	}

	@Override
	public boolean updatePassword(Users obj) {
		try {
			Connection con = getConnect();
			String str="update fcemployee set empPass=? where empId=?";
			PreparedStatement ps = con.prepareStatement(str);
			Scanner sc =new Scanner(System.in);
			System.out.println("Enter New Password : ");
			String New_password = sc.next();
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
			System.out.println("...Error...");
			return false;			
		}
	}

	@Override
	public boolean updatePhoneNum(Users obj) {
		try {
			Connection con = getConnect();
			String str="update fcemployee set empNumber=? where empId=?";
			PreparedStatement ps = con.prepareStatement(str);
			Scanner sc =new Scanner(System.in);
			System.out.println("Enter New Phone Number : ");
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
			System.out.println("...Error...");
			return false;			
		}
	}

}
