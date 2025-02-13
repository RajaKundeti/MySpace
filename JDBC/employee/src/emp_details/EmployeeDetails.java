package emp_details;

import java.util.*;
import java.util.stream.Collectors;
import java.sql.*;

public class EmployeeDetails {

	public record Employee(int id, String name, double salary, String adress, String mail, String mobile)
	{

	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try (sc) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "raj", "0725");
				PreparedStatement ps1 = con.prepareStatement("insert into employee_info values(?,?,?,?,?,?)");
				PreparedStatement ps2 = con.prepareStatement("select * from employee_info");
				PreparedStatement ps3 = con.prepareStatement("update employee_info set salary=? where id=?");
				while (true) {
					System.out.println("------------------");
					System.out.println("*****Employee*****");
					System.out.println("------------------");
					System.out.println("1. Insert Employee Data");
					System.out.println("2. Employee Data Operations");
					System.out.println("3. Exit");
					System.out.println("---------------------");
					System.out.print("Enter your option: ");
					int op1 = sc.nextInt();
					switch (op1) {
					// INSERT EMPLOYEE DATA
					case 1:
						System.out.println("Enter Employee Details :");
						System.out.print("Id : ");
						int id = sc.nextInt();
						System.out.print("Name : ");
						String name = sc.nextLine();
						name = sc.nextLine();
						System.out.print("Salary : ");
						long salary = sc.nextLong();
						System.out.print("Adress : ");
						String adress = sc.nextLine();
						adress = sc.nextLine();
						System.out.print("Mail-Id : ");
						String mail = sc.nextLine();
						System.out.print("Mobile : ");
						String mobile = sc.nextLine();
						ps1.setInt(1, id);
						ps1.setString(2, name);
						ps1.setDouble(3, salary);
						ps1.setString(4, adress);
						ps1.setString(5, mail);
						ps1.setString(6, mobile);
						int k = ps1.executeUpdate();
						if (k > 0) {
							System.out.println("Data Inserted Successfully");
						} else {
							System.err.println("Invalid Inputs");
						}
						break;
					// EMPLOYEE DATA OPERATIONS
					case 2:
						List<Employee> empList = new ArrayList<>();
						ResultSet rs = ps2.executeQuery();
						while (rs.next()) {
							empList.add(new Employee(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4),
									rs.getString(5), rs.getString(6)));
						}

						System.out.println("1. Sort Employees Data based on Salary");
						System.out.println("2. Find Employees whose name starts with  a letter");
						System.out.println("3. Increase 10% of Employee salary(<20000)");
						System.out.println("4. Find Employees & salary within a range");
						System.out.println("5. Find No of Employees");
						System.out.println("-------------------------");
						System.out.print("Enter your Option : ");
						int op2 = sc.nextInt();

						switch (op2) {
						case 1:
							System.out.println("1. Ascending order");
							System.out.println("2. Descending order");
							System.out.println("-------------------------");
							int op3 = sc.nextInt();
							switch (op3) {
							case 1:
								System.out.println(
										empList.stream().sorted(Comparator.comparingDouble(Employee::salary))
												.collect(Collectors.toList()));
								break;
							case 2:
								empList.stream().sorted(Comparator.comparingDouble(Employee::salary).reversed())
										.forEach(System.out::println);
							default:
								System.err.println("Invalid option");
							}

							break;
						case 2:
							System.out.print("Enter starting letter : ");
							String sw = sc.next();
							empList.stream().filter(t -> t.name().startsWith(sw)).forEach(System.out::println);
							break;
						case 3:
							List<Employee> salInc = empList.stream().filter(t -> t.salary() < 20000)
									.collect(Collectors.toList());
							int k1 = 0;
							for (Employee e : salInc) {
								ps3.setDouble(1, e.salary() * 0.1);
								ps3.setInt(2, e.id());
								k1 += ps3.executeUpdate();
							}
							if (k1 > 0) {
								System.out.println(k1 + " employees updated succuessfully");
							} else {
								System.err.println("No Salary found i.e, <20000");
							}
							break;
						case 4:
							empList.stream().filter(e -> e.salary() >= 20000 && e.salary() <= 40000)
									.forEach(System.out::println);
							break;
						case 5:
							System.out.println("No of Employees : " + empList.size());
							break;
						default:
							System.err.println("Invalid option");
						}
						break;
					case 3:
						System.out.println("Thank you");
						con.close();
						System.exit(0);
						// INVALID OPTION
					default:
						System.err.println("Invalid option");
					}
				}

			} catch (Exception e) {
				e.getMessage();
			}
		}
	}
}
