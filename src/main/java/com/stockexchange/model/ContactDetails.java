package com.stockexchange.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class ContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer customerId;

    @NotEmpty
    private String telephoneNumber;

    @NotEmpty
    private String email;

    public ContactDetails(Integer customerId, String telephoneNumber, String email) {
        this.customerId = customerId;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
    }
}
