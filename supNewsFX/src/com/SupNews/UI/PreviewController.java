package com.SupNews.UI;

import com.SupNews.Core.vo.Article;
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

    /**
     * initializing the tags that are to be used
     */
    private String b_o = "{b}", i_o = "{i}", u_o = "{u}", b_c = "{/b}", i_c = "{/i}", u_c = "{/u}";

    /**
     * disable editing in the preview window
     */
    public void initialize(){
        preview_title.setEditable(false);
        preview_content.setEditable(false);
    }

    /**
     * initialize content of the preview window, based on the contents of the Dashboard, called by {@link DashboardController#preview_btn_click(ActionEvent)}
     * using {@link PreviewController#getFormattedTexts()} the content of the content box is split and formatted into multiple
     * Text objects and stored in a List, all elements of the list is then added to the {@link PreviewController#textflow_box}
     * @param article {@link Article} object
     */
    public void initData(Article article){
        Image img = new Image(new ByteArrayInputStream(article.getImage()));
        preview_content.setText(article.getContent());
        preview_title.setText(article.getTitle());
        image_box.setImage(img);


        List<Text> textslist1 = getFormattedTexts();
        textflow_box.getChildren().addAll(textslist1);
    }

    /**
     * the content(string) is split into an array of strings based on hte closing tags then split again based on the
     * opening tags using regex's
     * @return return a list of formatted texts
     */
    private List<Text> getFormattedTexts() {
        List<Text> textslist1 =new ArrayList<Text>();
        String[] parts = preview_content.getText().split("\\"+ b_c +"|\\"+ i_c +"|\\"+ u_c);
        for (String str : parts){
            //System.out.println(str);
            if(str.contains(b_o)){
                split_format(textslist1, str, "\\" + b_o, "-fx-font-weight: bold");
            }
            else if(str.contains(i_o)){
                split_format(textslist1, str, "\\"+i_o, "-fx-font-style: italic ");
            }
            else if(str.contains(u_o)){
                split_format(textslist1, str, "\\"+u_o, "-fx-underline: true ");
            }
            else {
                Text text2 = new Text(str);
                textslist1.add(text2);
            }
        }
        return textslist1;
    }


    /**
     * this is used to format an split string that has been previously been split based on closing tags
     * @param textslist list of text that the formatted texts will be added to
     * @param to_split string to be split again and formatted
     * @param delimiter opening tags that can be used to split the string
     * @param css_format format of the Text object
     */
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

    /**
     * this is used to combine multiple formats on a text, since {@link Text#setStyle(String)} overrides previous styles
     * @param text2 text
     * @param added_style style to combine
     */
    private void combine_style(Text text2, String added_style) {
        String initstyle = text2.getStyle();
        text2.setStyle(added_style + initstyle);
    }

}
