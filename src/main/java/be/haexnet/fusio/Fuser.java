package be.haexnet.fusio;

import be.haexnet.fusio.processor.FusioProcessor;

public class Fuser<O, T> {

    public T fusio(final O origin, final T target) {
        return new FusioProcessor<O, T>().process(origin, target);
    }

}
