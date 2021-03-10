package com.rainbowforest.userservice.controller;

import com.rainbowforest.userservice.entity.Buyer;
import com.rainbowforest.userservice.http.header.HeaderGenerator;
import com.rainbowforest.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private HeaderGenerator headerGenerator;
    
    @PostMapping(value = "/registration")
    public ResponseEntity<Buyer> addUser(@RequestBody Buyer user, HttpServletRequest request){
    	if(user != null)
    		try {
    			userService.saveUser(user);
    			return new ResponseEntity<Buyer>(
    					user,
    					headerGenerator.getHeadersForSuccessPostMethod(request, user.getBuyerId()),
    					HttpStatus.CREATED);
    		}catch (Exception e) {
    			e.printStackTrace();
    			return new ResponseEntity<Buyer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<Buyer>(HttpStatus.BAD_REQUEST);
    }
}