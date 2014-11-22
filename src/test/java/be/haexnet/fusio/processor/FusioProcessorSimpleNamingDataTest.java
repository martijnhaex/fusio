package be.haexnet.fusio.processor;

import be.haexnet.fusio.data.simplenaming.OriginData;
import be.haexnet.fusio.data.simplenaming.TargetData;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class FusioProcessorSimpleNamingDataTest extends FusioProcessorTest<OriginData, TargetData> {

    @Test
    public void fusioReturnsTargetObject() throws Exception {
        final TargetData target = TargetData.empty();
        assertThat(processor.process(OriginData.empty(), target)).isSameAs(target);
    }

    @Test
    public void fusioReturnsTargetObjectWithFieldValuesOfOriginObject() throws Exception {
        final OriginData origin = OriginData.of("EM", "S");
        final TargetData target = TargetData.empty();

        final TargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
    }

    @Test
    public void fusioReturnsTargetObjectWithOverwrittenFieldValuesOfOriginObject() throws Exception {
        final OriginData origin = OriginData.of("CN", "200");
        final TargetData target = TargetData.of("RV", "SHP");

        final TargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);

    }

    @Override
    protected void validateProcessing(final OriginData origin, final TargetData processedTarget) {
        assertThat(processedTarget.getMailSubClass()).isNotNull().isEqualTo(origin.getItemSubClass());
        assertThat(processedTarget.getCategory()).isNotNull().isEqualTo(origin.getItemCategory());
    }

}
