package com.lviv.dao.inter;

import java.util.List;

import com.lviv.model.ArticleParam;

public interface ArticleParamDAO {
	
	public void save(ArticleParam articleParam);
	public List<ArticleParam> getAll();
	public ArticleParam getById(Integer id);
	public void update(ArticleParam articleParam);
	public void delete(ArticleParam articleParam);

}
