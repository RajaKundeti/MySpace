package bookstore;

import java.sql.*;
import java.util.*;

public class BookStore {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try (sc){
			//	LOADING
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//	CONNECTING TO DATABASE
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "raj", "0725");
			//	PREPARED STATEMENT FOR INSERTION
			PreparedStatement insertData = con.prepareStatement("insert into BookDetails values(?,?,?,?,?)");
			//	PREPARED STATEMENT FOR SELECTION OF A TABLE
			PreparedStatement displayData = con.prepareStatement("select * from BookDetails");
			// 	PREPARED STATEMENT FOR SELECTION BASED ON CONDITION
			PreparedStatement displayByCode = con.prepareStatement("select * from BookDetails where code=?");
			//	PREPARED STATEMENT FOR UPDATION
			PreparedStatement updateData = con.prepareStatement("update BookDetails set price=?,quantity=quantity+? where code=?");
			//	PREPARED STATEMENT FOR DELETION
			PreparedStatement delData = con.prepareStatement("delete from BookDetails where code=?");
			
			while(true) {
				//	MENU 
				System.out.println("----------------------");
				System.out.println("***** Book Store *****");
				System.out.println("----------------------");
				System.out.println("1. Add BookDetails"
						+ "\n2. View All BookDetails"
						+ "\n3. View BookDetails By Code"
						+ "\n4. Update BookDetails By Code(price - qty)"
						+ "\n5. Delete BookDetails By Code"
						+ "\n6. Exit");
				System.out.println("----------------------");
				
				// USER OPTION FOR MENU
				System.out.print("Enter your option : ");
				int option = sc.nextInt();
				
				//	SWITCHING OPERATION BASED ON USER INPUT OPTION
				switch(option) {
				//	INSERTION
				case 1:
					System.out.print("Enter Book Code : ");
					int code = sc.nextInt();
					System.out.print("Enter Book Name : ");
					String name = sc.nextLine();
					name = sc.nextLine();
					System.out.print("Enter Book author : ");
					String author = sc.nextLine();
					System.out.print("Enter Book price : ");
					double price = sc.nextDouble();
					System.out.print("Enter Book quantity : ");
					int quantity = sc.nextInt();
					insertData.setInt(1, code);
					insertData.setString(2, name);
					insertData.setString(3, author);
					insertData.setDouble(4, price);
					insertData.setInt(5, quantity);
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
						System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getDouble(4)+"\t"+rs.getInt(5));
					}
					break;
				//	DISPLAY OF DATA BASED ON CONDITION	
				case 3:
					System.out.print("Enter Book Code: ");
					int bcode = sc.nextInt();
					displayByCode.setInt(1, bcode);
					ResultSet rs1 = displayByCode.executeQuery();
					if(rs1.next()) {
						System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t"+rs1.getDouble(4)+"\t"+rs1.getInt(5));
					}
					else {
						System.err.println("Data not found");
					}
					break;
				//	UPDATION OF DATA BASED ON CONDITION	
				case 4:
					System.out.print("Enter Book Code: ");
					int ucode = sc.nextInt();
					displayByCode.setInt(1,ucode);
					ResultSet rs2 = displayByCode.executeQuery();
					if(rs2.next()) {
						System.out.print("Old Book Price: "+rs2.getDouble(4));
						System.out.print("Old Book Quantity: "+rs2.getInt(5));
						System.out.print("Enter new BookPrice: ");
						double nPrice = sc.nextDouble();
						System.out.print("Enter new BookQuantity: ");
						int nqty = sc.nextInt();
						updateData.setDouble(1, nPrice);
						updateData.setInt(2, nqty);
						updateData.setInt(3, ucode);
						int k1 = updateData.executeUpdate();
						if(k1>0) {
							System.out.println("Data updated successfully");
						}
						else {
							System.err.println("invalid Book Code");
						}
					}
					else {
						System.err.println("Data not found");
					}
					break;
				//	DELETION BASED ON CONDTION	
				case 5:
					System.out.print("Enter Book Code: ");
					int dcode = sc.nextInt();
					displayByCode.setInt(1, dcode);
					ResultSet rs3= displayByCode.executeQuery();
					if(rs3.next()) {
						delData.setInt(1, dcode);
						int k3 = delData.executeUpdate();
						if(k3>=0) {
							System.out.println("Data Deleted Successfully");
						}
						else {
							System.err.println("Invalid Book Code");
						}
					}
					else {
						System.err.println("Data not found!");
					}
					break;
				case 6:
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
