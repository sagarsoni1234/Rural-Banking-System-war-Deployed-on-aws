package packagerbs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class showbalservlet
 */
public class showbalservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public showbalservlet() {
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
        	String account_no = request.getParameter("customsearch");
            
	        PreparedStatement ps=con.prepareStatement("select * from funds where account_no=? ");
	        
	        ps.setString(1, account_no);
			
	        System.out.println(account_no);
	        out.print ("<table width=50% border=1>");
            out.print ("<caption>Customer Details:</caption>");
	        ResultSet rs=ps.executeQuery();
	        /* Printing column names */
            out.print ("</br></br>");
            ResultSetMetaData rsmd = rs.getMetaData ();
            int total = rsmd.getColumnCount ();
            out.print ("<tr>");
            for (int i = 1; i <= total; i++)
         {
             out.print ("<th>" + rsmd.getColumnName (i) + "</th>");
         }
            out.print ("</tr>");
            /* Printing result */
            while (rs.next ())
         {
             out.print ("<tr><td>" + rs.getString (1) + "</td><td>" +  rs.getString (2) + " </td><td>" + rs.getString (3) + " </td><tr>");
         }
            out.print ("</table>");
        }
        catch (Exception e2)
        {
            e2.printStackTrace ();
        }
        finally
        {
            out.close ();
        }
	}

}
