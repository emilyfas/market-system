package management;

import application.utills.ConnectionDB;
import management.enums.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Admin extends User{

    private Role role;
    private double salary;

    public Admin(String name, String mobile, String email, String addressCode, Role role, double salary) {
        super(name, mobile, email, addressCode);
        this.role = role;
        this.salary = salary;
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

    public void SignIn(Admin admin) {
        Connection cnn = ConnectionDB.getConnection();
        String sql = "INSERT INTO admin (name, mobile, email, adressCode, role, salary) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement smt = cnn.prepareStatement(sql)) {
            smt.setString(1, admin.getName());
            smt.setString(2, admin.getMobile());
            smt.setString(3, admin.getEmail());
            smt.setString(4, admin.getAddressCode());
            smt.setString(5, admin.getRole().toString());
            smt.setDouble(6, admin.getSalary());
            smt.executeUpdate();

            smt.close();
            cnn.close();
        } catch (Exception e) {
            System.out.println("Error! Failed to sign in user.");
        }
    }

    @Override
    protected boolean Login() {
        return false;
    }

    @Override
    public String toString() {
        return "Admin{ " +
                super.toString() +
                "role=" + role +
                ", salary=" + salary +
                " }";
    }
}
