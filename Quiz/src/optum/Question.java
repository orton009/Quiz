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
 * Servlet implementation class Question
 */
@WebServlet("/Question")
public class Question extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection con = DBCon.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from questions");
			rs.last() ;
			int totalRows = rs.getRow() ;
			
			HashMap<Integer , String> ques = new HashMap<Integer , String>();
			System.out.println("Total Rows = " + totalRows);
			for(int i=0; i <10 ; i++) {
				int random = (int)( (Math.random()*100)%(totalRows+1) ) ;
				rs.absolute(random) ;
				//System.out.print( " : "+ random+ " : ");
				if(!ques.containsKey(random) && rs!=null && random!=0)
					ques.put(random, rs.getString("question"));
				else i-- ;
			}
			//String[] questions = ques.values().toArray(new String[ques.size()]);
			System.out.println(ques);
			request.getSession().setAttribute("questions", ques);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
