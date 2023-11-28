package storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Catalog {

    private ArrayList<Product> products;

    public Catalog() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Connection connection, String name, String category, int stock, double price) throws SQLException {

        if (checkProductExists(name)) {
            System.out.println("Product already exists in catalog.");
            return;
        }

        Statement statement = connection.createStatement();

        statement.executeUpdate("INSERT INTO PRODUCTS(NAME, CATEGORY, STOCK, PRICE) " +
                "VALUES ('" + name + "', '" + category + "', " +
                stock + ", " + price + ")");

        ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTS WHERE NAME = '" + name + "'");

        String id = "";
        while (resultSet.next()) {
            id = String.valueOf(resultSet.getInt("id"));
        }
        products.add(new Product(id, name, category, stock, price));
    }

    public void addProduct(Product product) {
        if (checkProductExists(product.getName())) {
            System.out.println("Product already exists in catalog.");
            return;
        }
        products.add(product);
    }

    public void addProduct(Connection connection, Scanner scan) throws SQLException {
        System.out.print("Name: ");
        String name = scan.next();
        System.out.print("Category: ");
        String category = scan.next();
        System.out.print("Stock: ");
        int stock = scan.nextInt();
        System.out.print("Price: ");
        double price = scan.nextInt();
        addProduct(connection, name, category, stock, price);
    }

    private boolean checkProductExists(String name) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public String printCatalog() {
        StringBuilder catalog = new StringBuilder("--------- Catalog ---------\n");

        if (products.isEmpty()) {
            catalog.append("No products in catalog...");
            return catalog.toString();
        }

        for (int i = 0; i < products.size(); i++) {
            catalog.append(products.get(i).getName() + "\t\t" +
                            products.get(i).getStock() + "\t\t" +
                            "R$" + products.get(i).getPrice() + "\t\n");
        }
        return catalog.toString();
    }
}
