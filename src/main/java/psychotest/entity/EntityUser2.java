package psychotest.entity;

public class EntityUser2 {
    private Long id;
    private String username;
    private String password;
    private String passwordCONFIRM;

    public EntityUser2(Long id, String username, String password, String passwordCONFIRM) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.passwordCONFIRM = passwordCONFIRM;
    }

    public EntityUser2() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCONFIRM() {
        return passwordCONFIRM;
    }

    public void setPasswordCONFIRM(String passwordCONFIRM) {
        this.passwordCONFIRM = passwordCONFIRM;
    }
}
