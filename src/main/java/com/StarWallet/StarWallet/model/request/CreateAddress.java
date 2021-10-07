package com.StarWallet.StarWallet.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAddress {
    private String streetName;

    private String streetNumber;

    private String zipCode;

    private String state;

    private String city;

}
