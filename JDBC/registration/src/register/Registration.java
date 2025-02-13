package register;
import java.sql.*;
import java.util.*;

public class Registration {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "raj", "0725");
			PreparedStatement addData = con.prepareStatement("insert into facebook values(?,?,?,?,?,?)");
			PreparedStatement selData = con.prepareStatement("Select * from facebook where username=? and password=?");
			try(sc;con){
				while(true) {
					System.out.println("========Facebook=======");
					System.out.println("1. Register");
					System.out.println("2. Login");
					System.out.println("3. Exit");
					System.out.println("=======================");
					System.out.print("Enter your choice : ");
					int option = sc.nextInt();
					switch(option) {
					case 1:
					System.out.print("Enter username : ");
					String username = sc.nextLine();
					username = sc.nextLine();
					System.out.print("Enter password : ");
					String password = sc.nextLine();
					System.out.print("Enter firstname : ");
					String firstname = sc.nextLine();
					System.out.print("Enter lastname : ");
					String lastname = sc.nextLine();
					System.out.print("Enter mail-id : ");
					String mail = sc.nextLine();
					System.out.print("Enter mobile : ");
					String mobile = sc.nextLine();
					addData.setString(1, username);
					addData.setString(2, password);
					addData.setString(3, firstname);
					addData.setString(4, lastname);
					addData.setString(5, mail);
					addData.setString(6, mobile);
					int k = addData.executeUpdate();
					if(k>0) {
						System.out.println("Registered Successfully");
					}
					else {
						System.err.println("Invalid inputs");
					}
					break;
					case 2:System.out.print("Enter Username : ");
					String name = sc.nextLine();
					name = sc.nextLine();
					System.out.print("Enter Password : ");
					String pass =  sc.nextLine();
					selData.setString(1, name);
					selData.setString(2, pass);
					ResultSet rs = selData.executeQuery();
					if(rs.next()) {
						System.out.println("Login Successfull!");
					}
					else {
						System.err.println("Invalid username/password");
					}
					break;
					case 3:System.exit(0);
					default: System.out.println("Invalid input");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
