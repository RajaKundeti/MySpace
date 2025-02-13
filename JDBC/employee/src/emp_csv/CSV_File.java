package emp_csv;
import java.util.*;
import java.sql.*;
import java.io.*;
public class CSV_File {

	public static void main(String[] args) throws FileNotFoundException ,IOException, SQLException{
		Connection con=null;
		Scanner s = null;
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","raj","0725");
			PreparedStatement ps=con.prepareStatement("insert into emp_info values(?,?,?,?)");
			FileInputStream fis=new FileInputStream("C:\\Users\\narsi\\Downloads\\data.csv");
			s=new Scanner(fis);
			s.nextLine();
			int k=0;
			while(s.hasNext())
			{
				String str[]=s.nextLine().split(",");
				if((str[0].startsWith("A")||str[1].endsWith("a"))&&str[2].length()<=20) {
					ps.setString(1,str[0]);
					ps.setString(2,str[1]);
					ps.setString(3,str[2]);
					ps.setString(4,str[3]);
					k += ps.executeUpdate();
				}
			 }
			 System.out.println(k+" records inserted successfully"); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			s.close();
			con.close();
		}
	}

}
