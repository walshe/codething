package io.codething;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class UserTest {

    private User user1;
    private User user2;

    @BeforeEach
    public void setuo() {
        user1 = new User("emmett", 100, 2000);
        user2 = new User("emmett", 60, 1500);
    }

    @Test
    public void constructor() {

        assertThat(user1.getWeightInKg(), is(100));
        assertThat(user1.getBmr(), is(2000));
        assertThat(user1.getUserId(), is("emmett"));
    }

    @Test
    public void constructor_invalid() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new User(null, 100, 2000);
        });

        IllegalArgumentException userIdException = assertThrows(IllegalArgumentException.class, () -> {
            new User(null, 100, 2000);
        });

        IllegalArgumentException weightException = assertThrows(IllegalArgumentException.class, () -> {
            new User("validuserid", 0, 2000);
        });

        IllegalArgumentException bmrException = assertThrows(IllegalArgumentException.class, () -> {
            new User("validuserid", 70, 0);
        });
    }

    @Test
    public void addActivity() {

        //check empty first
        assertThat(Whitebox.getInternalState(user1, "activities"), is(Collections.emptyList()));

        Activity running = new Running(10, 1000);
        user1.addActivity(running);

        List<Activity> activityList = Whitebox.getInternalState(user1, "activities");

        assertThat(activityList, is(notNullValue()));
        assertThat(activityList, hasItem(running));

    }

    @Test
    public void getActivities() {

        //check empty first
        assertThat(user1.getActivities(), is(Collections.emptyList()));

        // set some data
        Activity running = new Running(10, 1000);
        Whitebox.setInternalState(user1, "activities", Arrays.asList(running));

        // test the actual method
        List<Activity> activityList = user1.getActivities();

        assertThat(activityList, is(notNullValue()));
        assertThat(activityList, hasItem(running));

    }

    @Test
    public void totalActivities() {
        //check empty first
        assertThat(user1.totalActivities(), is(0));

        // set some data
        Activity running = new Running(10, 1000);
        Whitebox.setInternalState(user1, "activities", Arrays.asList(running));

        // test the actual method
        int totalActivities = user1.totalActivities();

        //verify
        assertThat(totalActivities, is(1));

    }

    @Test
    public void totalCaloriesBurntExcludingBmr() {

        //check empty first
        assertThat(user1.totalActivities(), is(0));
        assertThat(user2.totalActivities(), is(0));

        // set some data
        Activity running = new Running(10, 1000);
        Whitebox.setInternalState(user1, "activities", Arrays.asList(running));
        Whitebox.setInternalState(user2, "activities", Arrays.asList(running));

        // test the actual method
        int totalCaloriesBurntExcludingBmrUser1 = user1.totalCaloriesBurntExcludingBmr();
        int totalCaloriesBurntExcludingBmrUser2 = user2.totalCaloriesBurntExcludingBmr();

        //verify
        assertThat(totalCaloriesBurntExcludingBmrUser1, is(running.getCaloriesBurnt()));
        assertThat(totalCaloriesBurntExcludingBmrUser2, is(running.getCaloriesBurnt()));

    }

    @Test
    public void totalCaloriesBurntIncludingBmr() {

        //check empty first
        assertThat(user1.totalActivities(), is(0));
        assertThat(user2.totalActivities(), is(0));

        // set some data
        Activity running = new Running(10, 1000);
        Whitebox.setInternalState(user1, "activities", Arrays.asList(running));
        Whitebox.setInternalState(user2, "activities", Arrays.asList(running));

        // test the actual method
        int totalCaloriesBurntIncludingBmrUser1 = user1.totalCaloriesBurntIncludingBmr();
        int totalCaloriesBurntIncludingBmrUser2 = user2.totalCaloriesBurntIncludingBmr();

        //verify
        assertThat(totalCaloriesBurntIncludingBmrUser1, is(running.getCaloriesBurnt() + (user1.getBmr() * running.getDurationInMinutes() / 1440)));
        assertThat(totalCaloriesBurntIncludingBmrUser2, is(running.getCaloriesBurnt() + (user2.getBmr() * running.getDurationInMinutes() / 1440)));

    }

    @Test
    void testEquals() {
        User testUser1 = new User("emmett", 65, 1500);
        User testUser2 = new User("emmett", 65, 1500);
        User testUser3 = new User("jack", 75, 2000);


        assertEquals(testUser1, testUser1);
        assertEquals(testUser1, testUser2);

        assertNotEquals(testUser1, testUser3);

    }

    @Test
    void testHashCode() {
        User testUser1 = new User("emmett", 65, 1500);
        User testUser2 = new User("emmeTT", 65, 1500);
        User testUser3 = new User("jack", 75, 2000);

        assertEquals(testUser1.hashCode(), testUser2.hashCode());

        // usually not equal :)
        assertNotEquals(testUser1.hashCode(), testUser3.hashCode());

    }

}
