package com.security.app.hero.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.app.hero.model.Hero;

@Service
public class HeroService {
	@Autowired
	HeroRepository hRepository;
	public void addHero(Hero h) {
		h.setId(null);
		Hero createdHero=hRepository.save(h);
		System.out.println(createdHero);
	}
	
	public Hero getHero(int id) {
		Optional<Hero> hOpt =hRepository.findById(id);
		if (hOpt.isPresent()) {
			return hOpt.get();
		}else {
			return null;
		}
	}

	public List<Hero> getAllHero() {
		List<Hero> heroList = new ArrayList<>();
		hRepository.findAll().forEach(heroList::add);
		return heroList;
	}

}
