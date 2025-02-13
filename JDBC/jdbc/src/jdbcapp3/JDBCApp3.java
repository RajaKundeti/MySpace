package jdbcapp3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCApp3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "raj", "0725");
			Statement st = con.createStatement();
			
			System.out.print("Enter employee id: ");
			int id = sc.nextInt();
			System.out.print("Enter employee name : ");
			String name = sc.nextLine();
			name = sc.nextLine();
			System.out.print("Enter employee designation : ");
			String desg = sc.nextLine(); 
			System.out.print("Enter employee salary : ");
			double sal = sc.nextDouble();
			double hra = sal*0.93;
			double da = sal*0.61;
			double total = sal+hra+da;
			
			int k = st.executeUpdate("insert into employee values("+id+",'"+name+"','"+desg+"',"+sal+","+hra+","+da+","+total+")");
			
			if(k>0) {
				System.out.println("Data Inserted");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
