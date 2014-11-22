package be.haexnet.fusio.processor;

import be.haexnet.fusio.data.simple.OriginData;
import be.haexnet.fusio.data.simple.TargetData;
import org.junit.Test;

import java.math.BigDecimal;

import static org.fest.assertions.api.Assertions.assertThat;

public class FusioProcessorSimpleDataTest extends FusioProcessorTest<OriginData, TargetData> {

    @Test
    public void fusioReturnsTargetObject() throws Exception {
        final TargetData target = TargetData.empty();
        assertThat(processor.process(OriginData.empty(), target)).isSameAs(target);
    }

    @Test
    public void fusioReturnsTargetObjectNotUpdatedBecauseOfNullalbeValues() throws Exception {
        final TargetData target = TargetData.of("Business Owner", new BigDecimal("60378.79"), 46, "Sanitary");
        assertThat(processor.process(OriginData.empty(), target)).isSameAs(target);
    }

    @Test
    public void fusioReturnsTargetObjectWithFieldValuesOfOriginObject() throws Exception {
        final OriginData origin = OriginData.of("Martijn Haex", new BigDecimal("10378.79"), 24, "Java Consultant");
        final TargetData target = TargetData.empty();

        final TargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
    }

    @Test
    public void fusioReturnsTargetObjectWithOverwrittenFieldValuesOfOriginObject() throws Exception {
        final OriginData origin = OriginData.of("James Hetfield", new BigDecimal("100000000"), 51, "Musician");
        final TargetData target = TargetData.of("Sebastian Vettel", new BigDecimal("5000000"), 27, "Race pilot");

        final TargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
    }

    @Override
    protected void validateProcessing(final OriginData origin, final TargetData processedTarget) {
        assertThat(processedTarget.getName()).isNotNull().isEqualTo(origin.getName());
        assertThat(processedTarget.getSalary()).isNotNull().isEqualTo(origin.getSalary());
        assertThat(processedTarget.getAge()).isNotNull().isEqualTo(origin.getAge());
        assertThat(processedTarget.getOccupation()).isNotNull().isEqualTo(origin.getOccupation());
    }

}
