package be.haexnet.fusio.data.simplechildorigin;

import be.haexnet.fusio.annotation.FusioField;

public class OriginDataElement {

    @FusioField
    private String childElement;

    private OriginDataElement() {
    }

    private OriginDataElement(final String childElement) {
        this.childElement = childElement;
    }

    public static OriginDataElement empty() {
        return new OriginDataElement();
    }

    public static OriginDataElement of(final String childElement) {
        return new OriginDataElement(childElement);
    }

    public String getChildElement() {
        return childElement;
    }

}
