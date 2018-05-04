package UI;

import Core.vo.Article;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class PreviewController {

    @FXML
    TextArea preview_content;
    @FXML
    TextField preview_title;

    public void initialize(){
        preview_title.setEditable(false);
        preview_content.setEditable(false);
    }

    public void initData(Article article){

        preview_content.setText(article.getContent());
        preview_title.setText(article.getTitle());


    }

}
