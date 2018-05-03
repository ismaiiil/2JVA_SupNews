package Core.dao;


import Core.vo.Article;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JDBCArticleDao implements ArticleDao {

    Connection connection = null;

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

    @Override
    public void insert(Article article){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO SupNews.articles (id, title, content) VALUES (NULL, ?, ?)"
            );
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setString(2, article.getContent());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

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
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                articles.add(article);
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return articles;
    }

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
