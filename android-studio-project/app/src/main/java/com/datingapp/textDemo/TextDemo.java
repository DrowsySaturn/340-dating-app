package com.datingapp.textDemo;

/*
* This is a prototype class version 1.
* The purpose of this class is to display the SignupLoginController.
*/

import com.datingapp.controller.SignupLoginController;
import com.datingapp.shared.datapersistence.Account;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextDemo {
    public static void main(String[] args) {
        try {
            initialize();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(TextDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void initialize() throws NoSuchAlgorithmException {
        run();
    }

    public static void run() throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        int input;
        do{
            System.out.println("1. Sign up a account.");
            System.out.println("2. Login in a account.");
            System.out.println("0. Exit Program");
            System.out.println("Enter either 1 or 2 for an action!");
            input = Integer.parseInt(sc.nextLine());
            if(input == 1) {
                System.out.println();
                System.out.println();
                System.out.println("Sign up interface");
                System.out.println("Email:");
                String email = sc.nextLine();
                System.out.println("Password:");
                String password = sc.nextLine();
                System.out.println("Name:");
                String name = sc.nextLine();
                System.out.println("Age:");
                int age = sc.nextInt();
                System.out.println("Say something about yourself");
                String personalMessage = sc.nextLine();
                SignupLoginController.signupAccount(age, name, personalMessage, email, password);
            } else if(input == 2) {
                System.out.println("");
                System.out.println("");
                System.out.println("Login interface");
                System.out.println("Login User Email:");
                String email = sc.nextLine();
                System.out.println("Login User Password:");
                String password = sc.nextLine();
                Account existingAccount = SignupLoginController.loginAccount(email, password);
                System.out.println(String.format("The current in session account is %s, Hi %s", existingAccount.getEmail(), existingAccount.getName()));
            } else {
                System.out.println("Incorrect input!");
            }
        } while (input != 0);
    }
}
