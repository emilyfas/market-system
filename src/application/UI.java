package application;

import management.Staff;
import management.enums.Role;
import storage.Category;
import storage.Product;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class UI {

    public static void optionsMenu(Staff staff) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("\n------------ Menu Operations ------------");
            System.out.println("1. Products Operations");
            System.out.println("2. Purchase");
            System.out.println("3. User Accounts Operations");
            if (staff.getRole() == Role.MANAGER || staff.getRole() == Role.DEVELOPER) {
                System.out.println("4. Staff Accounts Operations");
            }
            System.out.println("0. Exit Program");
            System.out.print("Insert an option: ");
            int ans = scan.nextInt();
            if (ans == 1) {
                productOperations(scan);
            } else if (ans == 3) {
                userAccountsOperations(scan);
            } else if (ans == 4 && (staff.getRole() == Role.MANAGER || staff.getRole() == Role.DEVELOPER)){
                staffAccountsOperations(scan);
            }
            else{
                System.out.println("END");
                break;
            }
        }

        scan.close();
    }

    public static void staffAccountsOperations(Scanner scan) {
        while (true) {

            System.out.println("\n------------ Staff Account Operations ------------");
            System.out.println("1. Add staff member");
            System.out.println("2. Remove staff member");
            System.out.println("3. Update staff member info");
            System.out.println("4. See staff members");
            System.out.println("5. Search for staff member");
            System.out.println("0. Exit to 'Menu Operations'");
            System.out.print("Insert an option: ");

            int ans = scan.nextInt();
            if (ans == 1) {
                scriptAddStaff(scan);
            } else if (ans == 2) {
                scriptRemoveStaff(scan);
            } else if (ans == 3) {

            } else if (ans == 4) {
                printStaffMembers();
            } else if (ans == 5) {

            } else if (ans == 0) {
                break;
            } else{
                System.out.println("Invalid Input!");
            }
        }
    }

    private static void scriptRemoveStaff(Scanner scan) {
        while (true) {
            System.out.println("\n------------ Remove Satff Member ------------");
            System.out.print("Id: ");
            int id = scan.nextInt();

            System.out.println("\nWaiting...\n");

            Staff staff = Staff.getStaffFromId(id);
            if (staff.getName() == null) {
                System.out.println("Invalid ID!");
                continue;
            }

            System.out.println("Removing staff member of name: "+ staff.getName());

            Staff.removeStaffFromDataBase(id);

            System.out.print("Want to remove another?(y/N) ");
            String ans = scan.next();
            if (ans.equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    private static void scriptAddStaff(Scanner scan) {
        while (true) {
            System.out.println("\n------------ Add Staff Member ------------");
            System.out.print("Name: ");
            String name = scan.next();

            System.out.print("Mobile: ");
            scan.nextLine();
            String mobile = scan.nextLine();

            System.out.print("Email: ");
            String email = scan.next();

            System.out.print("Adress Code: ");
            scan.nextLine();
            String adressCode = scan.next();

            String role = "";
            while (true) {
                System.out.print("Role: ");
                scan.nextLine();
                role = scan.next();

                if (isValidRole(role)) {
                    break;
                }
                System.out.println("\nInvalid Role!");
            }

            System.out.print("Salary: ");
            double salary = scan.nextDouble();

            String password = getValidPassword(scan);

            System.out.println("\nWaiting...\n");
            Staff.addToStaffToDatabase(new Staff(name, mobile, email, adressCode, password, Role.valueOf(role), salary));

            System.out.print("Want to add another?(y/N) ");
            String ans = scan.next();
            if (ans.equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    private static String getValidPassword(Scanner scan) {
        while (true) {
            System.out.print("Password: ");
            scan.nextLine();
            String password = scan.next();

            scan.nextLine();
            System.out.print("Confirm Password: ");
            if (scan.next().equals(password)) {
                return password;
            }
            System.out.println("Incorrect match of password!");
        }
    }

    private static boolean isValidRole(String test) {
        test = test.toUpperCase();
        for (Role c : Role.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    private static void userAccountsOperations(Scanner scan){
        while (true) {

            System.out.println("\n------------ User Account Operations ------------");
            System.out.println("1. Sign in user");
            System.out.println("2. Remove user");
            System.out.println("3. See user information");
            System.out.println("4. See user orders");
            System.out.println("0. Exit to 'Menu Operations'");
            System.out.print("Insert an option: ");

            int ans = scan.nextInt();
            if (ans == 1) {
                // sign in
            } else if (ans == 2) {
                // remove
            } else if (ans == 3) {
                // view info
            } else if (ans == 4) {
                // view order
            } else if (ans == 0) {
                break;
            } else{
                System.out.println("Invalid Input!");
            }
        }
    }

    private static void printStaffMembers() {
        ArrayList<Staff> list = Staff.getAllStaff();
        System.out.format("%-4s%-25s%-20s%-12s%-17s%-10s%-25s%-15s%n", "Id", "Name", "Mobile", "Role", "Adress Code", "Salary", "Email", "Password");


        for (Staff staff : list) {
            System.out.format("%-4s%-25s%-20s%-12s%-17s%-10s%-25s%-15s%n", staff.getIdUser(), staff.getName(), staff.getMobile(), staff.getRole(), staff.getAddressCode(), staff.getSalary(), staff.getEmail(), staff.getPassword());
        }
        System.out.println();
    }

    private static void productOperations(Scanner scan){
        while (true) {

            System.out.println("\n------------ Product Operations ------------");
            System.out.println("1. Add Products");
            System.out.println("2. Remove Products");
            System.out.println("3. Update Product Information");
            System.out.println("4. Print Catalog");
            System.out.println("0. Exit to 'Menu Operations'");
            System.out.print("Insert an option: ");

            int ans = scan.nextInt();
            if (ans == 1) {
                scriptAddProduct(scan);
            } else if (ans == 2) {
                scriptRemoveProduct(scan);
            } else if (ans == 3) {
                scriptUpdateProduct(scan);
            } else if (ans == 4) {
                System.out.println();
                printCatalog();
            } else if (ans == 0) {
                break;
            } else{
                System.out.println("Invalid Input!");
            }
        }
    }

    private static void printCatalog() {
        ArrayList<Product> list = Product.getAllProducts();
        System.out.format("%-5s%-15s%-17s%-9s%-10s%n", "Id", "Name", "Category", "Stock", "Price");


        for (Product product : list) {
            System.out.format("%-5s%-15s%-17s%-9s%-10s%n", product.getId(), product.getName(), product.getCategory(), product.getStock(), product.getPrice());
        }
        System.out.println();
    }

    private static void scriptUpdateProduct(Scanner scan) {
        while (true) {
            System.out.println("\n------------ Update Product ------------");
            System.out.print("Id: ");
            int id = scan.nextInt();

            System.out.println("\nWaiting...\n");

            Product product = Product.getProductFromId(id);
            if (product.getName() == null) {
                System.out.println("Invalid ID!");
                continue;
            }

            System.out.println("Updating product of name: "+ product.getName());

            System.out.print("Name: ");
            String name = scan.next();

            System.out.print("Category: ");
            scan.nextLine();
            String category = scan.next();

            if (!isValidCategory(category)) {
                System.out.println("\nInvalid Category!");
                continue;
            }

            System.out.print("Stock: ");
            int stock = scan.nextInt();

            System.out.print("Price: ");
            double price = scan.nextDouble();

            System.out.println("\nWaiting...\n");

            Product.updateProduct(id, name, category, stock, price);
            System.out.println("\nProduct Updated!\n");

            System.out.print("Want to update another?(y/N) ");
            String ans = scan.next();
            if (ans.equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    private static void scriptRemoveProduct(Scanner scan) {
        while (true) {
            System.out.println("\n------------ Remove Product ------------");
            System.out.print("Id: ");
            int id = scan.nextInt();

            System.out.println("\nWaiting...\n");

            Product product = Product.getProductFromId(id);
            if (product.getName() == null) {
                System.out.println("Invalid ID!");
                continue;
            }

            System.out.println("Removing product of name: "+ product.getName());

            Product.removeProductFromDataBase(id);

            System.out.print("Want to remove another?(y/N) ");
            String ans = scan.next();
            if (ans.equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    private static void scriptAddProduct(Scanner scan) {
        while (true) {
            System.out.println("\n------------ Add Product ------------");
            System.out.print("Name: ");
            String name = scan.next();

            System.out.print("Category: ");
            scan.nextLine();
            String category = scan.next();

            if (!isValidCategory(category)) {
                System.out.println("\nInvalid Category!");
                continue;
            }

            System.out.print("Stock: ");
            int stock = scan.nextInt();

            System.out.print("Price: ");
            double price = scan.nextDouble();

            System.out.println("\nWaiting...\n");
            Product.addProductToDataBase(name, category, stock, price);

            System.out.print("Want to add another?(y/N) ");
            String ans = scan.next();
            if (ans.equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    private static boolean isValidCategory(String test) {
        test = test.toUpperCase();
        for (Category c : Category.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

}
