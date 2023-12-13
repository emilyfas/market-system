package application;

import storage.Category;
import storage.Product;

import javax.crypto.spec.PSource;
import java.util.Scanner;

public abstract class UI {
    public static void optionsMenu() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("------------ Menu Operations ------------");
            System.out.println("1. Products Operations");
            System.out.println("2. Accounts Operations");
            System.out.println("3. Purchase");
            System.out.println("0. Exit Program");
            System.out.println("Insert an option: ");
            int ans = scan.nextInt();
            if (ans == 1) {
                productOperations(scan);
            }else{
                System.out.println("END");
                break;
            }
        }

        scan.close();

    }

    public static void productOperations(Scanner scan){
        while (true) {

            System.out.println("------------ Product Operations ------------");
            System.out.println("1. Add Products");
            System.out.println("2. Remove Products");
            System.out.println("3. Print Catalog");
            System.out.println("0. Exit to 'Menu Operations'");
            System.out.println("Insert an option: ");

            int ans = scan.nextInt();
            if (ans == 1) {
                scriptAddProduct(scan);
            } else if (ans == 2) {
                // remove
            } else if (ans == 3) {
                // print
            } else if (ans == 0) {
                break;
            } else{
                System.out.println("Invalid Input!");
            }
        }
    }

    public static void scriptAddProduct(Scanner scan) {
        while (true) {
            System.out.println("------------ Add Product ------------");
            System.out.print("Name: ");
            String name = scan.next();
            //scan.next();
            System.out.println(name);

            System.out.print("Category: ");
            scan.nextLine();
            String category = scan.next();
            System.out.println(category);

            if (!isValidCategory(category)) {
                System.out.println("Invalid Category!");
                continue;
            }

            System.out.print("Stock: ");
            int stock = scan.nextInt();

            System.out.print("Price: ");
            double price = scan.nextDouble();

            System.out.println("\nWaiting...\n");
            Product.addProductToDataBase(name, category, stock, price);
        }
    }

    private static boolean isValidCategory(String test) {
        test = test.toUpperCase();
        for (Category c : Category.values()) {
            System.out.println(c.name());
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

}
