package com.elasticsearch.demo.dto;

public class StatuesItem{
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
			"StatuesItem{" + 
			"doc_count = '" + docCount + '\'' + 
			",key = '" + key + '\'' + 
			"}";
		}
}
