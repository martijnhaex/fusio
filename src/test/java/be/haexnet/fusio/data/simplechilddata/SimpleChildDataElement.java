package be.haexnet.fusio.data.simplechilddata;

import be.haexnet.fusio.annotation.FusioField;

public class SimpleChildDataElement {

    @FusioField
    private String childElement;

    private SimpleChildDataElement() {
    }

    private SimpleChildDataElement(final String childElement) {
        this.childElement = childElement;
    }

    public static SimpleChildDataElement empty() {
        return new SimpleChildDataElement();
    }

    public static SimpleChildDataElement of(final String childElement) {
        return new SimpleChildDataElement(childElement);
    }

    public String getChildElement() {
        return childElement;
    }

}
