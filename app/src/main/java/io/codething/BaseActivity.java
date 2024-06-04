package io.codething;

public class BaseActivity {

    private final int durationInMinutes;

    private final int distanceInMeters;

    //TODO record date of activity for better reporting using the Timeframe enum

    public BaseActivity(final int durationInMinutes, final int distanceInMeters) {
        this.durationInMinutes = durationInMinutes;
        this.distanceInMeters = distanceInMeters;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public int getDistanceInMeters() {
        return distanceInMeters;
    }
}
