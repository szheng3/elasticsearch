package com.elasticsearch.demo.response;

import com.elasticsearch.demo.dto.AggDto;
import com.elasticsearch.demo.dto.Ecommerce;
import java.util.List;

public class ElasticSearchResponse {

    private List<Ecommerce> ecommerce;

    private AggDto agg;
    private Long total;

    public List<Ecommerce> getEcommerce() {
        return ecommerce;
    }

    public void setEcommerce(List<Ecommerce> ecommerce) {
        this.ecommerce = ecommerce;
    }

    public void setAgg(AggDto agg) {
        this.agg = agg;
    }

    public AggDto getAgg() {
        return agg;
    }

    public void setTotal(Long total) {
        this.total=total;
    }

    public Long getTotal() {
        return total;
    }
}
