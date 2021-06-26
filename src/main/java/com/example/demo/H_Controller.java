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
public class H_Controller {

	@Autowired
	H_Repository repository;
	@Autowired
	P_Repository p_repository;
	
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mv) {
		mv.addObject("title","top");
		mv.setViewName("index");
		return mv;
	}
	//write.htmlの表示
	@RequestMapping("/write")
	public ModelAndView write(ModelAndView mv) {
		mv.addObject("form",new H_Entity());
		mv.setViewName("write");
		return mv;
	}
	//フォームからのデータをDBに挿入
	@RequestMapping("/insert")
	public ModelAndView insert(@ModelAttribute("form")@Validated H_Entity entity,
			BindingResult result,ModelAndView mv) {
		if(result.hasErrors()) {
			mv.setViewName("write");
			return mv;
		}
		repository.saveAndFlush(entity);
		return new ModelAndView("redirect:/list");
	}
	
	@RequestMapping("/list")
	public ModelAndView list(ModelAndView mv) {
		List<H_Entity> list = repository.findAll();
		mv.addObject("title","hitters");
		mv.addObject("list",list);
		mv.setViewName("hitters");
		return mv;
	}
	
	@RequestMapping("/data/{id}")
	public ModelAndView data(ModelAndView mv,@PathVariable Integer id) {
		
		return set(mv,id,"詳細","data");
	}
	
	@RequestMapping("/update/{id}")
	public ModelAndView update(ModelAndView mv,@PathVariable Integer id) {
		return set(mv,id,"更新","h_update");
	}
	
	@RequestMapping("/delete")
	public ModelAndView remove(ModelAndView mv ,@RequestParam("id")Integer id) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/list");
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(ModelAndView mv,@PathVariable Integer id) {
		return set(mv,id,"削除","data");
	}
	
	@RequestMapping("/search")
	public ModelAndView search(ModelAndView mv,@RequestParam("name")String name) {
		List<H_Entity> list = repository.findByNameLike("%" + name + "%");
		mv.addObject("list",list);
		mv.setViewName("hitters");
		return mv;
	}
	
	@RequestMapping("/ranking")
	public ModelAndView ranking(ModelAndView mv,H_Entity entity,P_Entity p_entity) {
		List<H_Entity> homeruns =  repository.findByHomerun();
		mv.addObject("homeruns",homeruns);
		List<H_Entity> hits =  repository.findByHit();
		mv.addObject("hits",hits);
 		List<H_Entity> points = repository.findByPoint();
 		mv.addObject("points",points);
 		List<H_Entity> stolens = repository.findByStolen();
 		mv.addObject("stolens",stolens);
 		List<P_Entity> givvens = p_repository.findByGivven();
 		mv.addObject("givvens",givvens);
 		List<P_Entity> four_ball = p_repository.findByFour();
 		mv.addObject("four_ball",four_ball);
		mv.setViewName("ranking");
		return mv;
	}
	
	
	
	private ModelAndView set(ModelAndView mv,Integer id,String title,String template) {
		Optional<H_Entity> data = repository.findById(id);
		if(!data.isPresent()) {
			return new ModelAndView("redirect:/");
		}
		mv.addObject("form",data.get());
		mv.addObject("title",title);
		mv.setViewName(template);
		return mv;
	}
	
}






