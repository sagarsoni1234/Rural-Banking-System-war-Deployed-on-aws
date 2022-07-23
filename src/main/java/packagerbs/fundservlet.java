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
 * Servlet implementation class fundservlet
 */
public class fundservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public fundservlet() {
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
        
        /*Subtract*/
        try {  
        	Connection con=null;
        	
        	//Class.forName("con.cj.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://ruralbanking.crhysrqgmgzd.ap-south-1.rds.amazonaws.com:3306/RuralBankingDatabase?autoReconnect=true&useSSL=false","admin","Sagar1234");
        	String account_no = request.getParameter("account_no");
            String ifsc = request.getParameter("ifsc");
            String amountstr=request.getParameter("amountHTML");
            String rec_acc=request.getParameter("account2");
            System.out.println(amountstr);
            int amount=Integer.parseInt(amountstr);
        
	        PreparedStatement ps=con.prepareStatement("select fund from funds where account_no=? and ifsc=?");
	        ps.setString(1, account_no);
			ps.setString(2, ifsc);
	        System.out.println(account_no +" and "+ifsc);
	        ResultSet rs=ps.executeQuery();
	        if(rs.next()) {
		        	int fund=rs.getInt("fund");
		        	System.out.println("net balance is"+fund+" and desired amount is:"+amount);
		        	if(fund<amount) {
		        		out.print("<font color=red size=18>Insufficient Funds! Try again..<br>");
		        		out.println("<a href=ztransfer.html>Try Again!!</a>");
		        	}
		        	else {
		        		int updated_fund=fund-amount;
		        		ps=con.prepareStatement("UPDATE funds SET fund=? WHERE account_no=?");
		        		ps.setInt(1, updated_fund);
		    			ps.setString(2, account_no);
		    	        System.out.println(account_no +" and your updated funds are:"+updated_fund);
		    	        boolean rs2=ps.execute();
		    	        out.println("<font color=red size=18>Funds transferred Sussessfully!!<br>");
						out.println("<a href=login.html>go hOME!!</a>");
		        	}
	        }
			else
			{
				out.println("<font color=red size=18>Error while requesting(Incorrect Details)!!<br>");
				out.println("<a href=index.html>Try Again!!</a>");
			}	
	      }
        
        catch (Exception e) 
        {		
			e.printStackTrace();
		}
        
        
        
        /*add*/
        try {  
        	Connection con=null;
        	
        	//Class.forName("con.cj.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://ruralbanking.crhysrqgmgzd.ap-south-1.rds.amazonaws.com:3306/RuralBankingDatabase?autoReconnect=true&useSSL=false","admin","Sagar1234");
        	String account_no = request.getParameter("account_no");
            String ifsc = request.getParameter("ifsc");
            String amountstr=request.getParameter("amountHTML");
            String rec_acc=request.getParameter("account2");
            System.out.println(amountstr);
            int amount=Integer.parseInt(amountstr);
     
	        PreparedStatement ps=con.prepareStatement("select fund from funds where account_no=?");
	        ps.setString(1, rec_acc);
		
	        System.out.println(account_no +" and "+ifsc);
	        ResultSet rs=ps.executeQuery();
	        if(rs.next()) {
		        	int fund=rs.getInt("fund");
		        	System.out.println("net balance is"+fund+" and desired amount is:"+amount+fund);
		        	
		        		int updated_fund2=fund+amount;
		        		ps=con.prepareStatement("UPDATE funds SET fund=? WHERE account_no=?");
		        		ps.setInt(1, updated_fund2);
		    			ps.setString(2, rec_acc);
		    	        System.out.println(account_no +" and your updated funds are:"+updated_fund2);
		    	        boolean rs2=ps.execute();
	        }
			else
			{
				out.println("<font color=red size=18>funds recevied to reveiver!!<br>");
				
			}	
	      }
        
        catch (Exception e) 
        {		
			e.printStackTrace();
		}
	}

}
