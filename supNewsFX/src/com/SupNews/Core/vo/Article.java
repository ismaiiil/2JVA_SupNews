package com.SupNews.Core.vo;

public class Article {
    /**
     * id of Article
     */
    private int id;
    /**
     * User id associated with an Article
     */
    private int user_id;
    /**
     * Title of an Article
     */
    private String title;
    /**
     * Content of an Article
     */
    private String content;
    /**
     * image of an Article stored as a byte array
     */
    private byte[] image;


    /**
     * getter of user_id
     * @return returns the user_id associated to an Article
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * sets the user_id associated to an Article
     * @param user_id user id
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * gets the Image of an Article
     * @return image of Article as byte array
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * sets the Image of an Article
     * @param image image as byte array
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     *  gets id of an Article
     * @return id as integer
     */
    public int getId() {
        return id;
    }

    /**
     * sets id of an Article
     * @param id id as integer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets the title of an Article
     * @return title of an Article as string
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets title of an Article
     * @param title sets title of an Article
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets content of an Article
     * @return content of Article as string
     */
    public String getContent() {
        return content;
    }

    /**
     * sets content of an Article
     * @param content content as string
     */
    public void setContent(String content) {
        this.content = content;
    }


}
