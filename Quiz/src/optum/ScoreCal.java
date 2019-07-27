package optum;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.DBCon;

/**
 * Servlet implementation class ScoreCal
 */
@WebServlet("/ScoreCal")
public class ScoreCal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//--------------
			
			HashMap<Integer , Integer> attemptedQues = new HashMap<Integer , Integer>() ;
			HashMap<Integer , String> ques = (HashMap<Integer , String>)request.getSession().getAttribute("questions");
			
			for(Map.Entry<Integer, String> entry : ques.entrySet()) {
				int quesId = entry.getKey() ;
				String aID = request.getParameter(Integer.toString(quesId)) ;
				if(aID != null) {
					int ansId = Integer.parseInt(aID);
					System.out.println( "ansId for "+quesId+" : " + aID);
					if(ansId!=0 && quesId!=0)
						attemptedQues.put(ansId,quesId);
				}
				
			}
			System.out.println("attempted Ques : " + attemptedQues);
			//--------------
			//HashMap<Integer , Integer> attemptedQues = (HashMap<Integer , Integer>)request.getSession().getAttribute("attemptedQues") ;
			Connection con = DBCon.getConnection();
			Statement st = con.createStatement();
			int score = 0 ;
			for(Map.Entry<Integer, Integer> entry : attemptedQues.entrySet()) {
				int	quesId = entry.getValue() ;
				int ansId = entry.getKey() ;
				ResultSet rs = st.executeQuery("select value from answers where quesId = " + quesId + " and id = " + ansId) ;
				while(rs.next()) {
					if(rs.getBoolean("value") == true) {
						score++ ;
					}
				}
				
			}
			System.out.println("Score = " + score);
			request.getSession().setAttribute("score", score);
			request.getRequestDispatcher("score.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection Failed.........");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Parameter Mismatch.........");
		}
	}

}
