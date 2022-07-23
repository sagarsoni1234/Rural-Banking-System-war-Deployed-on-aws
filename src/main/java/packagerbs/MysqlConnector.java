package packagerbs;
import java.sql.Connection;
import java.sql.DriverManager;
public class MysqlConnector {
	public static Connection getConnection() {
		Connection con=null;
		try {                                           
			  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","");
			//con=DriverManager.getConnection("jdbc:mysql://mysqlprod789.crhysrqgmgzd.ap-south-1.rds.amazonaws.com:3306/r2schools?autoReconnect=true&useSSL=false","admin","sagar123");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String args[]) {
		Connection con=getConnection();
		if(con==null) {
			System.out.println("Not Connected!");
		}
		else
			System.out.println("***Connected!***");	
	}
}

/*mysql local host trial*/