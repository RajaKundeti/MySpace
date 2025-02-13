package employeee_function2;
import java.util.*;
import java.sql.*;

public class EmployeeFun2 {
	static Connection con;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try (sc){
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			System.out.print("Enter UserName : ");
			String username = sc.nextLine();
			System.out.print("Enter Password : ");
			String password = sc.nextLine();
			connect(url,username,password);
			CallableStatement cs = con.prepareCall("{call ?:=getTotalEmployees()}");
			int total = callFunction(cs);
			System.out.println("Total Employees in Database: "+total);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void connect(String url, String uname, String pass) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, uname, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static int callFunction(CallableStatement cs) {
		try {
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			return cs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
