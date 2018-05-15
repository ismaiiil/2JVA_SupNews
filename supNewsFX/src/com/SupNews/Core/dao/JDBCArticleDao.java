package com.SupNews.Core.dao;


import com.SupNews.Core.vo.Article;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JDBCArticleDao implements ArticleDao {

    Connection connection = null;

    /**
     * creates a connection with database using a {@link JDBCArticleDao} instance
     * @return returns a connection with the supnews database
     */
    public Connection getConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            if(connection == null){
                connection = DriverManager.getConnection("jdbc:mysql://localhost/SupNews?user=root&password=root");

            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;

    }

    /**
     * inserts a record in the Article database
     * @param article takes as paramenter an {@link Article} object
     */
    @Override
    public void insert(Article article){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO SupNews.articles (id, title, content,image,user_id) VALUES (NULL, ?, ?,?,?)"
            );
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setBytes(3,article.getImage());
            preparedStatement.setInt(4,article.getUser_id());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    /**
     * select all from database
     * @return returns a List of {@link Article} objects from the database
     */
    @Override
    public List<Article> select(){
        List<Article> articles = new LinkedList<Article>();

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM SupNews.articles");
            Article article = null;

            while(resultSet.next()){
                article = new Article();
                article.setId(Integer.parseInt(resultSet.getString("id")));
                article.setUser_id(Integer.parseInt(resultSet.getString("user_id")));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setImage(resultSet.getBytes("image"));
                articles.add(article);
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return articles;
    }

    /**
     * updates a specific row in databased by ID
     * @param article takes an {@link Article} object with a specific id that one wants to change
     */
    @Override
    public void update(Article article){
        try {
        PreparedStatement ps = connection.prepareStatement("UPDATE Supnews.articles SET title=?,content=?,image=?,user_id=? WHERE id=?");

        ps.setString(1,article.getTitle());
        ps.setString(2,article.getContent());
        ps.setBytes(3,article.getImage());
        ps.setInt(4,article.getUser_id());
        ps.setInt(5,article.getId());
        int rowsAffected = ps.executeUpdate();
        ps.close();
        System.out.println(rowsAffected + " Rows affected.");
        System.out.println("article with id " + article.getId());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * delete row by id
     * @param id id of Article in database
     */
    @Override
    public void delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM supnews.articles WHERE id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
            System.out.println("article with id: " + id + " was sucesfully deleted from DB.");

        }catch (SQLException e){
            e.printStackTrace();

        }
    }

    /**
     * stops the connection of a {@link JDBCArticleDao} context to the database
     */
    public void closeConnection(){
        try{
            if(connection != null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



//establish foreign key for credential and aritcle, user_id, id
