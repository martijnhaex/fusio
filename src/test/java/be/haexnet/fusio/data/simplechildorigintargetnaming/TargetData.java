package be.haexnet.fusio.data.simplechildorigintargetnaming;

public class TargetData {

    private String name;
    private TargetDataElement gamingStatistics;

    private TargetData() {
    }

    private TargetData(final String name, final TargetDataElement gamingStatistics) {
        this.name = name;
        this.gamingStatistics = gamingStatistics;
    }

    public static TargetData empty() {
        return new TargetData();
    }

    public static TargetData of(final String name, final TargetDataElement gamingStatistics) {
        return new TargetData(name, gamingStatistics);
    }

    public String getName() {
        return name;
    }

    public TargetDataElement getGamingStatistics() {
        return gamingStatistics;
    }

}
