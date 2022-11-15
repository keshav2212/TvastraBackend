package com.tvastra.Price;

public enum Currency {
    RUPEE(1),
    DOLLAR(75),
    EURO(84);

    private final int conversionFactor;
    Currency(int conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public float getEquivalent(Long amount, Currency type){
        return (this.conversionFactor/ (float)type.conversionFactor) * amount;
    }

    public int getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "conversionFactor=" + conversionFactor +
                '}';
    }
}
