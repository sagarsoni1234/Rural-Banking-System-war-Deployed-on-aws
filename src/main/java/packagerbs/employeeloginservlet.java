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
 * Servlet implementation class employeeloginservlet
 */
public class employeeloginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public employeeloginservlet() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 
        
        try {  
        	Connection con=null;
        	
        	//Class.forName("con.cj.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://ruralbanking.crhysrqgmgzd.ap-south-1.rds.amazonaws.com:3306/RuralBankingDatabase?autoReconnect=true&useSSL=false","admin","Sagar1234");
        	String username = request.getParameter("employee_email");
            String passcode = request.getParameter("employee_passcode");
	        PreparedStatement ps=con.prepareStatement("select username from employeelogin where username=? and password=?");
	        
	        ps.setString(1, username);
			ps.setString(2, passcode);
	        System.out.println(username +" and "+passcode);
	        ResultSet rs=ps.executeQuery();
	        if(rs.next()) {
				RequestDispatcher rd=request.getRequestDispatcher("zemployee.html");
			    rd.forward(request, response);
			}
			else
			{
				out.println("<font color=red size=18>Login FAILED!!<br>");
				out.println("<a href=index.html>Try Again!!</a>");
			
				
			}	
        	
        }
        catch (Exception e) 
        {		
			e.printStackTrace();
		}
	}

}
