package be.haexnet.fusio.processor;

import be.haexnet.fusio.data.SimpleOriginData;
import be.haexnet.fusio.data.SimpleTargetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FusioProcessorSimpleDataTest {

    FusioProcessor<SimpleOriginData, SimpleTargetData> fusioProcessor = new FusioProcessor<>();

    @Test
    public void fusioReturnsTargetObject() throws Exception {
        final SimpleTargetData target = SimpleTargetData.empty();
        assertThat(fusioProcessor.process(SimpleOriginData.empty(), target)).isSameAs(target);
    }

    @Test
    public void fusioReturnsTargetObjectWithFielValuesOfOriginObject() throws Exception {
        final SimpleOriginData origin = SimpleOriginData.of("Martijn Haex", new BigDecimal("10378.79"), 24, "Java Consultant");
        final SimpleTargetData target = SimpleTargetData.empty();

        final SimpleTargetData processedTarget = fusioProcessor.process(origin, target);
        verifyProcessing(origin, processedTarget);
    }

    @Test
    public void fusioReturnsTargetObjectWithOverwrittenFieldValuesOfOriginObject() throws Exception {
        final SimpleOriginData origin = SimpleOriginData.of("James Hetfield", new BigDecimal("100000000"), 51, "Musician");
        final SimpleTargetData target = SimpleTargetData.of("Sebastian Vettel", new BigDecimal("5000000"), 27, "Race pilot");

        final SimpleTargetData processedTarget = fusioProcessor.process(origin, target);
        verifyProcessing(origin, processedTarget);

    }

    private void verifyProcessing(final SimpleOriginData origin, final SimpleTargetData processedTarget) {
        assertThat(processedTarget.getName()).isNotNull().isEqualTo(origin.getName());
        assertThat(processedTarget.getSalary()).isNotNull().isEqualTo(origin.getSalary());
        assertThat(processedTarget.getAge()).isNotNull().isEqualTo(origin.getAge());
        assertThat(processedTarget.getOccupation()).isNotNull().isEqualTo(origin.getOccupation());
    }

}
