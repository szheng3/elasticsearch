package com.elasticsearch.demo.builder;

import com.elasticsearch.demo.dto.QuantityHistogramItem;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation.Bucket;

public final class QuantityHistogramItemBuilder {

    private int docCount;
    private double key;

    private QuantityHistogramItemBuilder() {
    }

    public static QuantityHistogramItemBuilder builder() {
        return new QuantityHistogramItemBuilder();
    }

    public QuantityHistogramItemBuilder withDocCount(int docCount) {
        this.docCount = docCount;
        return this;
    }

    public QuantityHistogramItemBuilder withKey(double key) {
        this.key = key;
        return this;
    }

    public QuantityHistogramItem build() {
        QuantityHistogramItem quantityHistogramItem = new QuantityHistogramItem();
        quantityHistogramItem.setDocCount(docCount);
        quantityHistogramItem.setKey(key);
        return quantityHistogramItem;
    }

    public QuantityHistogramItemBuilder withBucket(Bucket bucket) {
        return withKey((Double) bucket.getKey()).withDocCount((int) bucket.getDocCount());
    }
}
