package com.stockexchange.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class Customer implements PossessId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ContactDetails contactDetails;

    public Customer() {}

    public Customer(String firstName, String lastName, ContactDetails contactDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactDetails = contactDetails;
    }
}
