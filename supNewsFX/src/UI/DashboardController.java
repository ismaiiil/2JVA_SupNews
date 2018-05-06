package UI;

import Core.dao.JDBCArticleDao;
import Core.vo.Article;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DashboardController {

    @FXML ListView listOfArticles;
    @FXML TextField title_box;
    @FXML TextArea content_box;

    byte[] imagebyte_box; //u cant see dis one hey hey
    int u_id; //user id of user that was logged in

    //init user id of user that has logged in
    public void init_u_id(int id){
        u_id = id;
    }

    public void initialize(){
        updateListView();
    }
    public void add_image_btn_click(ActionEvent Event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("image", "*.png","*.bmp","*jpeg","*jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Path path = Paths.get(selectedFile.getPath());
            imagebyte_box = Files.readAllBytes(path);
            System.out.println(imagebyte_box);
        }
        else {
            System.out.println("File selection cancelled.");
        }


    }

    public  void delete_btn_click(ActionEvent Event){
        String str =(String) listOfArticles.getSelectionModel().getSelectedItem();
        System.out.println(str);
        JDBCArticleDao jdbcArticleDao = new JDBCArticleDao();
        jdbcArticleDao.getConnection();
        if(str != null){
            for (Article delart:jdbcArticleDao.select()) {
                if(delart.getTitle().equals(str) && delart.getUser_id() == u_id){
                    jdbcArticleDao.delete(delart.getId());
                    updateListView();
                    break;
                }

            }
        }


    }

    public void preview_btn_click(ActionEvent Event){
        try {
            Article article = new Article();
            article.setTitle(title_box.getText());
            article.setContent(content_box.getText());
            article.setImage(imagebyte_box);
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("preview.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            PreviewController previewController = fxmlloader.getController();
            previewController.initData(article);
            Stage stage = new Stage();
            stage.setTitle("preview");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    public void save_btn_pressed(ActionEvent Event){
        String str =(String) listOfArticles.getSelectionModel().getSelectedItem();
        System.out.println(str);

        if (!title_box.getText().replaceAll("\\s+","").equals("")){
            if(str == null){
                Article article = new Article();
                article.setUser_id(u_id);
                article.setContent(content_box.getText());
                article.setTitle(title_box.getText());
                article.setImage(imagebyte_box);

                JDBCArticleDao jdbcArticleDao = new JDBCArticleDao();
                jdbcArticleDao.getConnection();
                jdbcArticleDao.insert(article);
                jdbcArticleDao.closeConnection();
                updateListView();

            }else{
                JDBCArticleDao jdbcArticleDao = new JDBCArticleDao();
                jdbcArticleDao.getConnection();
                Article article1 = new Article();
                for (Article artc :jdbcArticleDao.select()) {
                    if(artc.getTitle().equals(str) && artc.getUser_id() == u_id){
                        article1.setId(artc.getId());
                        break;
                    }

                }
                article1.setUser_id(u_id);
                article1.setTitle(title_box.getText());
                article1.setContent(content_box.getText());
                article1.setImage(imagebyte_box);
                jdbcArticleDao.update(article1);
                jdbcArticleDao.closeConnection();
                updateListView();

            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("You can't leave the title empty");
            alert.showAndWait();
        }


    }

    public void click_listview(){
        String str =(String) listOfArticles.getSelectionModel().getSelectedItem();
        System.out.println(str);
        JDBCArticleDao jdbcArticleDao = new JDBCArticleDao();
        jdbcArticleDao.getConnection();
        for (Article artc :jdbcArticleDao.select()) {
            if(artc.getTitle().equals(str) && artc.getUser_id()== u_id){
                title_box.setText(artc.getTitle());
                content_box.setText(artc.getContent());
                imagebyte_box = artc.getImage();
                break;
            }

        }
        jdbcArticleDao.closeConnection();
    }

    private void updateListView(){
        listOfArticles.getItems().clear();
        JDBCArticleDao jdbcArticleDao = new JDBCArticleDao();
        jdbcArticleDao.getConnection();

        for(Article artic:jdbcArticleDao.select()) {
            if(artic.getUser_id() == u_id){
                listOfArticles.getItems().add(artic.getTitle());
            }
        }
        jdbcArticleDao.closeConnection();
        content_box.clear();
        title_box.clear();
        System.out.println(imagebyte_box);
        imagebyte_box = new byte[0];
    }

    public void chg_usr_btn_click(ActionEvent Event){
        try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root1 = (Parent) fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("login");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
            Stage stage1 = (Stage) title_box.getScene().getWindow();
            stage1.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}


//on startup update listview
//add new article
    //register user input in title box and content
//dsiable save button if list view not selected
// if user clicks on save, article is pushed on server, and list view is updated
