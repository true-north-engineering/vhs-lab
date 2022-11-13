package com.truenorth.vhslab.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull (message = "User id cannot be null")
    private Long userId;

    @Column(name = "USERNAME")
    @NotNull(message = "username should not be null")
    private String username;

    @Column(name = "PASSWORD")
    @NotNull(message = "password should not be null")
    private String password;

    public User() {
    }

    public User(Long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public boolean equals(Object obj) {

        if(this == obj)
            return true;

        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        User user = (User) obj;

        return (user.username.equals(this.username)  && user.userId == this.userId && user.password.equals(this.password));
    }

    @Override
    public int hashCode() {

        int result = 17;
        result = 31 * result + userId.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User id: " + userId + " Username: " + username + " Password: " + password;
    }
}
