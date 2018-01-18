package com.stockexchange.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Customer implements PossessId, PossessArchivedStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private boolean archived;

    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private ContactDetails contactDetails;

    public Customer() {}

    @Override
    public Integer getId( ) {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName( ) {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName( ) {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isArchived( ) {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public ContactDetails getContactDetails( ) {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
}
