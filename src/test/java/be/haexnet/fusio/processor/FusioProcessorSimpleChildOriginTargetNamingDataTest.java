package be.haexnet.fusio.processor;

import be.haexnet.fusio.data.simplechildorigintargetnaming.OriginData;
import be.haexnet.fusio.data.simplechildorigintargetnaming.OriginDataElement;
import be.haexnet.fusio.data.simplechildorigintargetnaming.TargetData;
import be.haexnet.fusio.data.simplechildorigintargetnaming.TargetDataElement;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class FusioProcessorSimpleChildOriginTargetNamingDataTest extends FusioProcessorTest<OriginData, TargetData> {

    @Test
    public void fusioReturnsTargetObject() throws Exception {
        final TargetData target = TargetData.empty();
        assertThat(processor.process(OriginData.empty(), target)).isSameAs(target);
    }

    @Test
    public void fusioReturnTargetObjectWithFieldValueOfOriginObjectHasCreatedEmbbedInTarget() throws Exception {
        final OriginData origin = OriginData.of("No Lifer", OriginDataElement.of("World of Warcraft", 1987432L));
        final TargetData target = TargetData.of("Has Lifer", null);

        final TargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
        validateChild(origin.getStatistics(), processedTarget.getGamingStatistics());
    }

    @Test
    public void fusioReturnsTargetObjectWithFieldValueOfOriginObjectExceptNullableElement() throws Exception {
        final OriginData origin = OriginData.of("Has Lifer", null);
        final TargetData target = TargetData.of("Noobish Lifer", TargetDataElement.of("Left behind", 3L));

        final TargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
        assertThat(processedTarget.getGamingStatistics()).isNotNull();
        assertThat(processedTarget.getGamingStatistics().getGame()).isNotNull().isEqualTo(target.getGamingStatistics().getGame());
        assertThat(processedTarget.getGamingStatistics().getHoursPlayed()).isNotNull().isEqualTo(target.getGamingStatistics().getHoursPlayed());
    }

    @Test
    public void fusioReturnsTargetObjectWithOverwrittenFieldValuesOfOriginObject() throws Exception {
        final OriginData origin = OriginData.of("Moekemedelij", OriginDataElement.of("Call of Duty", 183L));
        final TargetData target = TargetData.of("W3C", TargetDataElement.of("Medal of Honor", 89L));

        final TargetData processedTarget = processor.process(origin, target);
        validateProcessing(origin, processedTarget);
        validateChild(origin.getStatistics(), processedTarget.getGamingStatistics());
    }

    @Override
    protected void validateProcessing(final OriginData origin, final TargetData processedTarget) {
        assertThat(processedTarget.getName()).isNotNull().isEqualTo(origin.getName());
    }

    private void validateChild(final OriginDataElement origin, final TargetDataElement processedTarget) {
        assertThat(processedTarget.getGame()).isNotNull().isEqualTo(origin.getGame());
        assertThat(processedTarget.getHoursPlayed()).isNotNull().isEqualTo(origin.getHoursPlayed());
    }

}
