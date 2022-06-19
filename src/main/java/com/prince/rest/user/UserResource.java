package com.prince.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
public class UserResource {

    @Autowired
    private UserDaoServices services;


    //Retrieve All Users
    @GetMapping("/users")
    public List<Users> retriveAll(){
        return services.findAll();
    }


    //Retrieve Specific User-
    @GetMapping("/users/{id}")
    public Resource<Users> findSpecific(@PathVariable int id) throws Exception {
        Users user = services.findOne(id);

            if(user==null){
                throw new UserNotFoundException("User Not Found For ID-" + id);
            }

            //Apply 'Hateos' dependency for adding link
        Resource resource = new Resource(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAll());

        resource.add(linkTo.withRel("all-users"));
        return resource;
    }


    //save a new user-
    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody Users myUser){
        Users savedUser = services.save(myUser);

        // Code for Returning Correct Response Status-

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    //Delete a user-
    @DeleteMapping("user/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) throws Exception{
        Users user = services.deleteById(id);

        if(user==null){
            throw new UserNotFoundException("User Not Found For ID-" + id);
        }
        return ResponseEntity.ok().build();
    }
}
