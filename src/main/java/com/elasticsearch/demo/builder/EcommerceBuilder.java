package com.elasticsearch.demo.builder;

import com.elasticsearch.demo.dto.CategoriesItem;
import com.elasticsearch.demo.dto.Ecommerce;
import java.util.List;

public final class EcommerceBuilder {

    private Integer _id;
    private int quantity;
    private String price;
    private String name;
    private String description;
    private List<CategoriesItem> categories;
    private String status;
    private List<String> tags;

    private EcommerceBuilder() {
    }

    public static EcommerceBuilder builder() {
        return new EcommerceBuilder();
    }

    public EcommerceBuilder with_id(Integer _id) {
        this._id = _id;
        return this;
    }

    public EcommerceBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public EcommerceBuilder withPrice(String price) {
        this.price = price;
        return this;
    }

    public EcommerceBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EcommerceBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public EcommerceBuilder withCategories(List<CategoriesItem> categories) {
        this.categories = categories;
        return this;
    }

    public EcommerceBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public EcommerceBuilder withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Ecommerce build() {
        Ecommerce ecommerce = new Ecommerce();
        ecommerce.set_id(_id);
        ecommerce.setQuantity(quantity);
        ecommerce.setPrice(price);
        ecommerce.setName(name);
        ecommerce.setDescription(description);
        ecommerce.setCategories(categories);
        ecommerce.setStatus(status);
        ecommerce.setTags(tags);
        return ecommerce;
    }
}
