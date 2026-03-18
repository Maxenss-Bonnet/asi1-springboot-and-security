package com.security.app.hero.controller;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.security.app.hero.model.Hero;

public interface HeroRepository extends CrudRepository<Hero, Integer> {

	public List<Hero> findByName(String name);
}
