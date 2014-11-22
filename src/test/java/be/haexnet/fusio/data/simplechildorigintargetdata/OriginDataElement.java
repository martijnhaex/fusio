package be.haexnet.fusio.data.simplechildorigintargetdata;

import be.haexnet.fusio.annotation.FusioField;

public class OriginDataElement {

    @FusioField
    private String street;
    @FusioField
    private String streetNumber;

    private OriginDataElement() {
    }

    private OriginDataElement(final String street, final String streetNumber) {
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public static OriginDataElement empty() {
        return new OriginDataElement();
    }

    public static OriginDataElement of(final String street, final String streetNumber) {
        return new OriginDataElement(street, streetNumber);
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

}
