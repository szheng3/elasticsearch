package com.elasticsearch.demo.dto;

public class QuantityHistogramItem{
	private int docCount;
	private double key;

	public void setDocCount(int docCount){
		this.docCount = docCount;
	}

	public int getDocCount(){
		return docCount;
	}

	public void setKey(double key){
		this.key = key;
	}

	public double getKey(){
		return key;
	}

	@Override
 	public String toString(){
		return 
			"QuantityHistogramItem{" + 
			"doc_count = '" + docCount + '\'' + 
			",key = '" + key + '\'' + 
			"}";
		}
}
