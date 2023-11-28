package management;

public abstract class User {
    private String name;
    private String mobile;
    private String email;
    private long addressCode;

    public User(String name, String mobile, String email, long addressCode) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.addressCode = addressCode;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(long addressCode) {
        this.addressCode = addressCode;
    }

    protected abstract boolean Login();

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", addressCode=" + addressCode;
    }
}
