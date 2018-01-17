package com.stockexchange.model;

import com.stockexchange.enums.EnumUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
public class Share implements PossessId, PossessArchivedStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer priceWhenBought;

    @NotNull
    private Integer sharesAmount;

    @NotNull
    private OccupiedSide occupiedSide;

    @NotNull
    private boolean archived;

    public Share() {}

    public Share(Integer priceWhenBought, Integer sharesAmount, OccupiedSide occupiedSide) {
        this.priceWhenBought = priceWhenBought;
        this.sharesAmount = sharesAmount;
        this.occupiedSide = occupiedSide;
    }

    public enum OccupiedSide {
        BUY,
        SELL;

        private static EnumUtils<OccupiedSide> enumUtils = new EnumUtils<>(OccupiedSide.class);

        public static OccupiedSide getByNameAutoUpperCase(String name) {
            String upperCasedName = name.toUpperCase();
            return enumUtils.getValue(upperCasedName);
        }
    }

    private void generateDifference(BigDecimal actualPrice) {

    }

    @Override
    public Integer getId( ) {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPriceWhenBought( ) {
        return priceWhenBought;
    }

    public void setPriceWhenBought(Integer priceWhenBought) {
        this.priceWhenBought = priceWhenBought;
    }

    public Integer getSharesAmount( ) {
        return sharesAmount;
    }

    public void setSharesAmount(Integer sharesAmount) {
        this.sharesAmount = sharesAmount;
    }

    public OccupiedSide getOccupiedSide( ) {
        return occupiedSide;
    }

    public void setOccupiedSide(OccupiedSide occupiedSide) {
        this.occupiedSide = occupiedSide;
    }

    public boolean isArchived( ) {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}