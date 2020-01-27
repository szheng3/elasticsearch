package com.elasticsearch.demo;

import static org.junit.Assert.assertEquals;

import com.elasticsearch.demo.dto.CRUD;
import com.elasticsearch.demo.repo.CRUDRepo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private CRUDRepo crudRepo;

    @Test
    void contextLoads() {
        CRUD d = crudRepo.save(new CRUD(1, "D"));

        Iterable<CRUD> all = crudRepo.findAll();
        Collection<CRUD> cltn = new ArrayList<>();

        // Use iterable.forEach() to
        // Iterate through the iterable and
        // add each element into the collection
        all.forEach(cltn::add);
        assertEquals(cltn.size(), 1);

        Optional<CRUD> byId = crudRepo.findById(1);
        byId.ifPresent(crud -> {
            crud.setDescription("f");
            crudRepo.save(crud);
        });

        Optional<CRUD> byId1 = crudRepo.findById(1);
        byId1.ifPresent(crud -> {
            assertEquals(crud.getDescription(), "f");
            crudRepo.delete(crud);

        });

    }

}
