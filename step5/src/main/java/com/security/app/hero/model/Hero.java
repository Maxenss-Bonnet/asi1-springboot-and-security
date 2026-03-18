package com.security.app.hero.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "HERO")
public class Hero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String superPowerName;
	private int superPowerValue;
	private String imgUrl;
	
	public Hero() {
	}

	public Hero(int id,String name, String superPowerName, int superPowerValue, String imgUrl) {
		super();
		this.id=id;
		this.name = name;
		this.superPowerName = superPowerName;
		this.superPowerValue = superPowerValue;
		this.imgUrl = imgUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuperPowerName() {
		return superPowerName;
	}

	public void setSuperPowerName(String superPowerName) {
		this.superPowerName = superPowerName;
	}

	public int getSuperPowerValue() {
		return superPowerValue;
	}

	public void setSuperPowerValue(int superPowerValue) {
		this.superPowerValue = superPowerValue;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "HERO ["+this.id+"]: name:"+this.name+", superPowerName:"+this.superPowerName+", superPowerValue:"+this.superPowerValue+" imgUrl:"+this.imgUrl;
	}
}
