package application;

public abstract class UI {
    public static void printOptionsMenu() {
        System.out.println("------------ Operations ------------");
        System.out.println("1. Add Products");
        System.out.println("2. Remove Products");
        System.out.println("3. Print Catalog");
        System.out.println("0. Exit Menu Operations");
        System.out.println("Insert an option: ");
    }
}
