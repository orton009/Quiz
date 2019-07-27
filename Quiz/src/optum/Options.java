package optum;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.DBCon;

/**
 * Servlet implementation class Options
 */
@WebServlet("/Options")
public class Options extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int quesId = Integer.parseInt(request.getParameter("quesId")) ;
			Connection con = DBCon.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id,options from answers where quesId = " + quesId);
			
			HashMap<Integer , String> options = new HashMap<Integer , String>();
			while(rs.next()) {
				options.put(rs.getInt("id"), rs.getString("options")) ;
			}
			System.out.println("options = " + options);
			request.getSession().setAttribute("options", options);  
			
		} catch (NumberFormatException e) {
			System.out.println("Parameter mismatch........");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection failed..........");
		}
	}


}
