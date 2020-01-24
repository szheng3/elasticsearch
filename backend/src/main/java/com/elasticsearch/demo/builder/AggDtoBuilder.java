package com.elasticsearch.demo.builder;

import com.elasticsearch.demo.dto.AggDto;
import com.elasticsearch.demo.dto.CategoryItem;
import com.elasticsearch.demo.dto.QuantityHistogramItem;
import com.elasticsearch.demo.dto.QuantityRangeItem;
import com.elasticsearch.demo.dto.StatuesItem;
import java.util.List;

public final class AggDtoBuilder {

    private List<StatuesItem> statues;
    private List<QuantityRangeItem> quantityRange;
    private List<CategoryItem> category;
    private List<QuantityHistogramItem> quantityHistogram;

    private AggDtoBuilder() {
    }

    public static AggDtoBuilder builder() {
        return new AggDtoBuilder();
    }

    public AggDtoBuilder withStatues(List<StatuesItem> statues) {
        this.statues = statues;
        return this;
    }

    public AggDtoBuilder withQuantityRange(List<QuantityRangeItem> quantityRange) {
        this.quantityRange = quantityRange;
        return this;
    }

    public AggDtoBuilder withCategory(List<CategoryItem> category) {
        this.category = category;
        return this;
    }

    public AggDtoBuilder withQuantityHistogram(List<QuantityHistogramItem> quantityHistogram) {
        this.quantityHistogram = quantityHistogram;
        return this;
    }

    public AggDto build() {
        AggDto aggDto = new AggDto();
        aggDto.setStatues(statues);
        aggDto.setQuantityRange(quantityRange);
        aggDto.setCategory(category);
        aggDto.setQuantityHistogram(quantityHistogram);
        return aggDto;
    }
}
