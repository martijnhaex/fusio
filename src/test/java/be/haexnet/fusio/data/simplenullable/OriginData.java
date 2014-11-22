package be.haexnet.fusio.data.simplenullable;

import be.haexnet.fusio.annotation.FusioField;

import java.math.BigDecimal;

public class OriginData {

    @FusioField(nullable = true)
    private BigDecimal postageAmount;
    @FusioField(nullable = true)
    private String postageCurrency;

    private OriginData() {
    }

    private OriginData(final BigDecimal postageAmount, final String postageCurrency) {
        this.postageAmount = postageAmount;
        this.postageCurrency = postageCurrency;
    }

    public static OriginData empty() {
        return new OriginData();
    }

    public static OriginData of(final BigDecimal postageAmount, final String postageCurrency) {
        return new OriginData(postageAmount, postageCurrency);
    }

    public BigDecimal getPostageAmount() {
        return postageAmount;
    }

    public String getPostageCurrency() {
        return postageCurrency;
    }

}
