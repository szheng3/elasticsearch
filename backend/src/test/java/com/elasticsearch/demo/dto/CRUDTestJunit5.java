package com.elasticsearch.demo.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.elasticsearch.demo.JUNIT5;
import com.elasticsearch.demo.Junit5Object;
import com.elasticsearch.demo.Junit5ObjectAggregator;
import com.elasticsearch.demo.repo.CRUDRepo;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CRUDTestJunit5 {

    @Autowired
    private CRUDRepo crudRepo;

    @Test
    @DisplayName("Custom test name containing spaces")
    void name() {
        Optional<CRUD> byId = crudRepo.findById(1);
        assertEquals(null, byId.orElse(null));
    }

//    @ParameterizedTest(name = "Year {0} is a leap year.")
//    @ValueSource(ints = {2016, 2020, 2048})
//    void if_it_is_one_of_the_following_years(int year) {
//        assertEquals(2016, year);
//    }

    @ParameterizedTest
    @CsvSource( {
            "apple,         1,d,FIRST",
            "banana,        2,d,FIRST",
            "'lemon, lime', 0xF1,d,SECOND"
    })
    void testWithCsvSource(String fruit, int rank, String des, JUNIT5 junit5) {
        System.out.println(fruit + rank + des);
        assertNotNull(fruit);
        assertNotNull(junit5);
        assertNotEquals(0, rank);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/two-column.csv")
    void testWithCsvFileSource(String country, int reference,String value) {
        assertNotNull(country);
        assertNotNull(value);
        assertNotEquals(0, reference);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/two-column.csv")
    void testWithCsvFile(@AggregateWith(Junit5ObjectAggregator.class) Junit5Object j) {
        assertNotNull(j.getDescription());
        assertNotNull(j.getName());
    }
}
