package com.prince.rest.user;


import com.prince.rest.posts.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

// extends RepresentationModel in order to add 'link' to Users object only in case where we use WebMvcLinkBuilder.
// But in this project we use ControllerLinkBuilder not WebMvcLinkBuilder.

//@Entity annotation is used to make Users class an entity for communicating with JPA database

// @Data annotation is used to automatically generate getters & setters
@Entity
@Data
@NoArgsConstructor

public class Users {
    public Users(int id, String name, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    //Declaring ID as a primary key-
    @Id
    @GeneratedValue
    private int id;

    @Size(min = 2, message = "Name should consist of atleast 2 characters")
    private String name;

    @Past(message = "Please give past dates")
    private Date dateOfBirth;

    @OneToMany(mappedBy ="users")
    private List<Post> posts;


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
