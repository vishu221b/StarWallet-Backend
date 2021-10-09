package com.StarWallet.StarWallet.model.entity;

import com.StarWallet.StarWallet.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
@Table(name = "star_wallet_user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType ;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "mobile_number", unique = true)
    private String mobileNumber;

    @Column(name = "email")
    private String emailAddress;

    @JsonIgnore
    @OneToMany(targetEntity = Address.class, mappedBy = "user")
    private List<Address> addresses;

    @OneToMany(targetEntity = Wallet.class, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Wallet> wallets;

}

