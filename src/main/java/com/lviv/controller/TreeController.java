package com.lviv.controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lviv.model.ItemTree;
import com.lviv.model.Param;
import com.lviv.service.ArticleService;
import com.lviv.service.ItemTreeService;
import com.lviv.service.ParamService;
import com.lviv.service.UserService;
import com.lviv.utility.JasonResp;

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
	// List<Param> params;

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
			maxPriority = Collections.max(itemTree1.getParams()).getPriority();
			/*
			 * for (Param param1 : itemTree1.getParams()) { if
			 * (param1.getPriority() > maxPriority) { maxPriority =
			 * param1.getPriority(); } }
			 */
		} else {
			if (!itemTree1.getParentParams().isEmpty()) {
				maxPriority = Collections.max(itemTree1.getParentParams()).getPriority();
				/*
				 * for (Param param1 : itemTree1.getParentParams()) { if
				 * (param1.getPriority() > maxPriority) { maxPriority =
				 * param1.getPriority(); } }
				 */
			}
		}
		if (itemTree1.getChildrenList() != null) {
			List<Param> paramList = itemTreeService.getAllParamsOfChildren(itemTree1);
			for (Param param1 : paramList) {
				param1.setPriority(param1.getPriority() + 1);
				paramService.updateParam(param1);
			}
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

	@RequestMapping(value = "/editParamPriority", method = RequestMethod.GET)
	public @ResponseBody Boolean editParamPriorityAjax(@RequestParam("id") Integer idParam,
			@RequestParam("newPriority") Integer newPriority) {

		System.out.print("editParamPriorityAjax  ");
		System.out.println("idParam=" + idParam + " newPriority=" + newPriority);

		Param param = paramService.getById(idParam);
		Param param1 = paramService.getByPriorityAndIdItemTree(newPriority, param.getItemTree().getIdItemTree());
		if (param1 != null) {


				System.out.println("param wich need change: " + param1);
				System.out.println("called param:  " + param);
				
				param1.setPriority(param.getPriority());
				param.setPriority(newPriority);
				paramService.updateParam(param1);
				paramService.updateParam(param);

		}else return false;

		return true;
	}

	@RequestMapping(value = "/deleteParam", method = RequestMethod.GET)
	public @ResponseBody JasonResp deleteParamAjax(@RequestParam("id") Integer idParam) {

		System.out.print("deleteParamAjax  ");
		System.out.println("idParam=" + idParam);

		// Integer maxPriority = 0;

		try {
			Param param = paramService.getById(idParam);
			paramService.deleteParam(param);

			ItemTree itemTree1 = itemTreeService.getById(param.getItemTree().getIdItemTree());

			if (!itemTree1.getParams().isEmpty()) {

				for (Param param1 : itemTree1.getParams()) {
					if (param1.getPriority() > param.getPriority()) {
						param1.setPriority(param1.getPriority() - 1);
						paramService.updateParam(param1);
					}
				}
			}

			if (itemTree1.getChildrenList() != null) {
				List<Param> paramList = itemTreeService.getAllParamsOfChildren(itemTree1);
				for (Param param1 : paramList) {
					param1.setPriority(param1.getPriority() - 1);
					paramService.updateParam(param1);
				}
			}
		} catch (Exception e) {

			return new JasonResp("Unable delete param, it is already used.", false);
		}

		return new JasonResp("ok", true);
	}

}
