package test25pr2;
import java.util.*;
import java.sql.*;

/*Transactional Insertion Across Three Tables

Implement a Java program that performs a transactional insertion across three tables: "customers", "orders", and "order_items". The program should allow the user to insert a new customer into the "customers" table, create a new order in the "orders" table associated with that customer, and insert multiple order items into the "order_items" table for the new order. Use JDBC transaction management to ensure that all insertions are either committed together or rolled back if any insertion operation fails.*/

public class Customer {
	static int cid=100, oid=1000, pid=500;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				Connection con = DriverManager.getConnection(url, "raj", "0725");
				con.setAutoCommit(false);
				Savepoint sp = con.setSavepoint();
				
				System.out.print("Enter Customer name: ");
				String cname = sc.nextLine();
				System.out.print("Enter product name: ");
				String pname = sc.nextLine();
				System.out.println("Enter cost: ");
				int cost = sc.nextInt();
				
				CallableStatement cs = con.prepareCall("{call insertdata(?,?,?,?,?,?,?)}");
				cs.setInt(1,cid);
				cs.setString(2, cname);
				cs.setInt(3, oid);
				cs.setFloat(4, cost);
				cs.setInt(5, pid);
				cs.setString(6, pname);
				cs.setFloat(7, cost);
				int k = cs.executeUpdate();
				if(k>0) {
					++cid;
					++oid;
					++pid;
					System.out.println("record Inserted Successfully");
				}
				else {
					con.rollback(sp);
					System.out.println("Invalid data");
				}
				con.close();
			}catch (Exception e) {
				e.getMessage();
			}
		}
	}

}
