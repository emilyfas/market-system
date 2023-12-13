package application;

import storage.Product;

import java.sql.*;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws SQLException {

        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Elmore Market!");

        UI.optionsMenu();

    }
}
