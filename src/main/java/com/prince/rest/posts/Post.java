package com.prince.rest.posts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prince.rest.user.Users;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class Post {

    @javax.persistence.Id
    @GeneratedValue
    private int Id;

    @Size(min = 5, message = "Description must contain atleast 5 characters")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Users users;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Post{" +
                "Id=" + Id +
                ", description='" + description + '\'' +
                '}';
    }
}
