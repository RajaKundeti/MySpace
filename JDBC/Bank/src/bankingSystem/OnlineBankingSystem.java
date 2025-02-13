package bankingSystem;
import java.util.*;
import java.sql.*;

public class OnlineBankingSystem {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc){
			try {
				//	CONNECTING TO DATABASE DISABLING COMMIT SAVING SAVEPOINT
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				Connection con = DriverManager.getConnection(url, "raj", "0725");
				con.setAutoCommit(false);
				Savepoint sp = con.setSavepoint();
				
				//	TAKING USER INPUT FOR ACCOUNTS AND AMOUNT
				System.out.print("Enter Sender Account Number: ");
				long senderAccount  = sc.nextLong();
				System.out.print("Enter Reciever Account Number: ");
				long recieverAccount  = sc.nextLong();
				System.out.print("Enter Transfer Amount: ");
				float amount  = sc.nextFloat();
				
				//	CHECKING IF THE AMOUNT IS VALID
				if(amount <=0) {
					System.err.println("Invalid Amount");
					System.exit(0);
				}
				//	CHECKING ACCOUNTS IN THE DATABASE EXIST OR NOT
				boolean acc = false;
				PreparedStatement ps = con.prepareStatement("select * from accounts where account_number=?");
				ps.setLong(1, senderAccount);
				ResultSet rs = ps.executeQuery();
				
				//	CHECKING SENDER ACCOUNT IS EXIST OR NOT
				if(rs.next()) {
					ps.setLong(1, recieverAccount);
					rs = ps.executeQuery();
					
					//	CHECKING RECEIVER ACCOUNT IS EXIST OR NOT
					if(rs.next()) {
						acc = true;
					}
					
					//	RECEIVER ACCOUNT DOESNOT EXIST
					else {
						System.err.println("Receiver Account doesn't exist");
						System.exit(0);
					}
				}
				
				//	SENDER ACCOUNT DOESNOT EXIST
				else {
					System.err.println("Sender Account doesn't exist");
					System.exit(0);
				}
				
				//	IF EXISTS
				if(acc) {
					float senderBalance = getAccountBalance(senderAccount,con);
					
					//	CHECKING IF THE BALANCE IS SUFFICIENT
					if(amount <= senderBalance) {
						
						//	UPDATING ACCOUNTS BALANCE
						boolean isSent = updateAccountBalance(senderAccount, -amount,con);
						boolean isReceive = updateAccountBalance(recieverAccount, amount, con);
						
						//	CHECKING IF BOTH SENDING AND RECEIVING ARE DONE 
						if(isSent && isReceive) {
							System.out.println("Transaction Successfull");
							con.commit();
							con.close();
						}
						
						//	SENDING OR RECEIVING FAILED
						else {
							con.rollback(sp);
							System.err.println("Transaction Failed, Rollback Changes...");
						}
					}
					
					//	INSUFFICIENT BALANCE IN SENDER ACCOUNT
					else {
						System.out.println("Insufficient Balance");
					}
					
				}
				
			} catch (Exception e) {
				e.getMessage();
				System.err.println("Transaction failed");
			}
		}
	}

	//	METHOD FOR UPDATING ACCOUNT BALANCE
	private static boolean updateAccountBalance(long accountNo, float amount, Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement("update accounts set balance = balance + ? where account_number=?");
		ps.setFloat(1, amount);
		ps.setLong(2, accountNo);
		int k = ps.executeUpdate();
		if(k>0) {
			return true;
		}
		else {
			return false;
		}
	}

	//	METHOD FOR GETTING ACCOUNT BALANCE OF ACCOUNT
	private static float getAccountBalance(long accountNo, Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement("select balance from accounts where account_number=?");
		ps.setLong(1, accountNo);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getFloat(1);
		}
		return 0.0f;
	}

}
