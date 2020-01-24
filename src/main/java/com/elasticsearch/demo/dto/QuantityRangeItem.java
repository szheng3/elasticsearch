package com.elasticsearch.demo.dto;

public class QuantityRangeItem{
	private int docCount;
	private double from;
	private double to;
	private String key;

	public void setDocCount(int docCount){
		this.docCount = docCount;
	}

	public int getDocCount(){
		return docCount;
	}

	public void setFrom(double from){
		this.from = from;
	}

	public double getFrom(){
		return from;
	}

	public void setTo(double to){
		this.to = to;
	}

	public double getTo(){
		return to;
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
			"QuantityRangeItem{" + 
			"doc_count = '" + docCount + '\'' + 
			",from = '" + from + '\'' + 
			",to = '" + to + '\'' + 
			",key = '" + key + '\'' + 
			"}";
		}
}
