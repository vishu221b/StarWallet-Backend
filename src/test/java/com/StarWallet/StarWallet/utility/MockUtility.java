package com.StarWallet.StarWallet.utility;

import com.StarWallet.StarWallet.enums.UserType;
import com.StarWallet.StarWallet.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class MockUtility {

    public static Optional<User> getMockOptionalUser(){
        Date common = new Date();
        Optional<User> mockUser = Optional.of(new User(
                1L,
                "Testers",
                "Besters",
                UserType.CUSTOMER,
                common,
                "xxccssxxdd",
                "vd@ss.ss",
                new ArrayList<>(),
                new ArrayList<>()));
        mockUser.get().setIsActive(Boolean.TRUE);
        mockUser.get().setCreatedAt(common);
        mockUser.get().setLastUpdatedAt(common);
        return mockUser;
    }
}
