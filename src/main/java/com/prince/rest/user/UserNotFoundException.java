package com.prince.rest.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)

/*  Here we set ResponseStatus by other mode.
    For this do check 'handlingexceptionwithspecificstructure' package.
 */

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
