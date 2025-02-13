package employeee;
import java.sql.*;
import java.util.*;

public class EmployeeDetails {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try (sc){
			//	LOADING
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//	CONNECTING TO DATABASE
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "raj", "0725");
			//	PREPARED STATEMENT FOR INSERTION
			PreparedStatement insertData = con.prepareStatement("insert into employee values(?,?,?,?,?,?,?)");
			//	PREPARED STATEMENT FOR SELECTION OF A TABLE
			PreparedStatement displayData = con.prepareStatement("select * from employee");
			// 	PREPARED STATEMENT FOR SELECTION BASED ON CONDITION
			PreparedStatement displayByCode = con.prepareStatement("select * from employee where id=?");
			//	PREPARED STATEMENT FOR UPDATION
			PreparedStatement updateData = con.prepareStatement("update employee set basic_sal=?,hra=?,da=?,sal=? where id=?");
			//	PREPARED STATEMENT FOR DELETION
			PreparedStatement delData = con.prepareStatement("delete from employee where id=?");
			
			while(true) {
				//	MENU 
				System.out.println("----------------------");
				System.out.println("***** Employee Details *****");
				System.out.println("----------------------");
				System.out.println("1. Add EmployeeDetails"
						+ "\n2. View All EmployeeDetails"
						+ "\n3. View EmployeeDetails By id"
						+ "\n4. Update EmployeeDetails(bsal,hra,da,sal) By id"
						+ "\n5. Delete EmployeeDetails By id"
						+ "\n6. Exit");
				System.out.println("----------------------");
				
				// USER OPTION FOR MENU
				System.out.print("Enter your option : ");
				int option = sc.nextInt();
				
				//	SWITCHING OPERATION BASED ON USER INPUT OPTION
				switch(option) {
				//	INSERTION
				case 1:
					System.out.print("Enter Employee id : ");
					int id = sc.nextInt();
					System.out.print("Enter Employee Name : ");
					String name = sc.nextLine();
					name = sc.nextLine();
					System.out.print("Enter Employee designation : ");
					String desg = sc.nextLine();
					System.out.print("Enter Employee basic salary : ");
					double bsal = sc.nextDouble();
					double hra = bsal*0.93;
					double da = bsal*0.61;
					double total = bsal+hra+da;
					insertData.setInt(1, id);
					insertData.setString(2, name);
					insertData.setString(3, desg);
					insertData.setDouble(4, bsal);
					insertData.setDouble(5, hra);
					insertData.setDouble(6, da);
					insertData.setDouble(7, total);
					int k = insertData.executeUpdate();
					if(k>0) {
						System.out.println("Data Inserted Successfully");
					}
					else {
						System.err.println("Invalid inputs");
					}
					break;
				//	DISPLAY ALL DATA IN THE TABLE
				case 2:
					ResultSet rs = displayData.executeQuery();
					while(rs.next()) {
						System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getDouble(4)+"\t"+rs.getDouble(5)+"\t"+rs.getDouble(6)+"\t"+rs.getDouble(7));
					}
					break;
				//	DISPLAY OF DATA BASED ON CONDITION	
				case 3:
					System.out.print("Enter Employee Id: ");
					int bcode = sc.nextInt();
					displayByCode.setInt(1, bcode);
					ResultSet rs1 = displayByCode.executeQuery();
					if(rs1.next()) {
						System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t"+rs1.getDouble(4)+"\t"+rs1.getDouble(5)+"\t"+rs1.getDouble(6)+"\t"+rs1.getDouble(7));
					}
					else {
						System.err.println("Data not found");
					}
					break;
				//	UPDATION OF DATA BASED ON CONDITION	
				case 4:
					System.out.print("Enter Employee id: ");
					int uid = sc.nextInt();
					displayByCode.setInt(1,uid);
					ResultSet rs2 = displayByCode.executeQuery();
					if(rs2.next()) {
						System.out.print("Enter new Employee salary: ");
						double nbsal = sc.nextDouble();
						double nhra = nbsal*0.93;
						double nda = nbsal*0.61;
						double ntotal = nbsal+nhra+nda;
						updateData.setDouble(1, nbsal);
						updateData.setDouble(2, nhra);
						updateData.setDouble(3, nda);
						updateData.setDouble(4, ntotal);
						updateData.setInt(5, uid);
						int k1 = updateData.executeUpdate();
						if(k1>0) {
							System.out.println("Data updated successfully");
						}
						else {
							System.err.println("invalid Employee Id");
						}
					}
					else {
						System.err.println("Data not found");
					}
					break;
				//	DELETION BASED ON CONDTION	
				case 5:
					System.out.print("Enter Employee Id: ");
					int did = sc.nextInt();
					displayByCode.setInt(1, did);
					ResultSet rs3= displayByCode.executeQuery();
					if(rs3.next()) {
						delData.setInt(1, did);
						int k3 = delData.executeUpdate();
						if(k3>=0) {
							System.out.println("Data Deleted Successfully");
						}
						else {
							System.err.println("Invalid Employee Id");
						}
					}
					else {
						System.err.println("Data not found!");
					}
					break;
				case 6:
					System.out.println("Thank you");
					con.close();
					System.exit(0);
				default:
					System.err.println("Invalid option");
				}
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
