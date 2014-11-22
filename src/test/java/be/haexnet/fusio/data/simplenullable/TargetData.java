package be.haexnet.fusio.data.simplenullable;

import java.math.BigDecimal;

public class TargetData {

    private BigDecimal postageAmount;
    private String postageCurrency;

    private TargetData() {
    }

    private TargetData(final BigDecimal postageAmount, final String postageCurrency) {
        this.postageAmount = postageAmount;
        this.postageCurrency = postageCurrency;
    }

    public static TargetData empty() {
        return new TargetData();
    }

    public static TargetData of(final BigDecimal postageAmount, final String postageCurrency) {
        return new TargetData(postageAmount, postageCurrency);
    }

    public BigDecimal getPostageAmount() {
        return postageAmount;
    }

    public String getPostageCurrency() {
        return postageCurrency;
    }

}
