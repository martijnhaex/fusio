package be.haexnet.fusio.processor;

import be.haexnet.fusio.data.simplechildorigin.OriginData;
import be.haexnet.fusio.data.simplechildorigin.OriginDataElement;
import be.haexnet.fusio.data.simplechildorigin.TargetData;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class FusioProcessorSimpleChildDataTest extends FusioProcessorTest<OriginData, TargetData> {

    @Test
    public void fusioReturnsTargetObject() throws Exception {
        final TargetData target = TargetData.empty();
        assertThat(processor.process(OriginData.empty(), target)).isSameAs(target);
    }

    @Test
    public void fusioReturnsTargetObjectWithFieldValuesOfOriginObject() throws Exception {
        final OriginData origin = OriginData.of("Parent of the element", OriginDataElement.of("Child of the element"));
        final TargetData target = TargetData.empty();

        final TargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
    }

    @Test
    public void fusioReturnsTargetObjectWithFieldValueOfOriginObjectExceptNullableElement() throws Exception {
        final OriginData origin = OriginData.of("Parent of the element", null);
        final TargetData target = TargetData.of("Parent", "Child");

        final TargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
        assertThat(processedTarget.getChildElement()).isNotNull().isEqualTo(target.getChildElement());
    }

    @Test
    public void fusioReturnsTargetObjectWithOverwrittenFieldValuesOfOriginObject() throws Exception {
        final OriginData origin = OriginData.of("Darth Vader", OriginDataElement.of("Luke Skywalker"));
        final TargetData target = TargetData.of("Kill Bill vol.1", "Kill BIll vol.2");

        final TargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
    }

    @Override
    protected void validateProcessing(final OriginData origin, final TargetData processedTarget) {
        assertThat(processedTarget.getParentElement()).isNotNull().isEqualTo(origin.getParentElement());

        final OriginDataElement childElement = origin.getChildElement();
        if (childElement != null) {
            assertThat(processedTarget.getChildElement()).isNotNull().isEqualTo(childElement.getChildElement());
        }
    }

}
