package application;

import management.Staff;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Elmore Market!");
        while (true) {
            System.out.print("Email: ");
            String email = scan.next();
            System.out.print("Password: ");
            scan.nextLine();
            String password = scan.next();

            System.out.println("\nWaiting...\n");

            Staff staff = new Staff("", email, password);
            staff.login();

            if (!staff.isLoggedIn()) {
                System.out.println("Invalid user! Try another Login.");
            } else {
                UI.optionsMenu(staff);
                break;
            }
        }
    }
}
