package management;

import management.enums.Role;

public class Admin extends User{

    private Role role;
    private double salary;

    public Admin(String name, String mobile, String email, long addressCode, Role role, double salary) {
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
