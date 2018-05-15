package com.SupNews.Core.dao;
import java.util.List;
import com.SupNews.Core.vo.Article;

/**
 * interface Article Dao
 */
public interface ArticleDao {

    public void insert(Article article);

    public void update(Article article);

    public void delete(int id);

    public List<Article> select();
}
