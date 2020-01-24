package com.elasticsearch.demo.dto;

public class CategoryItem{
	private int docCount;
	private String key;

	public void setDocCount(int docCount){
		this.docCount = docCount;
	}

	public int getDocCount(){
		return docCount;
	}

	public void setKey(String key){
		this.key = key;
	}

	public String getKey(){
		return key;
	}

	@Override
 	public String toString(){
		return 
			"CategoryItem{" + 
			"doc_count = '" + docCount + '\'' + 
			",key = '" + key + '\'' + 
			"}";
		}
}
