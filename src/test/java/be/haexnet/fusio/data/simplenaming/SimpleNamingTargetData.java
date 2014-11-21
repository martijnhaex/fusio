package be.haexnet.fusio.data.simplenaming;

public class SimpleNamingTargetData {

    private String mailSubClass;
    private String category;

    private SimpleNamingTargetData() {
    }

    private SimpleNamingTargetData(final String mailSubClass, final String category) {
        this.mailSubClass = mailSubClass;
        this.category = category;
    }

    public static SimpleNamingTargetData empty() {
        return new SimpleNamingTargetData();
    }

    public static SimpleNamingTargetData of(final String mailSubClass, final String category) {
        return new SimpleNamingTargetData(mailSubClass, category);
    }

    public String getMailSubClass() {
        return mailSubClass;
    }

    public String getCategory() {
        return category;
    }

}
