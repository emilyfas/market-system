package storage;

import application.utills.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;

public class Product {

    private int id;
    private String name;
    private Category category;
    private int stock;
    private double price;

    public Product() {
    }

    public Product(String name, Category category, int stock, double price) {
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.id = getIdOfProduct(name, category, stock, price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public static void addProductToDataBase(String name, String category, int stock, double price) {
        Connection cnn = ConnectionDB.getConnection();
        String sql = "INSERT INTO products (name, category, stock, price) VALUES(?,?,?,?)";
        try (PreparedStatement smt = cnn.prepareStatement(sql)) {
            smt.setString(1, name);
            smt.setString(2, category);
            smt.setInt(3, stock);
            smt.setFloat(4, (float) price);
            smt.executeUpdate();

            smt.close();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Error! Failed to add product.");
        }
        System.out.println("Product added!");
    }

    public static void removeProductFromDataBase(int id) {
        Connection cnn = ConnectionDB.getConnection();
        String sql = "DELETE FROM products WHERE id_product = ?";
        try (PreparedStatement smt = cnn.prepareStatement(sql)) {
            smt.setInt(1, id);
            smt.executeUpdate();

            smt.close();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Error! Failed to remove product.");
        }
        System.out.println("Product removed!");
    }

    public static void updateProduct(int id, String name, String category, int stock, double price) {
        Connection cnn = ConnectionDB.getConnection();
        String sql = "UPDATE products SET name = ?, category = ?, stock = ?, price = ? WHERE id_product = ?";

        try (PreparedStatement smt = cnn.prepareStatement(sql)) {

            smt.setString(1, name);
            smt.setString(2, category);
            smt.setInt(3, stock);
            smt.setFloat(4, (float) price);
            smt.setInt(5, id);
            smt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error! Failed to update product.");
        }
    }

    public static int getIdOfProduct(String name, Category category, int stock, double price) {
        int idDB = 0;
        Connection cnn = ConnectionDB.getConnection();
        String sql = "SELECT id_product FROM products WHERE name = ? AND category = ? AND stock = ? AND price = ?";

        try (PreparedStatement smt = cnn.prepareStatement(sql)) {

            smt.setString(1, name);
            smt.setString(2, category.toString());
            smt.setInt(3, stock);
            smt.setFloat(4, (float) price);
            ResultSet resultSet = smt.executeQuery();
            while (resultSet.next()) {
                idDB = resultSet.getInt("id_product");
            }

            smt.close();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Error! Failed to get ID.");
        }
        return idDB;
    }

    public static Product getProductFromId(int id) {
        Product product = new Product();

        Connection cnn = ConnectionDB.getConnection();
        String sql = "SELECT * FROM products WHERE id_product = ?";

        try (PreparedStatement smt = cnn.prepareStatement(sql)) {

            smt.setInt(1, id);

            ResultSet resultSet = smt.executeQuery();
            while (resultSet.next()) {
                product.setName(resultSet.getString("name"));

                String categoryDb = resultSet.getString("category");
                product.setCategory(Category.valueOf(categoryDb.toUpperCase()));

                product.setPrice(resultSet.getFloat("price"));
                product.stock = resultSet.getInt("stock");
                product.id = id;
            }

            smt.close();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Error! Failed to get Product from id.");
        }
        return product;
    }

    public static ArrayList<Product> getAllProducts() {
        ArrayList<Product> list = new ArrayList<>();

        Connection cnn = ConnectionDB.getConnection();
        String sql = "SELECT * FROM products";

        try (PreparedStatement smt = cnn.prepareStatement(sql)) {

            ResultSet resultSet = smt.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();

                product.setName(resultSet.getString("name"));

                String categoryDb = resultSet.getString("category");
                product.setCategory(Category.valueOf(categoryDb.toUpperCase()));

                product.setPrice(resultSet.getFloat("price"));
                product.stock = resultSet.getInt("stock");
                product.id = resultSet.getInt("id_product");

                list.add(product);
            }

            smt.close();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Error! Failed to get all Products.");
        }
        return list;
    }

    public static void addToStock(int n, Product p) {
        Connection cnn = ConnectionDB.getConnection();
        String sql = "UPDATE products SET stock = stock + ? WHERE id_product = ?";
        try (PreparedStatement smt = cnn.prepareStatement(sql)) {
            smt.setInt(1, n);
            smt.setInt(2, p.id);
            smt.executeUpdate();

            smt.close();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Error! Failed to add product.");
        }
        p.stock += n;
        System.out.println("Stock now: " + p.stock);
    }

    public static void removeFromStock(int n, Product p)  {
        if (p.stock - n < 0) {
            System.out.println("Unable to remove from stock! Stock now: " + p.stock);
            return;
        }

        Connection cnn = ConnectionDB.getConnection();

        String sql = "UPDATE products SET stock = stock - ? WHERE id_product = ?";
        try (PreparedStatement smt = cnn.prepareStatement(sql)) {
            Statement statement = cnn.createStatement();

            smt.setInt(1, n);
            smt.setInt(2, p.id);
            smt.executeUpdate();

            smt.close();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Error! Failed to add product.");
        }
        p.stock -= n;
        System.out.println("Stock now: " + p.stock);
    }
}
