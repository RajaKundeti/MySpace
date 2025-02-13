package program3;

import java.sql.*;
//import java.util.*;

public class SvsPS {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			Connection con = DriverManager.getConnection(url, "raj", "0725");
			
			
			PreparedStatement ps = con.prepareStatement("select * from Product");
			long startTime = System.currentTimeMillis();
			ResultSet rs1 = ps.executeQuery();
			rs1.getInt(1);
			long endTime = System.currentTimeMillis();
			long time = endTime-startTime;
			System.out.println("Time taken by PreparedStatement : "+time+" Milli-Sec");
			
			Statement s = con.createStatement();
			startTime = System.currentTimeMillis();
			ResultSet rs = s.executeQuery("select * from Product");
			rs.getInt(1);
			endTime = System.currentTimeMillis();
			time = endTime-startTime;
			System.out.println("Time taken by Statement : "+time+" Milli-Sec");
			
			con.close();
		}catch (Exception e) {
		}
	}

}
