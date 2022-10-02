package application;

public class ClientInfo {
    private String mail;
    private String password;

    public ClientInfo(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
