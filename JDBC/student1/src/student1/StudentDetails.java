package student1;
import java.util.*;
import java.sql.*;

public class StudentDetails {

	public static void insertStudentData() {
		Scanner sc = new Scanner(System.in);
		try(sc){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				Connection con = DriverManager.getConnection(url, "raj", "0725");
				CallableStatement cs = con.prepareCall("{call stindata(?,?,?,?,?,?,?,?,?)}");
				System.out.println("Enter Student Details : ");
				System.out.print("Id : ");
				int id = sc.nextInt();
				System.out.print("Roll No : ");
				String roll = sc.nextLine();
				roll = sc.nextLine();
				System.out.print("Name : ");
				String name = sc.nextLine();
				System.out.print("Branch : ");
				String branch = sc.nextLine();
				System.out.print("House No : ");
				String hno = sc.nextLine();
				System.out.print("City : ");
				String city = sc.nextLine();
				System.out.print("PIN Code : ");
				int pin = sc.nextInt();
				System.out.print("Mail : ");
				String mail = sc.nextLine();
				mail = sc.nextLine();
				System.out.print("Mobile : ");
				long mobile = sc.nextLong();
				cs.setInt(1, id);
				cs.setString(2, roll);
				cs.setString(3, name);
				cs.setString(4, branch);
				cs.setString(5, hno);
				cs.setString(6, city);
				cs.setInt(7, pin);
				cs.setString(8, mail);
				cs.setLong(9, mobile);
				if(cs.execute()) {
					System.err.println("No Data Found");
				}
				else {
					System.out.println("Data Inserted Successfully");
				}
				con.close();
			}catch (Exception e) {
				e.getMessage();
			}
		}
	}
	
	public static void displayStudentData() {
		Scanner sc = new Scanner(System.in);
		try(sc){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				Connection con = DriverManager.getConnection(url, "raj", "0725");
				CallableStatement cs = con.prepareCall("{call stoutdata(?,?,?,?,?,?,?,?,?)}");
				System.out.print("Enter student id : ");
				int id = sc.nextInt();
				cs.setInt(1, id);
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.VARCHAR);
				cs.registerOutParameter(5, Types.VARCHAR);
				cs.registerOutParameter(6, Types.VARCHAR);
				cs.registerOutParameter(7, Types.INTEGER);
				cs.registerOutParameter(8, Types.VARCHAR);
				cs.registerOutParameter(9, Types.BIGINT);
				cs.execute();
				System.out.println("Student Details : ");
				System.out.println("---------------------");
				System.out.println("Id      : "+id);
				System.out.println("RollNo  : "+cs.getString(2));
				System.out.println("Name    : "+cs.getString(3));
				System.out.println("Branch  : "+cs.getString(4));
				System.out.println("H-No    : "+cs.getString(5));
				System.out.println("City    : "+cs.getString(6));
				System.out.println("PINcode : "+cs.getString(7));
				System.out.println("Mail    : "+cs.getString(8));
				System.out.println("Mobile  : "+cs.getString(9));;
			}catch (Exception e) {
				e.getMessage();
			}
		}
	}
	public static void main(String[] args) {
		//insertStudentData();
		displayStudentData();
	}

}
