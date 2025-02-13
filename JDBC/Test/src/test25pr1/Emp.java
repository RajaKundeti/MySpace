package test25pr1;
import java.util.*;
import java.sql.*;
public class Emp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc){
			try {
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "raj", "0725");
				//CallableStatement cs = con.prepareCall("{call ?:=getempsal(?)}");
				CallableStatement csdel = con.prepareCall("{call delemp(?)}");
				System.out.print("Enter emp id: ");
				int id = sc.nextInt();
				//cs.registerOutParameter(1, Types.FLOAT);
				//cs.setInt(2, id);
				//cs.execute();
				csdel.setInt(1, id);
				boolean del = csdel.execute();
				if(del) {
					System.out.println("No record Found");
				}
				else {
					System.out.println("Employee record Deleted Successfully");
				}
				con.close();
				sc.close();
			}catch (Exception e) {
			}
		}
	}
}
