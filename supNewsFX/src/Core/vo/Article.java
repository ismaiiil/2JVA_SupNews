package Core.vo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Article {

    int id;
    String title;
    String content;
    byte[] image;

    public InputStream getImage() {
        InputStream is = new ByteArrayInputStream(image);
        return is;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
