package com.elasticsearch.demo.repo;

import com.elasticsearch.demo.dto.Ecommerce;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EcommerceRepo  extends ElasticsearchRepository<Ecommerce,Integer> {

}
