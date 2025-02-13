package jdbcapp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCApp2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "raj", "0725");
			Statement st = con.createStatement();
			
			System.out.print("Enter product code: ");
			int code = sc.nextInt();
			System.out.print("Enter product name : ");
			String name = sc.nextLine();
			name = sc.nextLine();
			System.out.print("Enter product price : ");
			double price = sc.nextDouble();
			System.out.print("Enter product quantity : ");
			int quantity = sc.nextInt();
			
			int k = st.executeUpdate("insert into product values("+code+",'"+name+"',"+price+","+quantity+")");
			
			if(k>0) {
				System.out.println("Data Inserted");
			}
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
