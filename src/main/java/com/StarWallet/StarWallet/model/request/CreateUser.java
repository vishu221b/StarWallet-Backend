package com.StarWallet.StarWallet.model.request;

import com.StarWallet.StarWallet.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUser {

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private UserType userType;

    private CreateAddress address;

    private String mobile;

    private String email;

}
