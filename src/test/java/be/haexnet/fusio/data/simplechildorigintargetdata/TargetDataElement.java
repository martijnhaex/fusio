package be.haexnet.fusio.data.simplechildorigintargetdata;

public class TargetDataElement {

    private String street;
    private String streetNumber;

    private TargetDataElement() {
    }

    private TargetDataElement(final String street, final String streetNumber) {
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public static TargetDataElement empty() {
        return new TargetDataElement();
    }

    public static TargetDataElement of(final String street, final String streetNumber) {
        return new TargetDataElement(street, streetNumber);
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

}
