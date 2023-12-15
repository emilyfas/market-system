package management;

import application.utills.ConnectionDB;
import management.enums.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Staff extends User{

    private Role role;
    private double salary;
    private boolean loggedIn = false;

    public Staff(String name, String mobile, String email, String addressCode, String password, Role role, double salary) {
        super(name, mobile, email, addressCode, password);
        this.role = role;
        this.salary = salary;
    }

    public Staff(String name, String email, String password) {
        super(name, email, password);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void login() {
        if (!checkValidUser()) {
            return;
        }
        setLoggedIn(true);
        System.out.println("User Logged! Welcome " + this.getName());
    }

    public static void addToStaffToDatabase(Staff staff) {
        Connection cnn = ConnectionDB.getConnection();
        String sql = "INSERT INTO admin (name, mobile, email, adressCode, role, salary, password) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement smt = cnn.prepareStatement(sql)) {
            smt.setString(1, staff.getName());
            smt.setString(2, staff.getMobile());
            smt.setString(3, staff.getEmail());
            smt.setString(4, staff.getAddressCode());
            smt.setString(5, staff.getRole().toString());
            smt.setDouble(6, staff.getSalary());
            smt.setString(7, staff.getPassword());
            smt.executeUpdate();

            smt.close();
            cnn.close();
            System.out.println("Staff member added!");
        } catch (Exception e) {
            System.out.println("Error! Failed to add staff member.");
        }
    }

    @Override
    protected boolean checkValidUser() {
        boolean ans = false;
        Connection cnn = ConnectionDB.getConnection();
        String sql = "SELECT * FROM admin WHERE email = ? AND password = ?";
        try (PreparedStatement smt = cnn.prepareStatement(sql)) {
            smt.setString(1, this.getEmail());
            smt.setString(2, this.getPassword());

            ResultSet resultSet = smt.executeQuery();
            while (resultSet.next()) {
                this.setName(resultSet.getString("name"));
                this.setMobile(resultSet.getString("mobile"));
                this.setAddressCode("adressCode");
                this.setRole(Role.valueOf(resultSet.getString("role")));
                this.setSalary(resultSet.getDouble("salary"));
                ans = true;
            }

            smt.close();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Error! Invalid user.");
        }
        return ans;
    }
}
