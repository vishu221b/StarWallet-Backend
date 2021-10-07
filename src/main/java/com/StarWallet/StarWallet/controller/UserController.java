package com.StarWallet.StarWallet.controller;

import com.StarWallet.StarWallet.constants.ErrorConstants;
import com.StarWallet.StarWallet.enums.UserType;
import com.StarWallet.StarWallet.model.entity.User;
import com.StarWallet.StarWallet.model.exceptions.StarWalletResourceAlreadyExistsException;
import com.StarWallet.StarWallet.model.exceptions.StarWalletResourceNotFoundException;
import com.StarWallet.StarWallet.model.request.CreateUser;
import com.StarWallet.StarWallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("{userType}/create")
    public User createUser(@RequestBody CreateUser createUser, @PathVariable int userType) throws Exception {
        if(null!=UserType.getById(userType)){
            createUser.setUserType(UserType.getById(userType));
        }else{
            throw new StarWalletResourceNotFoundException(ErrorConstants.USER_TYPE_NOT_FOUND_ERROR, new Date().getTime());
        }
        try{
            return userService.createNewUser(createUser);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            throw new StarWalletResourceAlreadyExistsException(ErrorConstants.USER_ALREADY_EXISTS_ERROR, new Date().getTime());
        }
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
