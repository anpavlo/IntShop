package com.lviv.controller;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lviv.model.Article;
import com.lviv.model.ArticleParam;
import com.lviv.model.ItemTree;
import com.lviv.model.Param;
import com.lviv.model.ParamValue;
import com.lviv.service.ArticleParamService;
import com.lviv.service.ArticleService;
import com.lviv.service.ItemTreeService;
import com.lviv.service.ParamService;
import com.lviv.service.ParamValueService;
import com.lviv.utility.ArticleData;
import com.lviv.utility.ParamsData;

@Controller
public class ArticleController {

	@Autowired
	ArticleService articleService;
	@Autowired
	ItemTreeService itemTreeService;
	@Autowired
	ParamService paramService;
	@Autowired
	ArticleParamService articleParamService;
	@Autowired
	ParamValueService paramValueService;
	
	List<Article> listArticle = new LinkedList<Article>();

	/*@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showTree() {

		ModelAndView mv = new ModelAndView("prod_drp_dwn");
		ItemTree itemTree = itemTreeService.getById(itemTreeService.getRootId());
		mv.addObject("itemTree", itemTree);
		return mv;
	}*/
	
	@RequestMapping(value = "/showallarticle", method = RequestMethod.GET)
	public ModelAndView showAllArticle() {
		ModelAndView mv = new ModelAndView("products");
		listArticle = articleService.getAllArticles();
		ItemTree itemTreeMenu = itemTreeService.getById(itemTreeService.getRootId());
		mv.addObject("itemTree", itemTreeMenu);
		mv.addObject("listArticle", listArticle);
		return mv;
	}
	
	@RequestMapping(value = "/productslist", method = RequestMethod.GET)
	public ModelAndView productslist(@RequestParam("id") Integer id) {
		List<ItemTree> itemTreeList = itemTreeService.getAllChildrenCategoriesByParentsId(id);
		ModelAndView mv = new ModelAndView("products");
		
		listArticle = articleService.getListByCategoryId(itemTreeList);
		
		ItemTree itemTreeMenu = itemTreeService.getById(itemTreeService.getRootId());
		mv.addObject("itemTree", itemTreeMenu);
		mv.addObject("listArticle", listArticle);
		return mv;
	}

	@RequestMapping(value = "/articlepage", method = RequestMethod.GET)
	public ModelAndView showArtiklePage(@RequestParam("id") Integer id) {
		ModelAndView mv = new ModelAndView("product");
		Article article = articleService.getArticleByID(id);
		ItemTree itemTreeMenu = itemTreeService.getById(itemTreeService.getRootId());
		mv.addObject("itemTree", itemTreeMenu);
		mv.addObject("article", article);
		return mv;
	}
	

	@RequestMapping(value = "/getCategorys", method = RequestMethod.GET)
	public @ResponseBody List<ItemTree> getCategorys() {
		
		return itemTreeService.getAllCategoriesOfRootParent();
	}

	
	@RequestMapping(value="/addArticle", method = RequestMethod.POST)
	public @ResponseBody Boolean addArticle(@RequestBody ArticleData articleData) {
		
		System.out.print("addArticle  ");
		System.out.println(articleData.getPrice());
		System.out.println(articleData.getQuantity());
		System.out.println(articleData.getParamsDataList().get(0).getParamName());
		
		
		Article article = new Article();
		article.setArticlePrice(articleData.getPrice());
		article.setArticleQuantity(articleData.getQuantity());
		article.setArticleDescription(articleData.getDescription());
		article.setItemTree(articleData.getItemTree());
		articleService.save(article);
		
		List<ParamsData> paramsDataList = articleData.getParamsDataList();
		Set<ArticleParam> articleParamSet = new HashSet<ArticleParam>();
		
		for(ParamsData paramsData: paramsDataList ){
			ArticleParam articleParam = new ArticleParam();
			articleParam.setArticle(article);
					
			Param param = paramService.getByValue(paramsData.getParamName());
			if (param!=null){
				articleParam.setParam(param);
			}
			
			
			ParamValue paramValue = paramValueService.getByValueAndUnits(paramsData.getParamValue(), paramsData.getParamUnits());
			if(paramValue!=null){
				articleParam.setParamValue(paramValue);
			}else{
				paramValue = new ParamValue();
				paramValue.setParamValue(paramsData.getParamValue());
				paramValue.setUnits(paramsData.getParamUnits());
				paramValueService.save(paramValue);
				paramValue = paramValueService.getByValueAndUnits(paramsData.getParamValue(), paramsData.getParamUnits());
				articleParam.setParamValue(paramValue);
			}
			articleService.update(article);		
			articleParam.setArticle(article);
			articleParamService.save(articleParam);
			articleParamSet.add(articleParam);

		}
		
		articleService.update(article);		
		
		
		return true;
	}

}
