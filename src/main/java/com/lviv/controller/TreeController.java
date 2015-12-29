package com.lviv.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lviv.model.Article;
import com.lviv.model.ItemTree;
import com.lviv.model.Param;
import com.lviv.model.userdatas.UserAddress;
import com.lviv.service.ArticleService;
import com.lviv.service.ItemTreeService;
import com.lviv.service.ParamService;
import com.lviv.service.UserService;

@Controller
public class TreeController {

	@Autowired
	UserService userService;
	@Autowired
	ArticleService articleService;
	@Autowired
	ItemTreeService itemTreeService;
	@Autowired
	ParamService paramService;

	List<ItemTree> itemTrees = new LinkedList<ItemTree>();
	List<Param> params;

	@RequestMapping(value = "/addArticlePage", method = RequestMethod.GET)
	public ModelAndView addArticlePage() {
		ModelAndView mv = new ModelAndView("AddArticlePage");
		return mv;
	}

	@RequestMapping(value = "/showTree", method = RequestMethod.GET)
	public ModelAndView showTree() {

		ModelAndView mv = new ModelAndView("tree");
		ItemTree itemTree = itemTreeService.getById(itemTreeService.getRootId());
		mv.addObject("itemTree", itemTree);
		return mv;
	}

	@RequestMapping(value = "/addItemTree", method = RequestMethod.GET)
	public ModelAndView addItemTree(@RequestParam("newItemTreeName") String newItemTreeName,
			@RequestParam("parentIdItemTree") Integer parentIdItemTree) {
		// ItemTree itemTree1 = itemTreeService.getById(parentIdItemTree);
		// itemTreeService.exist(value,parentId); перевірити чи елемент вже
		// існує в межах своєї категорії
		ItemTree itemTree1 = new ItemTree();
		itemTree1.setValue(newItemTreeName);
		itemTreeService.addItem(itemTree1, parentIdItemTree);

		ModelAndView mv = new ModelAndView("tree");
		ItemTree itemTree = itemTreeService.getById(itemTreeService.getRootId());
		// String message = "hello";
		// mv.addObject("message", message);
		mv.addObject("itemTree", itemTree);

		return mv;
	}

	@RequestMapping(value = "/deleteItemTree", method = RequestMethod.GET)
	public ModelAndView deleteItemTree(@RequestParam("deleteIdItemTree") Integer deleteIdItemTree) {
		System.out.print("deleteItemTree  ");
		System.out.println("IdItemTree= " + deleteIdItemTree);

		ItemTree itemTree1 = itemTreeService.getById(deleteIdItemTree);
		itemTreeService.deleteItem(itemTree1);

		ModelAndView mv = new ModelAndView("tree");
		ItemTree itemTree = itemTreeService.getById(itemTreeService.getRootId());
		mv.addObject("itemTree", itemTree);

		return mv;
	}

	@RequestMapping(value = "/editItemTree", method = RequestMethod.GET)
	public ModelAndView editItemTree(@RequestParam("editItemTreeName") String editItemTreeName,
			@RequestParam("IdItemTree") Integer IdItemTree) {
		System.out.print("editItemTree  ");
		System.out.print("IdItemTree= " + IdItemTree);
		System.out.println("   editItemTreeName= " + editItemTreeName);

		ItemTree itemTree1 = itemTreeService.getById(IdItemTree);

		itemTree1.setValue(editItemTreeName);

		itemTreeService.updateItem(itemTree1);

		ModelAndView mv = new ModelAndView("tree");
		ItemTree itemTree = itemTreeService.getById(itemTreeService.getRootId());
		mv.addObject("itemTree", itemTree);

		return mv;
	}

	@RequestMapping(value = "/ItemTreeSelect", method = RequestMethod.GET)
	public @ResponseBody ItemTree showSelectedTree(@RequestParam("id") Integer id) {
		System.out.print("showSelectedTree  ");
		System.out.println("IdItemTree= " + id);

		ItemTree selectedItemTree = itemTreeService.getById(id);

		Set<Param> paramList = selectedItemTree.getParams();
		Set<Param> parentParams = selectedItemTree.getParentParams();

		if (paramList.size() == 0) {
			selectedItemTree.getParams().add(new Param());
		}
		if (parentParams.size() == 0) {
			selectedItemTree.getParentParams().add(new Param());
		}

		// System.out.println("paramList.get(0):
		// "+paramList.get(0).getParamName());
		// System.out.println("parentParams.get(0):
		// "+parentParams.get(0).getParamName());

		return selectedItemTree;
	}

	@RequestMapping(value = "/addParam", method = RequestMethod.GET)
	public @ResponseBody Boolean addParamAjax(@RequestParam("id") Integer idItemTree,
			@RequestParam("name") String paramName) {

		System.out.print("addParamAjax  ");
		System.out.println("idItemTree=" + idItemTree + " paramName=" + paramName);
		Integer maxPriority = 0;
		Param param = new Param();
		param.setParamName(paramName);
		ItemTree itemTree1 = itemTreeService.getById(idItemTree);

		if (!itemTree1.getParams().isEmpty()) {

			for (Param param1 : itemTree1.getParams()) {
				if (param1.getPriority() > maxPriority) {
					maxPriority = param1.getPriority();
				}
			}
		} else {
			if (!itemTree1.getParentParams().isEmpty()) {
				for (Param param1 : itemTree1.getParentParams()) {
					if (param1.getPriority() > maxPriority) {
						maxPriority = param1.getPriority();
					}
				}
			}
		}
		if(!itemTree1.getChildrenList().isEmpty()){
			
		}
		param.setPriority(maxPriority + 1);
		param.setItemTree(itemTree1);
		paramService.saveParam(param);
		return true;
	}

	@RequestMapping(value = "/editParam", method = RequestMethod.GET)
	public @ResponseBody Boolean editParamAjax(@RequestParam("id") Integer idParam,
			@RequestParam("name") String paramName) {

		System.out.print("editParamAjax  ");
		System.out.println("idParam=" + idParam + " paramName=" + paramName);

		Param param = paramService.getById(idParam);
		param.setParamName(paramName);
		paramService.updateParam(param);
		return true;
	}

	@RequestMapping(value = "/deleteParam", method = RequestMethod.GET)
	public @ResponseBody Boolean deleteParamAjax(@RequestParam("id") Integer idParam) {

		System.out.print("deleteParamAjax  ");
		System.out.println("idParam=" + idParam);

		Param param = paramService.getById(idParam);
		paramService.deleteParam(param);
		return true;
	}

}
