package be.haexnet.fusio.data.simplechildorigindata;

public class TargetData {

    private String parentElement;
    private String childElement;

    private TargetData() {
    }

    private TargetData(final String parentElement, final String childElement) {
        this.parentElement = parentElement;
        this.childElement = childElement;
    }

    public static TargetData empty() {
        return new TargetData();
    }

    public static TargetData of(final String parentElement, final String childElement) {
        return new TargetData(parentElement, childElement);
    }

    public String getParentElement() {
        return parentElement;
    }

    public String getChildElement() {
        return childElement;
    }

}
