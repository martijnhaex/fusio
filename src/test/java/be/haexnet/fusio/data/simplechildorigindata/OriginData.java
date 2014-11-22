package be.haexnet.fusio.data.simplechildorigindata;

import be.haexnet.fusio.annotation.FusioEmbedded;
import be.haexnet.fusio.annotation.FusioField;

public class OriginData {

    @FusioField
    private String parentElement;
    @FusioEmbedded(decoupling = true)
    private OriginDataElement childElement;

    private OriginData() {
        this.childElement = OriginDataElement.empty();
    }

    private OriginData(final String parentElement, final OriginDataElement childElement) {
        this.parentElement = parentElement;
        this.childElement = childElement;
    }

    public static OriginData empty() {
        return new OriginData();
    }

    public static OriginData of(final String parentElement, final OriginDataElement childElement) {
        return new OriginData(parentElement, childElement);
    }

    public String getParentElement() {
        return parentElement;
    }

    public OriginDataElement getChildElement() {
        return childElement;
    }

}
