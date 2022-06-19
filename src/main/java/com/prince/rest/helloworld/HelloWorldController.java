package com.prince.rest.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collector;

@RestController
public class HelloWorldController {

    //Internationalization concept:

    @Autowired
    private MessageSource messageSource;


    @RequestMapping(method= RequestMethod.GET, path = "/hello-world")
    public String helloWorld(){
        return "Hello World";

    }
    // Print Message in JSON Format:
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello There, This is Prince");
    }

    //Path Variable
    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format(("Hello World, This is %s"), name));
    }

    //Internationalization (Way-1 to implement):

    @GetMapping(path = "/hello-world-bean-internationalized")
    public String helloWorldBeanInternationalized(@RequestHeader(value = "Accept-Language", required = false) Locale locale) {

        return messageSource.getMessage("good.morning.message", null, locale);

    }

    //Internationalization (Way-2 to implement):

    @GetMapping(path = "/hello-world-bean-internationalized-with-no-parameter")
    public String helloWorldBeanInternationalizedWithNoParameter() {

        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());

    }

}
