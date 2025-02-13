package program2;

import java.sql.*;

import java.util.*;

public class Product {
	
	static record Pro(int id, String name, int price, int quantity) {
		
	}

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "raj", "0725");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select * from pro");
			List<Pro> pList = new ArrayList<>();
			while(rs.next()) {
				pList.add(new Pro(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4)));
			}
			pList.stream().filter(p->{
				int num = p.id;
				int c = 0;
				while(num!=0) {
					c++;
					num/=10;
				}				
				num = p.id;
				int res = 0;
				while(num!=0) {
					res += (int) Math.pow(num%10, c);
					num/=10;
				}
				if(res==p.id)return true;
				else return false;
			}).filter(p->p.price>1000&&p.price<5000).forEach(System.out::println);
			sc.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
