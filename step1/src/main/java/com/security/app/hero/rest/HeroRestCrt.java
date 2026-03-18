package com.security.app.hero.rest;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.security.app.hero.controller.HeroService;
import com.security.app.hero.model.Hero;


@RestController
public class HeroRestCrt {
	
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	HeroService hService;
	
	@RequestMapping(method=RequestMethod.POST,value="/hero")
	public void addHero(@RequestBody Hero hero) {
		hService.addHero(hero);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/hero/{id}")
	public Hero getHero(@PathVariable String id) {
		Hero h=hService.getHero(Integer.valueOf(id));
		return h;
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/hero")
	public List<Hero> getAllHero(HttpServletRequest request) {
		log.info("In the Rest Controller!");
		return hService.getAllHero();
	}

}
