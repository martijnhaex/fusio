package be.haexnet.fusio.data.simplenaming;

import be.haexnet.fusio.annotation.FusioField;

public class OriginData {

    @FusioField(name = "mailSubClass")
    private String itemSubClass;
    @FusioField(name = "category")
    private String itemCategory;

    private OriginData() {
    }

    private OriginData(final String itemSubClass, final String itemCategory) {
        this.itemSubClass = itemSubClass;
        this.itemCategory = itemCategory;
    }

    public static OriginData empty() {
        return new OriginData();
    }

    public static OriginData of(final String itemSubClss, final String itemCategory) {
        return new OriginData(itemSubClss, itemCategory);
    }

    public String getItemSubClass() {
        return itemSubClass;
    }

    public String getItemCategory() {
        return itemCategory;
    }

}
