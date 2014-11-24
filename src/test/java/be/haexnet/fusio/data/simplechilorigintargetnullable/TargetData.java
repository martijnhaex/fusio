package be.haexnet.fusio.data.simplechilorigintargetnullable;

public class TargetData {

    private String name;
    private TargetDataElement job;

    private TargetData() {
    }

    private TargetData(final String name, final TargetDataElement job) {
        this.name = name;
        this.job = job;
    }

    public static TargetData empty() {
        return new TargetData();
    }

    public static TargetData of(final String name, final TargetDataElement job) {
        return new TargetData(name, job);
    }

    public String getName() {
        return name;
    }

    public TargetDataElement getJob() {
        return job;
    }

}
