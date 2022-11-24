package com.tvastra.Price;

import javax.persistence.*;

@Embeddable
public class Price {

    private int amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Price() {
    }

    public Price(int amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
