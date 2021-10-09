package com.StarWallet.StarWallet.controller;

import com.StarWallet.StarWallet.constants.ErrorConstants;
import com.StarWallet.StarWallet.constants.RestConstants;
import com.StarWallet.StarWallet.constants.UserConstants;
import com.StarWallet.StarWallet.enums.UserType;
import com.StarWallet.StarWallet.model.entity.User;
import com.StarWallet.StarWallet.model.exceptions.StarWalletInternalServerErrorException;
import com.StarWallet.StarWallet.model.exceptions.StarWalletResourceAlreadyExistsException;
import com.StarWallet.StarWallet.model.exceptions.StarWalletResourceNotFoundException;
import com.StarWallet.StarWallet.model.request.CreateUser;
import com.StarWallet.StarWallet.model.response.GenericResponseDTO;
import com.StarWallet.StarWallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/{userType}")
    public User createUser(@RequestBody CreateUser createUser, @PathVariable int userType) {
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
        }catch (Exception e){
            e.printStackTrace();
            throw new StarWalletInternalServerErrorException(e.getMessage(), new Date().getTime());
        }
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.FOUND);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<GenericResponseDTO> deleteUserById(@PathVariable Long userId){
        GenericResponseDTO responseDTO = new GenericResponseDTO();
        try {
            Boolean userStatus = userService.deleteUserWithId(userId);
            if(!userStatus){
                responseDTO.setStatus(RestConstants.SUCCESS);
                responseDTO.setMessage(UserConstants.USER_DELETED_SUCCESSFULLY);
            }else{
                responseDTO.setStatus(RestConstants.FAILURE);
                responseDTO.setMessage(UserConstants.COULD_NOT_DELETE_USER);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new StarWalletInternalServerErrorException(e.getMessage(), new Date().getTime());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);
    }
}
