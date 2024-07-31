package hotel.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

import hotel.dto.Register;

public class Hotelfunction {
	Scanner sc = new Scanner(System.in);

	public void register(Register reg) throws Exception {
		DriverManager.registerDriver(new Driver());
		FileInputStream stream = new FileInputStream("access.properties");
		Properties properties = new Properties();
		properties.load(stream);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", properties);
		PreparedStatement sta = con.prepareStatement("insert into register values(?,?,?,?,?,?)");
		sta.setInt(1, reg.getId());
		sta.setString(2, reg.getName());
		sta.setLong(3, reg.getPh());
		sta.setDouble(4, reg.getWallet());
		sta.setString(5, reg.getEmail());
		sta.setString(6, reg.getPwd());
		sta.execute();
		sta.close();
		con.close();
		System.out.println("registration done...");

	}

	public void login() throws Exception {

		DriverManager.registerDriver(new Driver());
		FileInputStream stream = new FileInputStream("access.properties");
		Properties properties = new Properties();
		properties.load(stream);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", properties);
		PreparedStatement sta = con.prepareStatement("Select * from register where email=? and pwd=?");
		System.out.println("enter your email");
		String em = sc.next();
		sta.setString(1, em);
		System.out.println("enter your password");
		String pwd = sc.next();
		sta.setString(2, pwd);
		ResultSet res = sta.executeQuery();
		if (res.next()) {
			System.out.println("login successful");
			

			boolean ch = true;
			double sum = 0;
			do {
				menu();
				System.out.println("enter choice \n 1.Get Food \n 2.Bill Generate \n 3.Exit");
				int c = sc.nextInt();
				switch (c) {
				case 1: {
					System.out.print("enter your food id=");
					int d = sc.nextInt();
					fetch(d);
					System.out.print("enter quantity=");
					int q = sc.nextInt();
					double s =price(d);
					double p = s * q;
					sum = sum + p;
					System.out.print("Your Total bill=");
					System.out.println(sum);
					
				}
					break;
				case 2:{
					double w=wallet(em,pwd);
					System.out.println("Your current balance="+w);
					if(w>=sum) {
						double p=w-sum;
						update(p,em,pwd);
						System.out.println("Amount has been debeted");
						System.out.println("Your Order is Placed");
						w=wallet(em,pwd);
						System.out.println("Your Remaining Balance"+w);
					}else {
						System.out.println("your Wallet Balance is Insufficient");
						System.out.println("1.Add Money to Wallet \n 2.exit");
						int a=sc.nextInt();
						switch (a) {
						case 1:{
							System.out.println("Enter The Amount To Add In Your Wallet=");
							Double d=sc.nextDouble();
							d+=w;
							update(d,em,pwd);
							System.out.println("Amount has been Credited");
							System.out.println(wallet(em,pwd));
							System.out.println();
							System.out.println();
							System.out.println();
						}
							break;
						case 2:{
							ch=false;
						}
							break;
							
						default:
							ch=false;
							break;
						}
					}
					break;
				}
				case 3:
					sum = 0;
				
					ch = false;
					break;
				default:
					System.out.println("Invalid choice");
					ch=false;  
					break;
				}
			} while (ch);

		} else {
			System.out.println("Invalid Email or Password");
			login();
		}
		sta.close();
		con.close();

	}

	// food fun
	public void menu() throws Exception {
		DriverManager.registerDriver(new Driver());
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "Arunmadhu2631");
		Statement sta = con.createStatement();
		ResultSet res = sta.executeQuery("select * from food");
		System.out.println("          Food Menu ");
		while (res.next()) {
			System.out.print("           " + res.getInt(1) + "   ");
			System.out.print(res.getString(2) + "   ");
			System.out.print(res.getDouble(3) + "   " + "\n");
		}
		sta.close();
		con.close();
	}

	public void fetch(int c) throws Exception {
		DriverManager.registerDriver(new Driver());
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "Arunmadhu2631");
		Statement sta = con.createStatement();
		ResultSet res = sta.executeQuery("select * from food where id=" + c + "");
		while (res.next()) {
			System.out.print("           " + res.getInt(1) + "   ");
			System.out.print(res.getString(2) + "   ");
			System.out.print(res.getDouble(3) + "   " + "\n");
		}
		sta.close();
		con.close();
	}

	public double price(int c) throws Exception {
		DriverManager.registerDriver(new Driver());
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "Arunmadhu2631");
		PreparedStatement sta = con.prepareStatement("select * from food where id=?");
		sta.setInt(1, c);
		ResultSet res = sta.executeQuery();
		double s = 0;
		while (res.next()) {
			s = res.getDouble(3);
		}
		sta.close();
		con.close();
		return s;
	}

	public double wallet(String em, String pwd) throws Exception {
		DriverManager.registerDriver(new Driver());
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "Arunmadhu2631");
		PreparedStatement sta = con.prepareStatement("select wallet from register where email= ? && pwd =?");
		sta.setString(1, em);
		sta.setString(2, pwd);
		ResultSet res = sta.executeQuery();
		double s = 0;
		while (res.next()) {
			s = res.getDouble(1);
	
		}
		sta.close();
		con.close();
		return s;
	}
	
	public void update(double d,String em, String pwd) throws Exception {
		DriverManager.registerDriver(new Driver());
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "Arunmadhu2631");
		PreparedStatement sta = con.prepareStatement("update register set wallet=? where email=? && pwd=?");
		sta.setDouble(1, d);
		sta.setString(2, em);
		sta.setString(3, pwd);
		sta.executeUpdate();
		sta.close();
		con.close();

		
	}

}
