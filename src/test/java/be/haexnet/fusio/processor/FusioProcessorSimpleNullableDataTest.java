package be.haexnet.fusio.processor;

import be.haexnet.fusio.data.simplenullable.OriginData;
import be.haexnet.fusio.data.simplenullable.TargetData;
import org.junit.Test;

import java.math.BigDecimal;

import static org.fest.assertions.api.Assertions.assertThat;

public class FusioProcessorSimpleNullableDataTest extends FusioProcessorTest<OriginData, TargetData> {

    @Test
    public void fusioReturnsTargetObjectWithFieldValuesOfOriginObjectWhenNull() throws Exception {
        final OriginData origin = OriginData.empty();
        final TargetData target = TargetData.of(new BigDecimal("45.29"), "€");
        validateProcessing(origin, processor.process(origin, target));
    }

    @Override
    protected void validateProcessing(final OriginData origin, final TargetData processedTarget) {
        assertThat(processedTarget.getPostageAmount()).isNull();
        assertThat(processedTarget.getPostageCurrency()).isNull();
    }

}
