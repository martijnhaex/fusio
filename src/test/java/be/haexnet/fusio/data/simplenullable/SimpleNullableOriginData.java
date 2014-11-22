package be.haexnet.fusio.data.simplenullable;

import be.haexnet.fusio.annotation.FusioField;

import java.math.BigDecimal;

public class SimpleNullableOriginData {

    @FusioField(nullable = true)
    private BigDecimal postageAmount;
    @FusioField(nullable = true)
    private String postageCurrency;

    private SimpleNullableOriginData() {
    }

    private SimpleNullableOriginData(final BigDecimal postageAmount, final String postageCurrency) {
        this.postageAmount = postageAmount;
        this.postageCurrency = postageCurrency;
    }

    public static SimpleNullableOriginData empty() {
        return new SimpleNullableOriginData();
    }

    public static SimpleNullableOriginData of(final BigDecimal postageAmount, final String postageCurrency) {
        return new SimpleNullableOriginData(postageAmount, postageCurrency);
    }

    public BigDecimal getPostageAmount() {
        return postageAmount;
    }

    public String getPostageCurrency() {
        return postageCurrency;
    }

}
