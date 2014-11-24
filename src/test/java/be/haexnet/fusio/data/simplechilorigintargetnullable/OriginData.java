package be.haexnet.fusio.data.simplechilorigintargetnullable;

import be.haexnet.fusio.annotation.FusioEmbedded;
import be.haexnet.fusio.annotation.FusioField;

public class OriginData {

    @FusioField
    private String name;
    @FusioEmbedded(nullable = true)
    private OriginDataElement job;

    private OriginData() {
    }

    private OriginData(final String name, final OriginDataElement job) {
        this.name = name;
        this.job = job;
    }

    public static OriginData empty() {
        return new OriginData();
    }

    public static OriginData of(final String name, final OriginDataElement job) {
        return new OriginData(name, job);
    }

    public String getName() {
        return name;
    }

    public OriginDataElement getJob() {
        return job;
    }

}
