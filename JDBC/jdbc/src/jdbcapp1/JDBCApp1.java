package jdbcapp1;

import java.sql.*;

public class JDBCApp1 {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "raj", "0725");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from employee");
			/*	GETTING PRODUCT DATA
			 * 
			 * while (rs.next()) { System.out.println(rs.getInt(1) + "\t" + rs.getString(2)+ "\t" + rs.getDouble(3) + "\t" + rs.getInt(4)); }
			 */
			
			/*	GETTING EMPLOYEE DATA
			 */
			 while(rs.next()) { 
				 System.out.println(rs.getInt(1) + "\t" +rs.getString(2) + "\t" + rs.getString(3) + "\t" +rs.getDouble(4)+"\t"+rs.getDouble(5)+"\t"+rs.getDouble(6)+"\t"+rs.getDouble(7)); 
			 }
			 /**/
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
