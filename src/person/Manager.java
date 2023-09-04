package person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager extends Staff {

	public Manager(int id, String name, String contactNumber, String password, String role) {
		super(id, name, contactNumber, password, role);
		
	}

	public Manager(int id) {
		super(id);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public Manager managerObject(int id) {
		Manager m1 = null;
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("select * from fcemployee where empId=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				m1= new Manager(rs.getInt(1) ,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return m1;
	}
	
	public void createManager(int md_id, String md_name, String md_phone, String md_pass) {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("INSERT INTO fcemployee VALUES (?,?,?,?,?);");
			ps.setInt(1, md_id);
			ps.setString(2, md_name);
			ps.setString(3, md_phone);
			ps.setString(4, md_pass);
			ps.setString(5, "manager");
			int temp = ps.executeUpdate();
			if (temp ==1) {
				System.out.println("...Manager Created Successfully...");
				System.out.println(temp+" row/s affected");
			}
			else {
				System.out.println("...Manager Creation failed...");
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}
	public void deleateManager(int id) {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("delete from fcemployee where empId=? ");
			ps.setInt(1, id);
			int temp = ps.executeUpdate();
			if (temp == 1) {
				System.out.println("...Deleted the Manager...");
			}
			else {
				System.out.println("...Data Not Found...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}
	public void viewManager() {
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("SELECT empId,empName,empNumber FROM fcemployee where empRole='manager'");
			ResultSet rs = ps.executeQuery();
			System.out.println("Displaying all the Manager");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +rs.getString(3));
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}

}
