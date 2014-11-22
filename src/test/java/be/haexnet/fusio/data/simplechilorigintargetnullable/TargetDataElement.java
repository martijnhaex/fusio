package be.haexnet.fusio.data.simplechilorigintargetnullable;

public class TargetDataElement {

    private String name;
    private String description;

    private TargetDataElement() {
    }

    private TargetDataElement(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public static TargetDataElement empty() {
        return new TargetDataElement();
    }

    public static TargetDataElement of(final String name, final String description) {
        return new TargetDataElement(name, description);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
