package com.elasticsearch.demo.builder;

import com.elasticsearch.demo.dto.StatuesItem;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation.Bucket;

public final class StatuesItemBuilder {

    private int docCount;
    private String key;

    private StatuesItemBuilder() {
    }

    public static StatuesItemBuilder builder() {
        return new StatuesItemBuilder();
    }

    public StatuesItemBuilder withDocCount(int docCount) {
        this.docCount = docCount;
        return this;
    }

    public StatuesItemBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public StatuesItem build() {
        StatuesItem statuesItem = new StatuesItem();
        statuesItem.setDocCount(docCount);
        statuesItem.setKey(key);
        return statuesItem;
    }

    public StatuesItemBuilder withBucket(Bucket bucket) {
        return withKey((String) bucket.getKey()).withDocCount((int) bucket.getDocCount());
    }
}
