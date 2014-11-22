package be.haexnet.fusio.data.simplechildorigintargetdata;

import be.haexnet.fusio.annotation.FusioEmbedded;
import be.haexnet.fusio.annotation.FusioField;

public class OriginData {

    @FusioField
    private String name;
    @FusioEmbedded
    private OriginDataElement address;

    private OriginData() {
    }

    private OriginData(final String name, final OriginDataElement address) {
        this.name = name;
        this.address = address;
    }

    public static OriginData empty() {
        return new OriginData();
    }

    public static OriginData of(final String name, final OriginDataElement adress) {
        return new OriginData(name, adress);
    }

    public String getName() {
        return name;
    }

    public OriginDataElement getAddress() {
        return address;
    }

}
