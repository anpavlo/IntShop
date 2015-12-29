package com.lviv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lviv.dao.inter.ArticleDAO;
import com.lviv.model.Article;
import com.lviv.model.ItemTree;

@Service
public class ArticleService {

	@Autowired
	ArticleDAO articleDAO;

	public List<Article> getAllArticles() {
		return articleDAO.getAllArticles();
	}

	public Article getArticleByID(Integer id) {
		return articleDAO.getArticleById(id);
	}

	public void save(Article article) {
		articleDAO.saveArticle(article);
	}

	public void update(Article article) {
		articleDAO.updateArticle(article);
	}
	
	public void delete(Article article) {
		articleDAO.deleteArticle(article);
	}
	
	public List<Article> getListByCategoryId(List<ItemTree> itemTreeList){		
		
		return articleDAO.getListByCategorys(itemTreeList);
	}

}
