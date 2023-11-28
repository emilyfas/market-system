package application;

import management.Admin;
import management.enums.Role;
import storage.Catalog;
import storage.Product;

import java.sql.*;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Elmore Market!");

        Admin admin = new Admin("Emilly Fernandes", "31 98706-3759", "emilly@gmail.com",
                123456, Role.MANAGER, 1300, "root", "123456");

        Catalog catalog = new Catalog();
        Connection cnn = null;


        try {
            // Establishing connection with database
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/market_system",
                    admin.getUser(),
                    admin.getPassword());

            ResultSet resultSet = getProductsFromDatabase(cnn);

            // Making and adding products to catalog
            while (resultSet.next()) {
                catalog.addProduct(makeProductFromSQL(resultSet));
            }

            System.out.println(catalog.printCatalog());

            do {
                UI.printOptionsMenu();
                int option = scan.nextInt();

                if (option == 0) {
                    break;
                } else if (option == 1) {
                    // Add product
                    catalog.addProduct(cnn, scan);
                } else if (option == 2) {
                    // Remove product
                } else if (option == 3) {
                    System.out.println(catalog.printCatalog());
                }

            } while (true);







        } catch (SQLException sqlException) {
            System.out.println("Failed to connect to database.");
        }
    }

    // Returns data from table PRODUCTS
    private static ResultSet getProductsFromDatabase(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM PRODUCTS");
    }

    // Returns a product made with the data received from the database
    private static Product makeProductFromSQL(ResultSet rs) throws SQLException {
        return new Product(rs.getString("id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getInt("stock"),
                rs.getDouble("price"));
    }
}
