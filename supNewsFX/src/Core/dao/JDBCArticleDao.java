package Core.dao;


import Core.vo.Article;
import com.mysql.cj.x.protobuf.MysqlxCrud;

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
                    "INSERT INTO SupNews.articles (id, title, content,image) VALUES (NULL, ?, ?,?)"
            );
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setBytes(3,article.getImage());

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

    @Override
    public void update(Article article){
        try {
        PreparedStatement ps = connection.prepareStatement("UPDATE Supnews.articles SET title=?,content=?,image=? WHERE id=?");

        ps.setString(1,article.getTitle());
        ps.setString(2,article.getContent());
        ps.setBytes(3,article.getImage());
        ps.setInt(4,article.getId());
        int rowsAffected = ps.executeUpdate();
        ps.close();
        System.out.println(rowsAffected + " Rows affected.");
        System.out.println("User with id " + article.getId());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
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

