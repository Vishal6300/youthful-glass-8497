package com.masaiproject.useCases;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.masaiproject.Exception.EmployeeException;
import com.masaiproject.Exception.EngineerException;
import com.masaiproject.dao.EmployeeDao;
import com.masaiproject.dao.EmployeeDaoImpl;
import com.masaiproject.dao.EngineerDao;
import com.masaiproject.dao.EngineerDaoImpl;

public class EngineerChangePassword {
	public void engineerChangePassword() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your username");
		String username = sc.next();
		System.out.println("Enter your old password");
		String oldPassword = sc.next();
		System.out.println("Enter new password to be set.Password should have 8 to 20 characters without"
				+ "space and should include atleast 1 digit 1 uppercase 1 lowercase letter"
				+ "and a special character.");
		String newPassword = sc.next();
		
		String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
		
		Pattern p = Pattern.compile(regex);
		
		Matcher m = p.matcher(newPassword);
		
		if(m.matches()) {
			EngineerDao dao = new EngineerDaoImpl();
			
			try {
				String res = dao.changeEngineerPassword(username, oldPassword, newPassword);
				System.out.println(res);
			} catch (EngineerException e) {
				System.out.println(e.getMessage());
			}
		}else {
			System.out.println("Password should have 8 to 20 characters and must include"
					+ " an uppercase letter, a lowercase letter, a number and a special character"
					+ "without a space.");
		}
	}
}
