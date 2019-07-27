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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			request.setAttribute("username", userName);
			Connection con = DBCon.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select password from users where username =\"" + userName + "\"");
			String err = null ;
			if(rs.next() == false) {
				err = "Username not found" ;
			}
			else if(rs.getString("password").equals(password)){
				//succesfully logged in redirect to next page
				System.out.println("logged in ..........");
				try {
					request.getRequestDispatcher("welcome.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {
				err= "Incorrect Password" ;
			}
			System.out.println("error = " + err);
			if(err != null) {
				request.setAttribute("err", err);
				//redirect to login page with error message
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("connection failed...........");
		}
		catch(Exception e) {
			System.out.println("Parameters mismatch.........");
		}
	}

}
