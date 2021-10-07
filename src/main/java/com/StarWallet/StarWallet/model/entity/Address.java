package com.StarWallet.StarWallet.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "street_name")
    private String streetName;

    private String city;

    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

}

