package com.StarWallet.StarWallet.service;

import com.StarWallet.StarWallet.model.entity.User;
import com.StarWallet.StarWallet.model.request.CreateUser;

import java.util.List;

public interface UserService {

    User findById(long id);
    List<User> getAllUsers();

    User createNewUser(CreateUser user) throws Exception;

}
