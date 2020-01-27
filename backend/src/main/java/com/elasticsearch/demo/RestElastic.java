package com.elasticsearch.demo;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

import com.elasticsearch.demo.builder.ElasticSearchResponseBuilder;
import com.elasticsearch.demo.repo.EcommerceRepo;
import com.elasticsearch.demo.response.ElasticSearchResponse;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RestElastic {


    @Autowired
    private EcommerceRepo ecommerceRepo;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

//    @GetMapping("/ecommerce")
//    public Object getAll2() {
//
////        QueryBuilder query = QueryBuilders.matchQuery("name", "tom");
////        SearchQuery searchQuery = new NativeSearchQueryBuilder()
////                .withQuery(query)
////                .withHighlightFields(new Field("name")).build();
////        return elasticsearchTemplate.query(searchQuery, new CustomResultExtractor());
//        return ecommerceRepo.findAll();
//
//    }

    @GetMapping("/")
    public Object getIndex(){
        return new HashMap<String,String>(){
            {
                put("name","Hello");
            }
        };
    }

    @GetMapping("/ecommerce")
    public Object getEcommerce(
            @RequestParam("q") String search,
            @RequestParam(required = false, defaultValue = "0") Integer index,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false) Optional<String> status,
            @RequestParam(required = false) Optional<String[]> category

    ) {
        BoolQueryBuilder boolQueryBuilder = boolQuery();
        status.ifPresent(s -> {
            if (!s.equals("")) {
                boolQueryBuilder.must(QueryBuilders.termQuery("status", s));
            }

        });
        category.ifPresent(strings -> {
            Stream.of(strings).filter(s -> !s.equals("")).forEach(s -> {
                boolQueryBuilder.must(QueryBuilders.termQuery("categories.name.keyword", s));

            });
        });

        PageRequest pageable = PageRequest.of(index, size);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("ecommerce")
                .withQuery(
                        boolQuery()
                                .must(
                                        QueryBuilders.multiMatchQuery(search)
                                                .field("name")
                                                .field("description")
                                                .fuzziness(Fuzziness.AUTO)
                                )
                                .filter(
                                        boolQueryBuilder
                                )
                )
                .withHighlightFields(
                        new HighlightBuilder.Field("name")
                                .numOfFragments(0).preTags("<b>").postTags("</b>"),
                        new HighlightBuilder.Field("description")
                                .numOfFragments(0).preTags("<b>").postTags("</b>")
                )
                .withPageable(pageable)
                .addAggregation(AggregationBuilders.terms("statuses").field("status.keyword"))
                .addAggregation(AggregationBuilders.terms("categories").field("categories.name.keyword"))
                .addAggregation(AggregationBuilders.histogram("his").field("quantity").interval(10))
                .build();

        ElasticSearchResponse aggregations = elasticsearchTemplate.query(searchQuery, response -> ElasticSearchResponseBuilder.builder().withSearchResponse(response).build());
        //hits

        //response.getHits().getHits()
//        aggregations.

//        Terms terms = aggregations.getAggregations().get("statuses");   //查询遍历第一个根据货号分组的aggregation
//        List<? extends Bucket> buckets = terms.getBuckets();
//        for (Bucket entry : buckets
//        ) {
//            System.out.println("【 " + entry.getKey() + " 】订单数 : " + entry.getDocCount());
//
//        }

//        InternalHistogram terms = aggregations.getAggregations().get("his");
//        List<InternalHistogram.Bucket> buckets = terms.getBuckets();//查询遍历第一个根据货号分组的aggregation
//        for (InternalHistogram.Bucket entry:buckets
//        ) {
//            System.out.println("【 " + entry.getKey() + " 】订单数 : " + entry.getDocCount() );
//
//        }

        return aggregations;

    }


}
