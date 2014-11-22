package be.haexnet.fusio.data.simplenullable;

import java.math.BigDecimal;

public class SimpleNullableTargetData {

    private BigDecimal postageAmount;
    private String postageCurrency;

    private SimpleNullableTargetData() {
    }

    private SimpleNullableTargetData(final BigDecimal postageAmount, final String postageCurrency) {
        this.postageAmount = postageAmount;
        this.postageCurrency = postageCurrency;
    }

    public static SimpleNullableTargetData empty() {
        return new SimpleNullableTargetData();
    }

    public static SimpleNullableTargetData of(final BigDecimal postageAmount, final String postageCurrency) {
        return new SimpleNullableTargetData(postageAmount, postageCurrency);
    }

    public BigDecimal getPostageAmount() {
        return postageAmount;
    }

    public String getPostageCurrency() {
        return postageCurrency;
    }

}
