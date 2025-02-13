package student;

import java.sql.*;
import java.util.*;

public class StudentDetails {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try (sc) {
			try {
				// LOADING
				Class.forName("oracle.jdbc.driver.OracleDriver");
				// CONNECTING TO DATABASE
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "raj", "0725");
				// PREPARED STATEMENT FOR INSERTION
				PreparedStatement insertData = con.prepareStatement("insert into student values(?,?,?,?,?,?)");
				// PREPARED STATEMENT FOR SELECTION OF A TABLE
				PreparedStatement displayData = con.prepareStatement("select * from student");
				// PREPARED STATEMENT FOR SELECTION BASED ON CONDITION
				PreparedStatement displayByCode = con.prepareStatement("select * from student where roll=?");
				// PREPARED STATEMENT FOR DELETION
				PreparedStatement delData = con.prepareStatement("delete from student where roll=?");
				while (true) {
					// MENU
					System.out.println("----------------------");
					System.out.println("***** Student Details *****");
					System.out.println("----------------------");
					System.out.println("1. Add Student Details" + "\n2. View All Student Details"
							+ "\n3. View Student Details By Code" + "\n4. Delete Student Details By Code"
							+ "\n5. Exit");
					System.out.println("----------------------");

					// USER OPTION FOR MENU
					System.out.print("Enter your option : ");
					int option = sc.nextInt();

					switch (option) {
					// INSERTION
					case 1:
						System.out.print("Enter Student Roll : ");
						int roll = sc.nextInt();
						System.out.print("Enter Student Name : ");
						String name = sc.nextLine();
						name = sc.nextLine();
						System.out.print("Enter Student branch : ");
						String branch = sc.nextLine();
						System.out.println("Enter Student Marks : ");
						System.out.print("Telugu: ");
						double total = sc.nextDouble();
						System.out.print("English: ");
						total += sc.nextDouble();
						System.out.print("Hindi: ");
						total += sc.nextDouble();
						System.out.print("Science: ");
						total += sc.nextDouble();
						System.out.print("Social: ");
						total += sc.nextDouble();
						double per = total / 5;
						String grade;
						if (per > 90.0)
							grade = "A";
						else if (per > 80.0)
							grade = "B";
						else if (per > 70.0)
							grade = "C";
						else if (per > 60.0)
							grade = "D";
						else if (per > 40.0)
							grade = "E";
						else
							grade = "F";
						insertData.setInt(1, roll);
						insertData.setString(2, name);
						insertData.setString(3, branch);
						insertData.setDouble(4, total);
						insertData.setDouble(5, per);
						insertData.setString(6, grade);
						int k = insertData.executeUpdate();
						if (k > 0) {
							System.out.println("Data Inserted Successfully");
						} else {
							System.err.println("Invalid inputs");
						}
						break;
					// DISPLAY ALL DATA IN THE TABLE
					case 2:
						ResultSet rs = displayData.executeQuery();
						while (rs.next()) {
							System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
									+ rs.getDouble(4) + "\t" + rs.getDouble(5) + "\t" + rs.getString(6));
						}
						break;
					// DISPLAY OF DATA BASED ON CONDITION
					case 3:
						System.out.print("Enter Student Roll: ");
						int droll = sc.nextInt();
						displayByCode.setInt(1, droll);
						ResultSet rs1 = displayByCode.executeQuery();
						if (rs1.next()) {
							System.out.println(rs1.getInt(1) + "\t" + rs1.getString(2) + "\t" + rs1.getString(3) + "\t"
									+ rs1.getDouble(4) + "\t" + rs1.getDouble(5) + "\t" + rs1.getString(6));
						} else {
							System.err.println("Data not found");
						}
						break;
					// DELETION BASED ON CONDTION
					case 4:
						System.out.print("Enter Student roll: ");
						int delroll = sc.nextInt();
						displayByCode.setInt(1, delroll);
						ResultSet rs3 = displayByCode.executeQuery();
						if (rs3.next()) {
							delData.setInt(1, delroll);
							int k3 = delData.executeUpdate();
							if (k3 >= 0) {
								System.out.println("Data Deleted Successfully");
							} else {
								System.err.println("Invalid Student roll");
							}
						} else {
							System.err.println("Data not found!");
						}
						break;
					case 5:
						con.close();
						System.exit(0);
					default:
						System.err.println("Invalid option");
					}
				}
			} catch (ClassNotFoundException e) {
				System.err.println("Please check the connections once");
			} catch (Exception e) {
				System.err.println("Invalid Operatin occured, Please restart the application !");
			}
		}
	}
}
