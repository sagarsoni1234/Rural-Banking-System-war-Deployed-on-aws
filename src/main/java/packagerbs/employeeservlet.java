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
 * Servlet implementation class employeeservlet
 */
public class employeeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public employeeservlet() {
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
        	String account_number = request.getParameter("acc_no");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String opening_amount = request.getParameter("opening_amount");
            String pan = request.getParameter("pancard");
            String aadhar = request.getParameter("aadharcard");
            String mobile_number = request.getParameter("mobile");
            String address = request.getParameter("address");
            String verification = request.getParameter("verification");
            
            System.out.println(account_number+"    "+email+"   "+password+"   "+firstname+"   "+lastname+"   "+opening_amount+"  "+pan+"  "+ aadhar+"   "+mobile_number+"   "+ address+"   "+verification);
            
            
	        PreparedStatement ps=con.prepareStatement("insert into login VALUES (?,?,?,?,?,?,?,?,?,?,?)");
	        
	        ps.setString(1, account_number);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, firstname);
			ps.setString(5, lastname);
			ps.setString(6, opening_amount);
			ps.setString(7, pan);
			ps.setString(8, aadhar);
			ps.setString(9, mobile_number);
			ps.setString(10, address);
			ps.setString(11, verification);
	        boolean rs=ps.execute();
	        if(rs==false) {
	        	out.println("<font color=red size=18>Customer Created Successfully!!<br>");
				out.println("<a href=zemployee.html>go Back!!</a>");
				
			}
			else
			{
				out.println("<font color=red size=18>InsertionFAILED!!<br>");
				out.println("<a href=index.html>Try Again!!</a>");
			
				
			}	
        	
        }
        catch (Exception e) 
        {		
			e.printStackTrace();
		}
	}

}
