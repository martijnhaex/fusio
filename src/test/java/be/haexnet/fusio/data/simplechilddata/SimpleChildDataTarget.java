package be.haexnet.fusio.data.simplechilddata;

public class SimpleChildDataTarget {

    private String parentElement;
    private String childElement;

    private SimpleChildDataTarget() {
    }

    private SimpleChildDataTarget(final String parentElement, final String childElement) {
        this.parentElement = parentElement;
        this.childElement = childElement;
    }

    public static SimpleChildDataTarget empty() {
        return new SimpleChildDataTarget();
    }

    public static SimpleChildDataTarget of(final String parentElement, final String childElement) {
        return new SimpleChildDataTarget(parentElement, childElement);
    }

    public String getParentElement() {
        return parentElement;
    }

    public String getChildElement() {
        return childElement;
    }

}
