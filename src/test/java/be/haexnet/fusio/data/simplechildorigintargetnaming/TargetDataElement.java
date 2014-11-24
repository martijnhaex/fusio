package be.haexnet.fusio.data.simplechildorigintargetnaming;

import be.haexnet.fusio.annotation.FusioField;

public class TargetDataElement {

    @FusioField
    private String game;
    @FusioField
    private Long hoursPlayed;

    private TargetDataElement() {
    }

    private TargetDataElement(final String game, final Long hoursPlayed) {
        this.game = game;
        this.hoursPlayed = hoursPlayed;
    }

    public static TargetDataElement empty() {
        return new TargetDataElement();
    }

    public static TargetDataElement of(final String game, final Long hoursPlayed) {
        return new TargetDataElement(game, hoursPlayed);
    }

    public String getGame() {
        return game;
    }

    public Long getHoursPlayed() {
        return hoursPlayed;
    }

}
