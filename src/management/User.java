package management;

import application.utills.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class User {

    private int idUser;
    private String name;
    private String mobile;
    private String email;
    private String password;
    private String addressCode;

    public User() {
    }

    public User(String name, String mobile, String email, String addressCode, String password) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.addressCode = addressCode;
        this.password = password;
        this.idUser = getIdOfUserFromDataBase(name,email,password);
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    protected abstract boolean checkValidUser();
    protected abstract int getIdOfUserFromDataBase(String name, String email, String password);

}
