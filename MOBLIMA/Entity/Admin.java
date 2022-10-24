package MOBLIMA.Entity;

import java.io.Serializable;

public class Admin implements Serializable {
    private String Username;
    private String Password;

    public Admin(String username, String password) {
        this.Username = username;
        this.Password = password;
    }

    public String getUsername() {
        return this.Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
