package com.elasticsearch.demo.dto;

import java.util.List;

public class AggDto{
	private List<StatuesItem> statues;
	private List<QuantityRangeItem> quantityRange;
	private List<CategoryItem> category;
	private List<QuantityHistogramItem> quantityHistogram;

	public void setStatues(List<StatuesItem> statues){
		this.statues = statues;
	}

	public List<StatuesItem> getStatues(){
		return statues;
	}

	public void setQuantityRange(List<QuantityRangeItem> quantityRange){
		this.quantityRange = quantityRange;
	}

	public List<QuantityRangeItem> getQuantityRange(){
		return quantityRange;
	}

	public void setCategory(List<CategoryItem> category){
		this.category = category;
	}

	public List<CategoryItem> getCategory(){
		return category;
	}

	public void setQuantityHistogram(List<QuantityHistogramItem> quantityHistogram){
		this.quantityHistogram = quantityHistogram;
	}

	public List<QuantityHistogramItem> getQuantityHistogram(){
		return quantityHistogram;
	}

	@Override
 	public String toString(){
		return 
			"AggDto{" + 
			"statues = '" + statues + '\'' + 
			",quantity_range = '" + quantityRange + '\'' + 
			",category = '" + category + '\'' + 
			",quantity_histogram = '" + quantityHistogram + '\'' + 
			"}";
		}
}