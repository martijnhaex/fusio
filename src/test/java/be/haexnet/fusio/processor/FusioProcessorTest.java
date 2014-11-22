package be.haexnet.fusio.processor;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class FusioProcessorTest<O, T> {

    protected FusioProcessor<O, T> processor = new FusioProcessor<>();

    protected abstract void validateProcessing(O origin, T processedTarget);

}
