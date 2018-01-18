package com.stockexchange.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Commodity implements PossessId, PossessArchivedStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean archived;
    private String commodityMark;
    private BigDecimal value;

    @JsonIgnoreProperties("commodities")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stockExchange_id")
    private StockExchange stockExchange;

    @Override
    public Integer getId( ) {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommodityMark( ) {
        return commodityMark;
    }

    public void setCommodityMark(String commodityMark) {
        this.commodityMark = commodityMark;
    }

    public BigDecimal getValue( ) {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public StockExchange getStockExchange( ) {
        return stockExchange;
    }

    public void setStockExchange(StockExchange stockExchange) {
        this.stockExchange = stockExchange;
    }

    @Override
    public boolean isArchived( ) {
        return archived;
    }

    @Override
    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
