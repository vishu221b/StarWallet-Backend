package com.StarWallet.StarWallet.service;

import com.StarWallet.StarWallet.model.entity.Address;
import com.StarWallet.StarWallet.model.entity.User;
import com.StarWallet.StarWallet.model.request.CreateAddress;

public interface AddressService {
    Address createNewAddress(CreateAddress createAddress, User user);
}
