package com.android.excilys.chatwithmrroboto.entity;

/**
 * Created by excilys on 16/01/17.
 */

public class User {

    private Long id;
    private String username;
    private String password;

    private User(Builder builder)
    {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static final class Builder {
        private Long id;
        private String username;
        private String password;

        public Builder(String username)
        {
            this.username = username;
        }

        public Builder password(String password)
        {
            this.password = password;
            return this;
        }

        public Builder id(Long id)
        {
            this.id = id;
            return this;
        }

        public User build()
        {
            return new User(this);
        }

    }


}
