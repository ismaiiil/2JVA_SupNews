package Core.dao;
import java.util.List;
import Core.vo.Article;

public interface ArticleDao {

    public void insert(Article article);

    public void update(Article article);

    public void delete(int id);

    public List<Article> select();
}
