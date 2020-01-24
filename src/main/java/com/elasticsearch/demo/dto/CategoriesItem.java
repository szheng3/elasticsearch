package com.elasticsearch.demo.dto;

public class CategoriesItem{
	private String name;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"CategoriesItem{" + 
			"name = '" + name + '\'' + 
			"}";
		}
}
