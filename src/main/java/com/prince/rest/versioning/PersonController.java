package com.prince.rest.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @GetMapping("person/V1")
    public PersonV1 setNameV1(){
        PersonV1 nameV1 = new PersonV1("Prince Jain");
        return nameV1;
    }
    @GetMapping("person/V2")
    public PersonV2 setNameV2(){
        PersonV2 nameV2 = new PersonV2(new Name("Prince", "Jain"));
        return nameV2;
    }

    // Do the same thing as above, but with same mapping and params-

    @GetMapping(value = "person", params = "Version=1")
    public PersonV1 paramV1(){
        PersonV1 param1 = new PersonV1("Prince Jain");
        return param1;
    }
    @GetMapping(value = "person", params = "Version=2")
    public PersonV2 paramV2(){
        PersonV2 param2 = new PersonV2(new Name("Prince", "Jain"));
        return param2;
    }

    // Do the same thing as above, but with same mapping and headers-

    @GetMapping(value = "person", headers = "X-API-Version=1")
    public PersonV1 headerV1(){
        PersonV1 header1 = new PersonV1("Prince Jain");
        return header1;
    }
    @GetMapping(value = "person", headers = "X-API-Version=2")
    public PersonV2 headerV2(){
        PersonV2 header2 = new PersonV2(new Name("Prince", "Jain"));
        return header2;
    }

    // Do the same thing as above, but with same mapping and using produces-

    @GetMapping(value = "person", produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1(){
        PersonV1 produces1 = new PersonV1("Prince Jain");
        return produces1;
    }
    @GetMapping(value = "person", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2(){
        PersonV2 produces2 = new PersonV2(new Name("Prince", "Jain"));
        return produces2;
    }

}
