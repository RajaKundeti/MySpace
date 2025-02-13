package employeee_function3;
import java.util.*;
import java.sql.*;

public class EmpInfo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc){
			/*
			 * System.out.print("Enter Employee Id to Update: "); int id = sc.nextInt();
			 * System.out.print("Enter Adress to update: "); String adr = sc.nextLine(); adr
			 * = sc.nextLine(); System.out.print("Enter Email to update: "); String mail =
			 * sc.nextLine(); System.out.print("Enter Mobile to update: ");String mob = sc.nextLine();
			 */
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "raj", "0725");
			Statement s = con.createStatement();
			s.addBatch("insert into emp_batch values(1,'Atul',20000,'Hyd','atul@gmail.com','9999988888')");
			s.addBatch("insert into emp_batch values(2,'Bhai',30000,'Hyd','bhai@gmail.com','1122334455')");
			//s.addBatch("update emp_batch set eadr='"+adr+"',email='"+mail+"',emobile='"+mob+"' where eid="+id);
			//s.addBatch("delete from emp_batch where esal>=10000 and esal<=20000");
			int[] count = s.executeBatch();
			System.out.println(count.length+" rows updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
