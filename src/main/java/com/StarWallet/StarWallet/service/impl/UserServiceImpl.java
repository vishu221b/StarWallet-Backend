package com.StarWallet.StarWallet.service.impl;

import com.StarWallet.StarWallet.constants.ErrorConstants;
import com.StarWallet.StarWallet.model.entity.Address;
import com.StarWallet.StarWallet.model.entity.User;
import com.StarWallet.StarWallet.model.exceptions.StarWalletResourceNotFoundException;
import com.StarWallet.StarWallet.model.request.CreateAddress;
import com.StarWallet.StarWallet.model.request.CreateUser;
import com.StarWallet.StarWallet.repository.AddressRepository;
import com.StarWallet.StarWallet.repository.UserRepository;
import com.StarWallet.StarWallet.service.AddressService;
import com.StarWallet.StarWallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    AddressService addressService;

    @Autowired
    UserRepository userRepository;

    public void deleteUser(Long userId) throws Exception{
        User user = userRepository.findById(userId).orElse(null);
        if(null == user)
            throw new StarWalletResourceNotFoundException(ErrorConstants.USER_NOT_FOUND_ERROR, new Date().getTime());
        user.setIsActive(Boolean.FALSE);
        userRepository.save(user);
    }

    @Override
    public User findById(long id) {
        User user = userRepository.findById(id).orElse(null);
        if(null!=user && user.getIsActive().equals(Boolean.TRUE)){
            return user;
        }
        if(null==user || user.getIsActive().equals(Boolean.FALSE)){
            throw new StarWalletResourceNotFoundException(ErrorConstants.USER_NOT_FOUND_ERROR, System.currentTimeMillis());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .filter(u -> u.getIsActive().equals(Boolean.TRUE))
                .collect(Collectors.toList());
    }

    @Override
    public User createNewUser(CreateUser user) throws Exception{
        User user1 = new User();
        user1.setUserType(user.getUserType());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setMobileNumber(user.getMobile());
        user1.setEmailAddress(user.getEmail());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        user1.setBirthDate(sdf.parse(user.getDateOfBirth()));
        user1 = userRepository.save(user1);
        addressService.createNewAddress(user.getAddress(), user1);
        return user1;
    }

}
