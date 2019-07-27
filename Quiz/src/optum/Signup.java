package optum;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.DBCon;
/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			if (firstName != null && lastName!=null && userName!=null && password !=null) 
			{
				Connection con = DBCon.getConnection();
				Statement st = con.createStatement();
				st.executeUpdate("Insert into users(firstName,lastName,userName,password) values (\""+firstName + "\",\"" + lastName + "\",\"" + userName + "\",\"" + password + "\");");
				request.getRequestDispatcher("login.jsp").forward(request,response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection Failed.................");
		}
		catch(Exception e) {
			System.out.println("Parameter mismatch ........... ");
		}
	}

}
