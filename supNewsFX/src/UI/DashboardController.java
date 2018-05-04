package UI;

import Core.dao.JDBCArticleDao;
import Core.vo.Article;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class DashboardController {

    @FXML ListView listOfArticles;
    @FXML TextField title_box;
    @FXML TextArea content_box;

    public void initialize(){
        updateListView();
    }


    public void save_btn_pressed(ActionEvent Event){
        String str =(String) listOfArticles.getSelectionModel().getSelectedItem();
        System.out.println(str);
        if(str == null){
            Article article = new Article();
            article.setContent(content_box.getText());
            article.setTitle(title_box.getText());

            JDBCArticleDao jdbcArticleDao = new JDBCArticleDao();
            jdbcArticleDao.getConnection();
            jdbcArticleDao.insert(article);
            jdbcArticleDao.closeConnection();
            updateListView();
            content_box.clear();
            title_box.clear();
        }else{
            JDBCArticleDao jdbcArticleDao = new JDBCArticleDao();
            jdbcArticleDao.getConnection();
            Article article1 = new Article();
            for (Article artc :jdbcArticleDao.select()) {
                if(artc.getTitle().equals(str)){
                    article1.setId(artc.getId());
                    break;
                }

            }
            article1.setTitle(title_box.getText());
            article1.setContent(content_box.getText());
            jdbcArticleDao.update(article1);
            jdbcArticleDao.closeConnection();
            updateListView();
            content_box.clear();
            title_box.clear();

        }


    }

    public void click_listview(){
        String str =(String) listOfArticles.getSelectionModel().getSelectedItem();
        System.out.println(str);
        JDBCArticleDao jdbcArticleDao = new JDBCArticleDao();
        jdbcArticleDao.getConnection();
        for (Article artc :jdbcArticleDao.select()) {
            if(artc.getTitle().equals(str)){
                title_box.setText(artc.getTitle());
                content_box.setText(artc.getContent());
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
            listOfArticles.getItems().add(artic.getTitle());
        }
        jdbcArticleDao.closeConnection();
    }

}


//on startup update listview
//add new article
    //register user input in title box and content
//dsiable save button if list view not selected
// if user clicks on save, article is pushed on server, and list view is updated
