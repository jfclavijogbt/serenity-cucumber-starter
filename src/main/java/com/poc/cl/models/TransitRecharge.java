package com.poc.cl.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransitRecharge implements Serializable {
    @JsonProperty("transit_recharge")
    @JsonAlias("transit_card")
    private TransitCard transitCard;
    private static final long serialVersionUID = -602595956485402318L;

    public TransitRecharge() {
    }

    public TransitRecharge(TransitCard transitCard) {
        super();
        this.transitCard = transitCard;
    }

    @JsonProperty("transit_recharge")
    @JsonAlias("transit_card")
    public TransitCard getTransitRecharge() {
        return transitCard;
    }

    @JsonProperty("transit_recharge")
    @JsonAlias("transit_card")
    public void setTransitRecharge(TransitCard transitCard) {
        this.transitCard = transitCard;
    }
}
