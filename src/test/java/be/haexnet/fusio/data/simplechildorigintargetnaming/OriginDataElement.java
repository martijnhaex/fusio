package be.haexnet.fusio.data.simplechildorigintargetnaming;

import be.haexnet.fusio.annotation.FusioField;

public class OriginDataElement {

    @FusioField
    private String game;
    @FusioField
    private Long hoursPlayed;

    private OriginDataElement() {
    }

    private OriginDataElement(final String game, final Long hoursPlayed) {
        this.game = game;
        this.hoursPlayed = hoursPlayed;
    }

    public static OriginDataElement empty() {
        return new OriginDataElement();
    }

    public static OriginDataElement of(final String game, final Long hoursPlayed) {
        return new OriginDataElement(game, hoursPlayed);
    }

    public String getGame() {
        return game;
    }

    public Long getHoursPlayed() {
        return hoursPlayed;
    }

}
