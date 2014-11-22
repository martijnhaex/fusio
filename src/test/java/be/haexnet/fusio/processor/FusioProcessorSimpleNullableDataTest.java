package be.haexnet.fusio.processor;

import be.haexnet.fusio.data.simplenullable.SimpleNullableOriginData;
import be.haexnet.fusio.data.simplenullable.SimpleNullableTargetData;
import org.junit.Test;

import java.math.BigDecimal;

import static org.fest.assertions.api.Assertions.assertThat;

public class FusioProcessorSimpleNullableDataTest extends FusioProcessorTest<SimpleNullableOriginData, SimpleNullableTargetData> {

    @Test
    public void fusioReturnsTargetObjectWithFieldValuesOfOriginObjectWhenNull() throws Exception {
        final SimpleNullableOriginData origin = SimpleNullableOriginData.empty();
        final SimpleNullableTargetData target = SimpleNullableTargetData.of(new BigDecimal("45.29"), "€");
        validateProcessing(origin, processor.process(origin, target));
    }

    @Override
    protected void validateProcessing(final SimpleNullableOriginData origin, final SimpleNullableTargetData processedTarget) {
        assertThat(processedTarget.getPostageAmount()).isNull();
        assertThat(processedTarget.getPostageCurrency()).isNull();
    }

}
