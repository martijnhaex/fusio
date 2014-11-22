package be.haexnet.fusio.processor;

import be.haexnet.fusio.data.simplechilddata.SimpleChildDataElement;
import be.haexnet.fusio.data.simplechilddata.SimpleChildDataOrigin;
import be.haexnet.fusio.data.simplechilddata.SimpleChildDataTarget;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class FusioProcessorSimpleChildDataTest extends FusioProcessorTest<SimpleChildDataOrigin, SimpleChildDataTarget> {

    @Test
    public void fusioReturnsTargetObject() throws Exception {
        final SimpleChildDataTarget target = SimpleChildDataTarget.empty();
        assertThat(processor.process(SimpleChildDataOrigin.empty(), target)).isSameAs(target);
    }

    @Test
    public void fusioReturnsTargetObjectWithFieldValuesOfOriginObject() throws Exception {
        final SimpleChildDataOrigin origin = SimpleChildDataOrigin.of("Parent of the element", SimpleChildDataElement.of("Child of the element"));
        final SimpleChildDataTarget target = SimpleChildDataTarget.empty();

        final SimpleChildDataTarget processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
    }

    @Test
    public void fusioReturnsTargetObjectWithFieldValueOfOriginObjectExceptNullableElement() throws Exception {
        final SimpleChildDataOrigin origin = SimpleChildDataOrigin.of("Parent of the element", null);
        final SimpleChildDataTarget target = SimpleChildDataTarget.of("Parent", "Child");

        final SimpleChildDataTarget processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
        assertThat(processedTarget.getChildElement()).isNotNull().isEqualTo(target.getChildElement());
    }

    @Test
    public void fusioReturnsTargetObjectWithOverwrittenFieldValuesOfOriginObject() throws Exception {
        final SimpleChildDataOrigin origin = SimpleChildDataOrigin.of("Darth Vader", SimpleChildDataElement.of("Luke Skywalker"));
        final SimpleChildDataTarget target = SimpleChildDataTarget.of("Kill Bill vol.1", "Kill BIll vol.2");

        final SimpleChildDataTarget processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
    }

    @Override
    protected void validateProcessing(final SimpleChildDataOrigin origin, final SimpleChildDataTarget processedTarget) {
        assertThat(processedTarget.getParentElement()).isNotNull().isEqualTo(origin.getParentElement());

        final SimpleChildDataElement childElement = origin.getChildElement();
        if (childElement != null) {
            assertThat(processedTarget.getChildElement()).isNotNull().isEqualTo(childElement.getChildElement());
        }
    }

}
