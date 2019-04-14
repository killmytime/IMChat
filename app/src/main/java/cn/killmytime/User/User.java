package cn.killmytime.User;

/**
 * Created by leiwe on 2018/6/6.
 * Thank you for reading, everything gonna to be better.
 */
public class User {
    private String username;
    private String password;
    private String publicKey;
    private int status;

    public User(String username, String password, String publicKey,int status) {
        this.username = username;
        this.password = password;
        this.publicKey = publicKey;
        this.status=status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

