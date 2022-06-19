package com.prince.rest.filter;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilterController {
    @GetMapping(path = "/filter-beans")
    //MappingJackson supports dynamic Filtering-
    public MappingJacksonValue pickSomeBeans(){
        SomeBeans someBeans =  new SomeBeans("Username", "Email-id", "Password");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("feild1", "feild2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("Some-Beans-Filter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(someBeans);
        mapping.setFilters(filters);
        return mapping;
    }
    @GetMapping(path = "/filter-listof-beans")
    public MappingJacksonValue pickSomeBeansList(){

        List<SomeBeans> list =  Arrays.asList(new SomeBeans("Username-1", "E-mail-id-1", "Password-1"), new SomeBeans("Username-2", "E-mail-id-2", "Password-2"));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("feild1");
        FilterProvider filters = new SimpleFilterProvider().addFilter("Some-Beans-Filter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);
        return mapping;

    }
}
