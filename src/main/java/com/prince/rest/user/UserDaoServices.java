package com.prince.rest.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoServices {

    private static List<Users> usersList = new ArrayList<>();
    private static int countUser= 3;
     static {
        Users user1 = new Users(1, "Arjav", new Date());
        Users user2 = new Users(2, "Prabhat", new Date());
        Users user3 = new Users(3, "Chirag", new Date());
        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);
    }

    //Find All Users-


    public List<Users> findAll() {
        return usersList;
    }

    // Save New User-

    public Users save(Users myUser){
        if(myUser.getId()==0){
            myUser.setId(++countUser);
        }
         usersList.add(myUser);
         return myUser;
    }

    // Find Specific User with passing an ID-

    public Users findOne(int id){
        for (Users user : usersList){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }

    public Users deleteById(int id){
        Iterator<Users> iterator = usersList.iterator();
        while (iterator.hasNext()){
            Users user = iterator.next();
            if(user.getId()==id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
