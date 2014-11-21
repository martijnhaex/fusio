package be.haexnet.fusio.data.simplenaming;

import be.haexnet.fusio.annotation.FusioField;

public class SimpleNamingOriginData {

    @FusioField(name = "mailSubClass")
    private String itemSubClass;
    @FusioField(name = "category")
    private String itemCategory;

    private SimpleNamingOriginData() {
    }

    private SimpleNamingOriginData(final String itemSubClass, final String itemCategory) {
        this.itemSubClass = itemSubClass;
        this.itemCategory = itemCategory;
    }

    public static SimpleNamingOriginData empty() {
        return new SimpleNamingOriginData();
    }

    public static SimpleNamingOriginData of(final String itemSubClss, final String itemCategory) {
        return new SimpleNamingOriginData(itemSubClss, itemCategory);
    }

    public String getItemSubClass() {
        return itemSubClass;
    }

    public String getItemCategory() {
        return itemCategory;
    }

}
