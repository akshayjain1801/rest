package com.prince.rest.user;

import com.prince.rest.posts.Post;
import com.prince.rest.posts.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
public class UserJPAResource {

    @Autowired
    private UserJPARepository jpaService;

    @Autowired
    private PostRepository postServices;

    //Retrieve All Users
    @GetMapping("jpa/users")
    public List<Users> retriveAll(){
        return jpaService.findAll();
    }


    //Retrieve Specific User-
    @GetMapping("jpa/users/{id}")
    public Resource<Users> findSpecific(@PathVariable int id) throws Exception {
        Optional<Users> user = jpaService.findById(id);

            if(!user.isPresent()){
                throw new UserNotFoundException("User Not Found For ID-" + id);
            }

            //Apply 'Hateos' dependency for adding link
        Resource resource = new Resource(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAll());

        resource.add(linkTo.withRel("all-users"));
        return resource;
    }


    //save a new user-
    @PostMapping("jpa/users")
    public ResponseEntity createUser(@Valid @RequestBody Users myUser){
        Users savedUser = jpaService.save(myUser);

        // Code for Returning Correct Response Status-

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("jpa/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        jpaService.deleteById(id);


        return ResponseEntity.ok().build();

    }

    @GetMapping("jpa/users/{id}/posts")
    public List<Post> retriveAllPosts(@PathVariable int id){
        Optional<Users> user = jpaService.findById(id);

        if(!user.isPresent()){
            throw new UserNotFoundException("User Not Found - " + id );
        }
        List<Post> allPosts = user.get().getPosts();
        return allPosts;



    }

    @PostMapping("jpa/users/{id}/createPost")
    public ResponseEntity createPost(@PathVariable int id, @Valid @RequestBody Post post){
        Optional<Users> user = jpaService.findById(id);
        if(!user.isPresent()){
            throw new  UserNotFoundException("User Not Found - "+ id);
        }

//        List<Post> list = user.get().getPosts();
//        list.add(post);
        post.setUsers(user.get());
        postServices.save(post);


        // Code for Returning Correct Response Status-

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
