package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class P_Controller {
	@Autowired
	P_Repository repository;
	
	//write.htmlの表示
	@RequestMapping("/p_write")
	public ModelAndView p_write(ModelAndView mv) {
		mv.addObject("p_form",new P_Entity());
		mv.setViewName("p_write");
		return mv;
	}
	//フォームからのデータをDBに挿入
	@RequestMapping("/p_insert")
	public ModelAndView p_insert(@ModelAttribute("p_form") @Validated P_Entity entity,
			BindingResult result,ModelAndView mv) {
		if(result.hasErrors()) {
			mv.setViewName("p_write");
			return mv;
		}
		repository.saveAndFlush(entity);
		return new ModelAndView("redirect:/p_list");
	}
	
	@RequestMapping("/p_list")
	public ModelAndView p_list(ModelAndView mv) {
		List<P_Entity> list = repository.findAll();
		mv.addObject("title","pichers");
		mv.addObject("p_list",list);
		mv.setViewName("pichers");
		return mv;
	}
	
	@RequestMapping("/p_data/{id}")
	public ModelAndView p_data(ModelAndView mv,@PathVariable Integer id) {
		
		return p_set(mv,id,"p詳細","p_data");
	}
	
	@RequestMapping("/p_update/{id}")
	public ModelAndView p_update(ModelAndView mv,@PathVariable Integer id) {
		return p_set(mv,id,"p更新","p_update");
	}
	
	@RequestMapping("/p_delete")
	public ModelAndView p_remove(ModelAndView mv ,@RequestParam("id")Integer id) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/p_list");
	}
	
	@RequestMapping("/p_delete/{id}")
	public ModelAndView p_delete(ModelAndView mv,@PathVariable Integer id) {
		return p_set(mv,id,"p削除","p_data");
	}
	
	@RequestMapping("/search2")
	public ModelAndView search(ModelAndView mv,@RequestParam("name")String name) {
		List<P_Entity> p_list = repository.findByNameLike("%" + name + "%");
		mv.addObject("p_list",p_list);
		mv.setViewName("pichers");
		return mv;
	}
	
	
	
	private ModelAndView p_set(ModelAndView mv,Integer id,String title,String template) {
		Optional<P_Entity> data = repository.findById(id);
		if(!data.isPresent()) {
			return new ModelAndView("redirect:/");
		}
		mv.addObject("p_form",data.get());
		mv.addObject("title",title);
		mv.setViewName(template);
		return mv;
	}
}
