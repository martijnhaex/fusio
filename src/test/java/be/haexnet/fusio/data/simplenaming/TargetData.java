package be.haexnet.fusio.data.simplenaming;

public class TargetData {

    private String mailSubClass;
    private String category;

    private TargetData() {
    }

    private TargetData(final String mailSubClass, final String category) {
        this.mailSubClass = mailSubClass;
        this.category = category;
    }

    public static TargetData empty() {
        return new TargetData();
    }

    public static TargetData of(final String mailSubClass, final String category) {
        return new TargetData(mailSubClass, category);
    }

    public String getMailSubClass() {
        return mailSubClass;
    }

    public String getCategory() {
        return category;
    }

}
