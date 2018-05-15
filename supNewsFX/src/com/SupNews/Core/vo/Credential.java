package com.SupNews.Core.vo;

public class Credential {
    /**
     * user id of a user
     */
    private int id;
    /**
     * username of a user
     */
    private String username;
    /**
     * password of user
     */
    private String password;

    /**
     * password getter
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * password setter
     * @param password password as string
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * user id getter
     * @return user id
     */
    public int getId() {
        return id;
    }

    /**
     * id setter
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * username getter
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * username setter
     * @param username username setter
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
