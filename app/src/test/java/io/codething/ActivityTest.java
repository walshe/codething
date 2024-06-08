package io.codething;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class ActivityTest {

    private Activity activity;

    private int durationInMinutes = 100;

    private int distanceInMeters = 100;

    @BeforeEach
    public void setup(){
        activity = null;
    }

    @Test
    public void walking(){
        activity = new Walking(durationInMinutes, distanceInMeters);
        assertThat(activity.getDurationInMinutes(), is(durationInMinutes));
        assertThat(activity.getDistanceInMeters(), is(distanceInMeters));
        assertThat(activity.getCaloriesBurnt(), is(10));
    }

    @Test
    public void swimming(){
        activity = new Swimming(durationInMinutes, distanceInMeters);
        assertThat(activity.getDurationInMinutes(), is(durationInMinutes));
        assertThat(activity.getDistanceInMeters(), is(distanceInMeters));
        assertThat(activity.getCaloriesBurnt(), is(20));
    }

    @Test
    public void running(){
        activity = new Running(durationInMinutes, distanceInMeters);
        assertThat(activity.getDurationInMinutes(), is(durationInMinutes));
        assertThat(activity.getDistanceInMeters(), is(distanceInMeters));
        assertThat(activity.getCaloriesBurnt(), is(20));
    }

    @Test
    void testEquals() {
        Activity activity1 = new Running(durationInMinutes, distanceInMeters);
        Activity activity2 = new Running(durationInMinutes, distanceInMeters);
        Activity activity3 = new Running(100000, distanceInMeters);

        assertEquals(activity1, activity1);
        assertEquals(activity1, activity2);

        assertNotEquals(activity1, activity3);

    }

    @Test
    void testHashCode() {
        Activity activity1 = new Running(durationInMinutes, distanceInMeters);
        Activity activity2 = new Running(durationInMinutes, distanceInMeters);
        Activity activity3 = new Running(100000, distanceInMeters);

        assertEquals(activity1.hashCode(), activity2.hashCode());

        // usually not equal :)
        assertNotEquals(activity1.hashCode(), activity3.hashCode());

    }

}
