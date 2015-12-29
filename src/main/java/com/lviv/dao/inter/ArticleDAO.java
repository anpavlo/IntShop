package com.lviv.dao.inter;

import java.util.List;

import com.lviv.model.Article;
import com.lviv.model.ItemTree;



public interface ArticleDAO {
	
	public void saveArticle(Article article);
	public List<Article> getAllArticles();
	public Article getArticleById(Integer id);
	public void updateArticle(Article article);
	public void deleteArticle(Article article);
	public List<Article> getListByCategorys(List<ItemTree> itemTreeList);

}
