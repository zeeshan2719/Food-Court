package person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends Manager {

	public Admin(int id, String name, String contactNumber, String password, String role) {
		super(id, name, contactNumber, password, role);
		
	}

	public Admin(int id) {
		super(id);
	}
	public Admin adminObject(int id) {
		Admin a1 = null;
		try {
			Connection con = getConnect();
			PreparedStatement ps = con.prepareStatement("select * from fcemployee where empId=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				a1= new Admin(rs.getInt(1) ,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return a1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
