package be.haexnet.fusio.data.simplechildorigintarget;

public class TargetData {

    private String name;
    private TargetDataElement address;

    private TargetData() {
    }

    private TargetData(final String name, final TargetDataElement address) {
        this.name = name;
        this.address = address;
    }

    public static TargetData empty() {
        return new TargetData();
    }

    public static TargetData of(final String name, final TargetDataElement adress) {
        return new TargetData(name, adress);
    }

    public String getName() {
        return name;
    }

    public TargetDataElement getAddress() {
        return address;
    }

}
