package person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Users {
	protected final int Id;
	protected String name;
	protected String contactNumber;
	protected String password;
	protected String role;
	
	
	static PreparedStatement ps;
	public static Connection getConnect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/foodcourt", "root", "root");
		}
	
	public Users (int id){
		super();
		Id=id;	
	}
	
	public Users(int id, String name, String contactNumber, String password) {
		super();
		Id = id;
		this.name = name;
		this.contactNumber = contactNumber;
		this.password = password;
	}
	public Users(int id, String name, String contactNumber, String password, String role) {
		super();
		Id = id;
		this.name = name;
		this.contactNumber = contactNumber;
		this.password = password;
		this.role = role;
	}
	
	public abstract boolean login(int id,String pass);
	public abstract boolean updatePassword(Users obj);
	public abstract boolean updatePhoneNum(Users obj);
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return Id;
	}

}
