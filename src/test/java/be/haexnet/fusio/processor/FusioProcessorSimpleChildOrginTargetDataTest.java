package be.haexnet.fusio.processor;

import be.haexnet.fusio.data.simplechildorigintarget.OriginData;
import be.haexnet.fusio.data.simplechildorigintarget.OriginDataElement;
import be.haexnet.fusio.data.simplechildorigintarget.TargetData;
import be.haexnet.fusio.data.simplechildorigintarget.TargetDataElement;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class FusioProcessorSimpleChildOrginTargetDataTest extends FusioProcessorTest<OriginData, TargetData> {

    @Test
    public void fusioReturnsTargetObject() throws Exception {
        final TargetData target = TargetData.empty();
        assertThat(processor.process(OriginData.empty(), target)).isSameAs(target);
    }

    @Test
    public void fusioReturnTargetObjectWithFieldValueOfOriginObjectHasCreatedEmbbedInTarget() throws Exception {
        final OriginData origin = OriginData.of("Origin", OriginDataElement.of("OriginStreet", "35"));
        final TargetData target = TargetData.of("Target", null);

        final TargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
        validateChild(origin.getAddress(), processedTarget.getAddress());
    }

    @Test
    public void fusioReturnsTargetObjectWithFieldValueOfOriginObjectExceptNullableElement() throws Exception {
        final OriginData origin = OriginData.of("Parent of the element", null);
        final TargetData target = TargetData.of("Target", TargetDataElement.of("TargetStreet", "1"));

        final TargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
        assertThat(processedTarget.getAddress()).isNotNull();
        assertThat(processedTarget.getAddress().getStreet()).isNotNull().isEqualTo(target.getAddress().getStreet());
        assertThat(processedTarget.getAddress().getStreetNumber()).isNotNull().isEqualTo(target.getAddress().getStreetNumber());
    }

    @Test
    public void fusioReturnsTargetObjectWithOverwrittenFieldValuesOfOriginObject() throws Exception {
        final OriginData origin = OriginData.of("Dummy Person", OriginDataElement.of("OriginStreet", "198 A3"));
        final TargetData target = TargetData.of("Test Person", TargetDataElement.of("TestStreet", "35a"));

        final TargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
        validateChild(origin.getAddress(), processedTarget.getAddress());
    }

    @Override
    protected void validateProcessing(final OriginData origin, final TargetData processedTarget) {
        assertThat(processedTarget.getName()).isNotNull().isEqualTo(origin.getName());
    }

    private void validateChild(final OriginDataElement origin, final TargetDataElement processedTarget) {
        assertThat(processedTarget.getStreet()).isNotNull().isEqualTo(origin.getStreet());
        assertThat(processedTarget.getStreetNumber()).isNotNull().isEqualTo(origin.getStreetNumber());
    }

}
