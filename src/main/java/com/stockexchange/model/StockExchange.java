package com.stockexchange.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class StockExchange implements PossessId, PossessArchivedStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private boolean archived;

    @Override
    public Integer getId( ) {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isArchived( ) {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
