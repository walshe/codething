/*
 * Tracker app for tracking activities and reporting calories
 */
package io.codething;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TrackerApp {

    //Mimics a database of users
    private final List<User> users = new ArrayList<>();

    public void addUser(User newUser) {
        if (users.stream().anyMatch(user -> user.getUserId().equalsIgnoreCase(newUser.getUserId()))) {
            throw new IllegalArgumentException("UserId already exists: " + newUser.getUserId());
        }
        users.add(newUser);
    }

    public User getUser(final String userId) {
        return users.stream()
                .filter(user -> user.getUserId().equalsIgnoreCase(userId))
                .findFirst()
                .orElse(null);
    }


    public void addActivity(final String userId, final Activity activity) {
        Optional.ofNullable(getUser(userId))
                .ifPresent(user -> user.addActivity(activity));
    }

    public int totalActivities(final String userId) {
        return Optional.ofNullable(getUser(userId))
                .map(User::totalActivities)
                .orElse(0);
    }

    public int totalCaloriesBurnt(String userId, boolean includeBmr) {
        return Optional.ofNullable(getUser(userId))
                .map(user -> includeBmr ? user.totalCaloriesBurntIncludingBmr() : user.totalCaloriesBurntExcludingBmr())
                .orElse(0);
    }

    public int totalDurationInMinutes(String userId) {
        return Optional.ofNullable(getUser(userId))
                .map(User::totalDurationInMinutes)
                .orElse(0);
    }

    public static void main(String[] args) {

        try {
            TrackerApp app = new TrackerApp();

            User emmett = new User("emmett", 70, 1500);
            User andy = new User("andy", 75, 1650);

            app.addUser(emmett);
            app.addUser(new User("Emmett", 71, 1500));
            app.addUser(andy);

            app.addActivity("emmett", new Running(13, 2000));
            app.addActivity("emmett", new Walking(60, 5000));
            app.addActivity("emmett", new Swimming(10, 500));


            app.addActivity("andy", new Running(45, 5000));
            app.addActivity("andy", new Walking(60, 5000));
            app.addActivity("andy", new Swimming(20, 900));

            Arrays.asList(emmett, andy).forEach(user -> {
                System.out.println("-------------------------------------------" + user.getUserId() + "-------------------------------------------");
                System.out.println(user.getUserId() + "'s Total Activities: " + app.totalActivities(user.getUserId()));
                System.out.println(user.getUserId() + "'s Total Duration: " + app.totalDurationInMinutes(user.getUserId()) + " minutes");
                System.out.println(user.getUserId() + "'s Total Calories Including BMR: " + app.totalCaloriesBurnt(user.getUserId(), true) + " calories");
                System.out.println(user.getUserId() + "'s Total Calories Excluding BMR: " + app.totalCaloriesBurnt(user.getUserId(), false) + " calories");
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }

}
