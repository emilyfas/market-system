package application;

import management.Admin;
import management.enums.Role;
import storage.Category;
import storage.Product;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class UI {

    public static void optionsMenu(Admin admin) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("\n------------ Menu Operations ------------");
            System.out.println("1. Products Operations");
            System.out.println("2. Purchase");
            System.out.println("3. User Accounts Operations");
            if (admin.getRole() == Role.MANAGER || admin.getRole() == Role.DEVELOPER) {
                System.out.println("4. Admin Accounts Operations");
            }
            System.out.println("0. Exit Program");
            System.out.print("Insert an option: ");
            int ans = scan.nextInt();
            if (ans == 1) {
                productOperations(scan);
            } else if (ans == 3) {
                userAccountsOperations(scan);
            } else if (ans == 4 && (admin.getRole() == Role.MANAGER || admin.getRole() == Role.DEVELOPER)){
                adminAccountsOperations(scan);
            }
            else{
                System.out.println("END");
                break;
            }
        }

        scan.close();
    }

    public static void adminAccountsOperations(Scanner scan) {

    }

    public static void userAccountsOperations(Scanner scan){
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

    public static void productOperations(Scanner scan){
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

    public static void printCatalog() {
        ArrayList<Product> list = Product.getAllProducts();
        System.out.format("%-5s%-15s%-17s%-9s%-10s%n", "Id", "Name", "Category", "Stock", "Price");


        for (int i = 0; i < list.size(); i++) {
            Product product = list.get(i);

            System.out.format("%-5s%-15s%-17s%-9s%-10s%n", product.getId(), product.getName(), product.getCategory(), product.getStock(), product.getPrice());
        }
        System.out.println();
    }

    public static void scriptUpdateProduct(Scanner scan) {
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

    public static void scriptRemoveProduct(Scanner scan) {
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

    public static void scriptAddProduct(Scanner scan) {
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
