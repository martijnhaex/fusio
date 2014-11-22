package be.haexnet.fusio.processor;

import be.haexnet.fusio.data.simplechilorigintargetnullable.OriginData;
import be.haexnet.fusio.data.simplechilorigintargetnullable.TargetData;
import be.haexnet.fusio.data.simplechilorigintargetnullable.TargetDataElement;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class FusioProcessorSimplChildOriginTargetNullableDataTest extends FusioProcessorTest<OriginData, TargetData> {

    @Test
    public void fusioReturnsTargetObjectWithFieldValuesOfOriginObjectWhenNull() throws Exception {
        final OriginData origin = OriginData.empty();
        final TargetData target = TargetData.of("Martijn Haex", TargetDataElement.of("Java Consultant", "Writing (web-)applications"));

        final TargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
        assertThat(processedTarget.getName()).isEqualTo(target.getName());
    }

    @Override
    protected void validateProcessing(final OriginData origin, final TargetData processedTarget) {
        assertThat(processedTarget.getName()).isNotNull();
        assertThat(processedTarget.getJob()).isNull();
    }

}
