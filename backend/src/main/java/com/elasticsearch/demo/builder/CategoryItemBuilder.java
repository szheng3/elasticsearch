package com.elasticsearch.demo.builder;

import com.elasticsearch.demo.dto.CategoryItem;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation.Bucket;

public final class CategoryItemBuilder {

    private int docCount;
    private String key;

    private CategoryItemBuilder() {
    }

    public static CategoryItemBuilder builder() {
        return new CategoryItemBuilder();
    }

    public CategoryItemBuilder withDocCount(int docCount) {
        this.docCount = docCount;
        return this;
    }

    public CategoryItemBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public CategoryItem build() {
        CategoryItem categoryItem = new CategoryItem();
        categoryItem.setDocCount(docCount);
        categoryItem.setKey(key);
        return categoryItem;
    }

    public CategoryItemBuilder withBucket(Bucket bucket) {
        return withKey((String) bucket.getKey()).withDocCount((int) bucket.getDocCount());
    }
}
