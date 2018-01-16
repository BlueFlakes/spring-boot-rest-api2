package com.stockexchange.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private ContactDetails contactDetails;

    public Customer(String firstName, String lastName, ContactDetails contactDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactDetails = contactDetails;
    }
}
