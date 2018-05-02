package UI;

import Core.dao.JDBCArticleDao;
import Core.vo.Article;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 150));
        primaryStage.show();
    }


    public static void main(String[] args) {


        Article article = new Article();
        article.setTitle("Turtle");
        article.setContent("i like turtles");

        JDBCArticleDao jdbcArticleDao = new JDBCArticleDao();
        jdbcArticleDao.getConnection();
        jdbcArticleDao.insert(article);
        System.out.println(jdbcArticleDao.select());
        jdbcArticleDao.closeConnection();

        launch(args);
    }


}

//table_1/ credentials(id, username, password)
//class JDBCCredentialDao (package dao)
//interface CredentialDao (package dao)
    //select
//class Credential (package vo)
//table_2/ articles(id, title, content, image)
//class JDBCArticleDao (package dao)
//interface ArticleDao (package dao)
    //insert, update, select, delete
//class Article (package vo)
