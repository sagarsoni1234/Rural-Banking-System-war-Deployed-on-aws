package packagerbs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class passwordchangingservlet
 */
public class passwordchangingservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public passwordchangingservlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        
        try {  
        	Connection con=null;
        	
        	//Class.forName("con.cj.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://ruralbanking.crhysrqgmgzd.ap-south-1.rds.amazonaws.com:3306/RuralBankingDatabase?autoReconnect=true&useSSL=false","admin","Sagar1234");
        	String account_no = request.getParameter("user_account");
            String newpasscode = request.getParameter("user_new_passcode");
            String oldpasscode = request.getParameter("user_old_passcode");
            
            
            
        	PreparedStatement ps2=con.prepareStatement("UPDATE login SET password=? WHERE account_no=?");
   
	        ps2.setString(1, newpasscode);
			ps2.setString(2, account_no);
	        System.out.println(account_no +" and "+oldpasscode);
	        boolean rs=ps2.execute();
	        if(rs==false) {
	        	
				System.out.println(newpasscode);
				out.println("<font color=red size=18>Password Reset Successfully.<br>");
				out.println("<a href=login.html>Home Page.</a>");
			    
			}
			else
			{
				out.println("<font color=red size=18>Login FAILED!!<br>");
				out.println("<a href=zbankpass.html>Try Again!!</a>");
				
			}	
        	
        }
        catch (Exception e) 
        {		
			e.printStackTrace();
		}
     
	
	}

}
