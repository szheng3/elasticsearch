package com.elasticsearch.demo;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class Junit5ObjectAggregator implements ArgumentsAggregator {
    @Override
    public Junit5Object aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
        return new Junit5Object(arguments.getString(0),
                arguments.getInteger(1),
                arguments.get(2, JUNIT5.class)
        );
    }
}
