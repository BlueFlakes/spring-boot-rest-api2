package com.stockexchange.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StockExchange implements PossessId, PossessArchivedStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockExchange_id")
    private Integer id;

    @NotEmpty
    private String name;

    @NotNull
    private boolean archived;

    @JsonIgnoreProperties("stockExchange")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stockExchange", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commodity> commodities = new ArrayList<>();

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

    public List<Commodity> getCommodities( ) {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }

    public String getName( ) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
