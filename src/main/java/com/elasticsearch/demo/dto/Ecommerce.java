package com.elasticsearch.demo.dto;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "ecommerce",type = "product")
public class Ecommerce{

	@Id
	private Integer _id;
	private int quantity;
	private String price;
	private String name;
	private String description;
	private List<CategoriesItem> categories;
	private String status;
	private List<String> tags;

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setTags(List<String> tags){
		this.tags = tags;
	}

	public List<String> getTags(){
		return tags;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	@Override
 	public String toString(){
		return 
			"Ecommerce{" + 
			"quantity = '" + quantity + '\'' + 
			",price = '" + price + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",categories = '" + categories + '\'' + 
			",status = '" + status + '\'' + 
			",tags = '" + tags + '\'' + 
			"}";
		}
}
