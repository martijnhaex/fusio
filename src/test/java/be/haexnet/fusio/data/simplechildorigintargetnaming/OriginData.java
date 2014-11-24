package be.haexnet.fusio.data.simplechildorigintargetnaming;

import be.haexnet.fusio.annotation.FusioEmbedded;
import be.haexnet.fusio.annotation.FusioField;

public class OriginData {

    @FusioField
    private String name;
    @FusioEmbedded(name = "gamingStatistics")
    private OriginDataElement statistics;

    private OriginData() {
    }

    private OriginData(final String name, final OriginDataElement statistics) {
        this.name = name;
        this.statistics = statistics;
    }

    public static OriginData empty() {
        return new OriginData();
    }

    public static OriginData of(final String name, final OriginDataElement statistics) {
        return new OriginData(name, statistics);
    }

    public String getName() {
        return name;
    }

    public OriginDataElement getStatistics() {
        return statistics;
    }

}
