package UI;

import Core.vo.Article;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PreviewController {

    @FXML
    TextArea preview_content;
    @FXML
    TextField preview_title;
    @FXML
    ImageView image_box;
    @FXML
    TextFlow textflow_box;

    String b_o = "{b}";
    String i_o = "{i}";
    String u_o = "{u}";
    String b_c = "{/b}";
    String i_c = "{/i}";
    String u_c = "{/u}";

    public void initialize(){
        preview_title.setEditable(false);
        preview_content.setEditable(false);
    }

    public void initData(Article article){
        Image img = new Image(new ByteArrayInputStream(article.getImage()));
        preview_content.setText(article.getContent());
        preview_title.setText(article.getTitle());
        image_box.setImage(img);


        String[] parts = preview_content.getText().split("\\"+ b_c +"|\\"+ i_c +"|\\"+ u_c);

        List<Text> textslist =new ArrayList<Text>();

        for (String str :parts){
            //System.out.println(str);
            if(str.contains(b_o)){
                split_format(textslist, str, "\\" + b_o, "-fx-font-weight: bold");
            }
            else if(str.contains(i_o)){
                split_format(textslist, str, "\\"+i_o, "-fx-font-style: italic ");
            }
            else if(str.contains(u_o)){
                split_format(textslist, str, "\\"+u_o, "-fx-underline: true ");
            }
            else {
                Text text2 = new Text(str);
                textslist.add(text2);
            }
        }
        textflow_box.getChildren().addAll(textslist);
    }

    private void split_format(List<Text> textslist, String to_split, String delimiter, String css_format) {
        String[] two = to_split.split(delimiter);
        System.out.println(Arrays.toString(two));
        Text text2 = new Text(two[1]);
        text2.setStyle(css_format);
        if(two[0].contains(b_o)){
            combine_style(text2, "-fx-font-weight: bold;");
        }
        if(two[0].contains(u_o)){
            combine_style(text2, "-fx-underline: true;");
        }
        if(two[0].contains(i_o)){
            combine_style(text2, "-fx-font-style: italic;");
        }
        String removetags = two[0].replaceAll("\\"+ b_o +"|\\"+ i_o +"|\\"+ u_o,"");
        Text text1 = new Text(removetags);
        textslist.add(text1);
        textslist.add(text2);
    }

    private void combine_style(Text text2, String added_style) {
        String initstyle = text2.getStyle();
        text2.setStyle(added_style + initstyle);
    }

}
