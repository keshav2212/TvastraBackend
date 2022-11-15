package com.tvastra.Price;

import javax.persistence.*;

@Embeddable
public class Price {
    private Long amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;


    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
