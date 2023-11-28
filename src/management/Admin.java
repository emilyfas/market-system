package management;

import management.enums.Role;

public class Admin extends User{

    private Role role;
    private double salary;
    private String user;
    private String password;

    public Admin(String name, String mobile, String email, long addressCode, Role role, double salary, String user, String password) {
        super(name, mobile, email, addressCode);
        this.role = role;
        this.salary = salary;
        this.user = user;
        this.password = password;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
