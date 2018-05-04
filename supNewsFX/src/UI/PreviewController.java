package UI;

import Core.vo.Article;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;


public class PreviewController {

    @FXML
    TextArea preview_content;
    @FXML
    TextField preview_title;
    @FXML
    ImageView image_box;

    public void initialize(){
        preview_title.setEditable(false);
        preview_content.setEditable(false);
    }

    public void initData(Article article){
        Image img = new Image(new ByteArrayInputStream(article.getImage()));
        preview_content.setText(article.getContent());
        preview_title.setText(article.getTitle());
        image_box.setImage(img);


    }

}
