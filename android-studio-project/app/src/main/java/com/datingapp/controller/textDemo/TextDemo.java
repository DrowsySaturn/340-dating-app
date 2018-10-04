package com.datingapp.controller.textDemo;

/*
 * The purpose of this class is to display a text demo of SignupLoginController.
 *
 * @Author: Vincent Yang
 *
 * @Version 1:9/25/2018
 */

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.controller.SignupLoginController;
import com.datingapp.shared.datapersistence.LoginInformation;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextDemo {

    /**
     * This is the main method.
     * @param args
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void main(String[] args) {
        try {
            initialize();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(TextDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method initialize the program.
     * @throws NoSuchAlgorithmException
     * @throws SQLException
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void initialize() throws NoSuchAlgorithmException, SQLException {
        run();
    }


    /**
     * This method will run the program.
     * @throws NoSuchAlgorithmException
     * @throws SQLException
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void run() throws NoSuchAlgorithmException, SQLException {
        Scanner sc = new Scanner(System.in);
        int input;
        LoginInformation existingLoginInformation = null;
        do{
            menu();
            input = Integer.parseInt(sc.nextLine());
            if(input == LoginSignupExecuteCommand.SIGN_UP) {
                signUpInterface(sc);
                System.out.println("Login interface");
            } else if(input == LoginSignupExecuteCommand.LOGIN_IN) {
                loginInterface(sc, existingLoginInformation);
                System.out.println("Sign Up interface");
            } else if(input == LoginSignupExecuteCommand.EXIT) {
                System.out.println("Input 0, exiting program");
            } else {
                System.out.println("Invalid input, try again");
            }
        } while (input != LoginSignupExecuteCommand.EXIT);
    }


    /**
     * This is login interface.
     * @param _sc
     * @param _user_account
     * @throws NoSuchAlgorithmException
     * @throws SQLException
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static void loginInterface(Scanner _sc, LoginInformation _user_account) throws NoSuchAlgorithmException, SQLException {
        System.out.println("");
        System.out.println("");
        System.out.println("Login interface");
        System.out.println("Login User Email:");
        String email = _sc.nextLine();
        System.out.println("Login User Password:");
        String password = _sc.nextLine();
        _user_account = SignupLoginController.login(email, password);
        System.out.println(String.format("The current in session account is %s", _user_account.getEmail()));
    }


    /**
     * This is the menu.
     */
    private static void menu() {
        System.out.println("1. Sign up a account.");
        System.out.println("2. Login in a account.");
        System.out.println("0. Exit Program");
        System.out.println("Enter either 1 or 2 for an action!");
    }


    /**
     * This is the sign up interface.
     * @param _sc
     * @throws NoSuchAlgorithmException
     * @throws SQLException
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static void signUpInterface(Scanner _sc) throws NoSuchAlgorithmException, SQLException{
        System.out.println();
        System.out.println();
        System.out.println("Sign up interface");
        System.out.println("Email:");
        String email = _sc.nextLine();
        System.out.println("Password:");
        String password = _sc.nextLine();
        SignupLoginController.signUp(email, password);
    }


}
