package com.elasticsearch.demo.builder;

import com.elasticsearch.demo.dto.AggDto;
import com.elasticsearch.demo.dto.CategoryItem;
import com.elasticsearch.demo.dto.Ecommerce;
import com.elasticsearch.demo.dto.QuantityHistogramItem;
import com.elasticsearch.demo.dto.StatuesItem;
import com.elasticsearch.demo.response.ElasticSearchResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

public final class ElasticSearchResponseBuilder {

    private List<Ecommerce> ecommerce;
    private AggDto agg;
    private Long total;
    private ElasticSearchResponseBuilder() {
    }

    public static ElasticSearchResponseBuilder builder() {
        return new ElasticSearchResponseBuilder();
    }

    public ElasticSearchResponseBuilder withEcommerce(List<Ecommerce> ecommerce) {
        this.ecommerce = ecommerce;
        return this;
    }
    public ElasticSearchResponseBuilder withAgg(AggDto agg) {
        this.agg = agg;
        return this;
    }
    public ElasticSearchResponseBuilder withTotal(Long total) {
        this.total = total;
        return this;
    }

    public ElasticSearchResponse build() {
        ElasticSearchResponse elasticSearchResponse = new ElasticSearchResponse();
        elasticSearchResponse.setEcommerce(ecommerce);
        elasticSearchResponse.setAgg(agg);
        elasticSearchResponse.setTotal(total);

        return elasticSearchResponse;
    }

    public ElasticSearchResponseBuilder withSearchResponse(SearchResponse response) {
        long totalHits = response.getHits().getTotalHits();
        MultiBucketsAggregation statuses = response.getAggregations().get("statuses");
        List<StatuesItem> statuesItemBuilders = statuses.getBuckets().stream().map(o -> StatuesItemBuilder.builder().withBucket(o).build()).collect(Collectors.toList());
        MultiBucketsAggregation his = response.getAggregations().get("his");
        List<QuantityHistogramItem> histogram = his.getBuckets().stream().map(o -> QuantityHistogramItemBuilder.builder().withBucket(o).build()).collect(Collectors.toList());

        MultiBucketsAggregation categories = response.getAggregations().get("categories");
        List<CategoryItem> categoryItems = categories.getBuckets().stream().map(o -> CategoryItemBuilder.builder().withBucket(o).build()).collect(Collectors.toList());
        AggDto aggDto = AggDtoBuilder.builder().withStatues(statuesItemBuilders).withCategory(categoryItems).withQuantityHistogram(histogram).build();
        List<Ecommerce> ecommerces = Stream.of(response.getHits().getHits())
                .map(this::getEcommerce)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return withEcommerce(ecommerces).withAgg(aggDto).withTotal(totalHits);
    }

    private Ecommerce getEcommerce(SearchHit searchHit) {

        try {

            Ecommerce ecommerce = new ObjectMapper().readValue(searchHit.getSourceAsString(), Ecommerce.class);
            ecommerce.set_id(Integer.valueOf(searchHit.getId()));
            Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
            Optional.ofNullable(highlightFields.get("name")).map(HighlightField::getFragments).map(Stream::of)
                    .orElseGet(Stream::empty).filter(Objects::nonNull).findFirst().map(Text::string).ifPresent(ecommerce::setName);
            Optional.ofNullable(highlightFields.get("description")).map(HighlightField::getFragments).map(Stream::of)
                    .orElseGet(Stream::empty).filter(Objects::nonNull).findFirst().map(Text::string).ifPresent(ecommerce::setDescription);
            return ecommerce;
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
