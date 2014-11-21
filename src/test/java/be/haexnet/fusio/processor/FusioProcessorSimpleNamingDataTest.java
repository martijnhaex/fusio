package be.haexnet.fusio.processor;

import be.haexnet.fusio.data.simplenaming.SimpleNamingOriginData;
import be.haexnet.fusio.data.simplenaming.SimpleNamingTargetData;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class FusioProcessorSimpleNamingDataTest extends FusioProcessorTest<SimpleNamingOriginData, SimpleNamingTargetData> {

    @Test
    public void fusioReturnsTargetObject() throws Exception {
        final SimpleNamingTargetData target = SimpleNamingTargetData.empty();
        assertThat(processor.process(SimpleNamingOriginData.empty(), target)).isSameAs(target);
    }

    @Test
    public void fusioReturnsTargetObjectWithFieldValuesOfOriginObject() throws Exception {
        final SimpleNamingOriginData origin = SimpleNamingOriginData.of("EM", "S");
        final SimpleNamingTargetData target = SimpleNamingTargetData.empty();

        final SimpleNamingTargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
    }

    @Test
    public void fusioReturnsTargetObjectWithOverwrittenFieldValuesOfOriginObject() throws Exception {
        final SimpleNamingOriginData origin = SimpleNamingOriginData.of("CN", "200");
        final SimpleNamingTargetData target = SimpleNamingTargetData.of("RV", "SHP");

        final SimpleNamingTargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);

    }

    @Override
    protected void validateProcessing(final SimpleNamingOriginData origin, final SimpleNamingTargetData processedTarget) {
        assertThat(processedTarget.getMailSubClass()).isNotNull().isEqualTo(origin.getItemSubClass());
        assertThat(processedTarget.getCategory()).isNotNull().isEqualTo(origin.getItemCategory());
    }

}
