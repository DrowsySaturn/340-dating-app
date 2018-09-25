package com.datingapp.controller.textDemo;

/*
 * The purpose of this class is to display a text demo of SignupLoginController.
 *
 * @Author: Vincent Yang
 *
 * @Version 1:9/25/2018
 */

import com.datingapp.controller.SignupLoginController;
import com.datingapp.shared.datapersistence.UserAccount;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextDemo {


    public static void main(String[] args) {
        try {
            initialize();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(TextDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    public static void initialize() throws NoSuchAlgorithmException, SQLException {
        run();
    }


    public static void run() throws NoSuchAlgorithmException, SQLException {
        Scanner sc = new Scanner(System.in);
        int input;
        UserAccount existingUserAccount = null;
        do{
            menu();
            input = Integer.parseInt(sc.nextLine());
            if(input == 1) {
//                signUpInterface(sc);
                System.out.println("Login interface");
            } else if(input == 2) {
//                loginInterface(sc, existingUserAccount);
                System.out.println("Sign Up interface");
            } else if(input == 0) {
                System.out.println("Input 0, exiting program");
            } else {
                System.out.println("Invalid input, try again");
            }
        } while (input != 0);
    }


    private static void loginInterface(Scanner _sc, UserAccount _User_account) throws NoSuchAlgorithmException, SQLException {
        System.out.println("");
        System.out.println("");
        System.out.println("Login interface");
        System.out.println("Login User Email:");
        String email = _sc.nextLine();
        System.out.println("Login User Password:");
        String password = _sc.nextLine();
        _User_account = SignupLoginController.loginAccount(email, password);
        System.out.println(String.format("The current in session account is %s", _User_account.getEmail()));
    }


    private static void menu() {
        System.out.println("1. Sign up a account.");
        System.out.println("2. Login in a account.");
        System.out.println("0. Exit Program");
        System.out.println("Enter either 1 or 2 for an action!");
    }


    private static void signUpInterface(Scanner _sc) throws NoSuchAlgorithmException, SQLException{
        System.out.println();
        System.out.println();
        System.out.println("Sign up interface");
        System.out.println("Email:");
        String email = _sc.nextLine();
        System.out.println("Password:");
        String password = _sc.nextLine();
        SignupLoginController.signupAccount(email, password);
    }


}
