package com.StarWallet.StarWallet.service.impl;

import com.StarWallet.StarWallet.model.entity.Address;
import com.StarWallet.StarWallet.model.entity.User;
import com.StarWallet.StarWallet.model.request.CreateAddress;
import com.StarWallet.StarWallet.repository.AddressRepository;
import com.StarWallet.StarWallet.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address createNewAddress(CreateAddress createAddress, User user) {
        Address address = new Address();
        address.setCity(createAddress.getCity());
        address.setState(createAddress.getState());
        address.setStreetName(createAddress.getStreetName());
        address.setStreetNumber(createAddress.getStreetNumber());
        address.setZipCode(createAddress.getZipCode());
        address.setUser(user);
        return addressRepository.save(address);
    }
}
