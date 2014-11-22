package be.haexnet.fusio.data.simplechilorigintargetnullable;

import be.haexnet.fusio.annotation.FusioField;

public class OriginDataElement {

    @FusioField
    private String name;
    @FusioField
    private String description;

    private OriginDataElement() {
    }

    private OriginDataElement(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public static OriginDataElement empty() {
        return new OriginDataElement();
    }

    public static OriginDataElement of(final String name, final String description) {
        return new OriginDataElement(name, description);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
