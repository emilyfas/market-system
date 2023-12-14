package application;

import management.Admin;
import storage.Product;

import java.sql.*;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws SQLException {

        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Elmore Market!");
        while (true) {
            System.out.print("Email: ");
            String email = scan.next();
            System.out.print("Password: ");
            scan.nextLine();
            String password = scan.next();

            System.out.println("\nWaiting...\n");

            Admin admin = new Admin("", email, password);
            admin.login();

            if (!admin.isLoggedIn()) {
                System.out.println("Invalid user! Try another Login.");
            } else {
                UI.optionsMenu(admin);
                break;
            }
        }

    }
}
