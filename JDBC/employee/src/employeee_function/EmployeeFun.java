package employeee_function;

import java.sql.*;
import java.util.Scanner;
public class EmployeeFun {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			Connection con = DriverManager.getConnection(url, "raj", "0725");
			CallableStatement cs = con.prepareCall("{call ?:=getsal(?)}");
			System.out.print("Enter Emp id to get Salary:");
			int id = sc.nextInt();
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, id);
			boolean isExe = cs.execute();
			if(isExe) {
				System.out.println("Executed");
			}else {
				System.out.println("Not Executed");
			}
			System.out.println("Emp ID: "+id);
			System.out.println("Emp Salary : "+cs.getInt(1));
			con.close();
			sc.close();
		}catch (Exception e) {
		}
	}

}
