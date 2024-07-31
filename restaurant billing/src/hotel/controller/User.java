package hotel.controller;

import java.util.Scanner;

import hotel.dao.Hotelfunction;
import hotel.dto.Register;

public class User {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Hotelfunction fun = new Hotelfunction();
		Boolean check = true;
		do {
			System.out.println("enter your choice \n 1.registration \n 2.Login \n 3.Exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				System.out.println("enter id=");
				int id = sc.nextInt();
				System.out.println("enter name");
				String name = sc.next();
				System.out.println("enter phno");
				long phno = sc.nextLong();
				System.out.println("enter Wallet Amount");
				double wallet = sc.nextDouble();
				System.out.println("enter email");
				String email = sc.next();
				System.out.println("enter Password");
				String pwd = sc.next();

				Register reg = new Register(id, name, phno, wallet, email, pwd);
				fun.register(reg);

			}
				break;
			case 2: {
				fun.login();
				// ============================================

			}
			case 3: {
				System.out.println("Thank You For Coming");
					check=false;
			}
				break;
				
			default:
				System.out.println("Invalid Choice");
				check=false;
				break;
			}

		} while (check);
	}

}
