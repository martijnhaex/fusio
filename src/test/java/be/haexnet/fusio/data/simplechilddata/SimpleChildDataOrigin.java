package be.haexnet.fusio.data.simplechilddata;

import be.haexnet.fusio.annotation.FusioEmbedded;
import be.haexnet.fusio.annotation.FusioField;

public class SimpleChildDataOrigin {

    @FusioField
    private String parentElement;
    @FusioEmbedded
    private SimpleChildDataElement childElement;

    private SimpleChildDataOrigin() {
        this.childElement = SimpleChildDataElement.empty();
    }

    private SimpleChildDataOrigin(final String parentElement, final SimpleChildDataElement childElement) {
        this.parentElement = parentElement;
        this.childElement = childElement;
    }

    public static SimpleChildDataOrigin empty() {
        return new SimpleChildDataOrigin();
    }

    public static SimpleChildDataOrigin of(final String parentElement, final SimpleChildDataElement childElement) {
        return new SimpleChildDataOrigin(parentElement, childElement);
    }

    public String getParentElement() {
        return parentElement;
    }

    public SimpleChildDataElement getChildElement() {
        return childElement;
    }

}
