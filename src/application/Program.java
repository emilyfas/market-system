package application;

import management.Admin;
import management.enums.Role;

public class Program {
    public static void main(String[] args) {
        System.out.println("Welcome to Elmore Market!");
        Admin admin = new Admin("Emilly Fernandes", "31 98706-3759", "emilly@gmail.com",
                123456, Role.MANAGER, 1300);

        System.out.println(admin);
    }
}
