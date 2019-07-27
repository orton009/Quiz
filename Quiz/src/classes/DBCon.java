package classes;
import java.sql.*;

public class DBCon {
	public static Connection getConnection() {
		Connection con = null ;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb" , "root" , "root");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String[] args) {
		Connection con = DBCon.getConnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from users");
			while(rs.next()) {
				String username = rs.getString(1);
				String password = rs.getString(2);
				System.out.println("Username : " + username+ " Password : " + password);
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}