package UI;

import Core.dao.JDBCArticleDao;
import Core.dao.JDBCCredentialDao;
import Core.vo.Article;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("login");
        primaryStage.setScene(new Scene(root, 300, 150));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

//    public static byte[] extractBytes (String ImageName) throws IOException {
//          Path path = Paths.get(ImageName);
//          byte[] data = Files.readAllBytes(path);
//          return data;
//    }
//

    public static void main(String[] args) {


//        Article article = new Article();
//        article.setTitle("new test image");
//        article.setContent("i like turtles");
//        try{
//            article.setImage(extractBytes("C:\\Users\\USER\\Pictures\\tomami\\acl1.PNG"));
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//        JDBCArticleDao jdbcArticleDao = new JDBCArticleDao();
//        jdbcArticleDao.getConnection();
//        jdbcArticleDao.insert(article);
//        System.out.println(jdbcArticleDao.select());
//        jdbcArticleDao.closeConnection();
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



