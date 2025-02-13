package program1;
import java.sql.*;
import java.util.*;

public class Employee1 {
	
	static record Emp(int id, String name, int age, String dept) {
	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "raj", "0725");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select * from emp");
			List<Emp> empData = new ArrayList<>();
			while(rs.next()) {
				empData.add(new Emp(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
			}
			System.out.println("Employees whose age > 25 & age is prime ends with A / E / D");
			empData.stream().filter(e->e.age>25).filter(
					e->
					{
						int prime=1;
						for(int i=2;i<=e.age/2;i++) {
							if(e.age%i==0) {
								prime=0;
							}
						}
						if(prime==1) {
							return true;
						}else 
							return false;
					}
			).filter(e->e.name.endsWith("A")||e.name.endsWith("E")||e.name.endsWith("K")).forEach(System.out::println);
			sc.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
