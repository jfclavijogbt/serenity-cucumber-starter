package com.poc.cl.models;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "transit_card_number",
        "amount",
        "id",
        "created_at",
        "mach_id",
        "state",
        "success"
})
public class TransitCard implements Serializable {
    @JsonProperty("amount")
    @JsonAlias("balance")
    private Integer amount;
    @JsonProperty("id")
    private String id;
    @JsonProperty("created_at")
    @JsonAlias("date")
    private String createdAt;
    @JsonProperty("mach_id")
    private String machId;
    @JsonProperty("state")
    private String state;
    @JsonProperty("transit_card_number")
    private String transitCardNumber;
    @JsonProperty("success")
    private Boolean success;
    private static final long serialVersionUID = -4698985863530452916L;

    public TransitCard() {
    }

    public TransitCard(String transitCardNumber) {
        super();
        this.transitCardNumber = transitCardNumber;
    }

    public TransitCard(String transitCardNumber, int amount) {
        super();
        this.transitCardNumber = transitCardNumber;
        this.amount = amount;
    }

    @JsonProperty("amount")
    @JsonAlias("balance")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    @JsonAlias("balance")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("created_at")
    @JsonAlias("date")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("mach_id")
    public String getMachId() {
        return machId;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("transit_card_number")
    public String getTransitCardNumber() {
        return transitCardNumber;
    }

    @JsonProperty("transit_card_number")
    public void setTransitCardNumber(String transitCardNumber) {
        this.transitCardNumber = transitCardNumber;
    }

    @JsonProperty("success")
    public Boolean isSuccess() {
        return success;
    }

}
