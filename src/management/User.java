package management;

import application.utills.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class User {
    private String name;
    private String mobile;
    private String email;
    private String password;
    private String addressCode;

    public User(String name, String mobile, String email, String addressCode, String password) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.addressCode = addressCode;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    protected abstract boolean checkValidUser();

}
