package io.codething;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userId;
    private final int weightInKg;
    private final int bmr;

    private List<Activity> activities;

    public User(final String userId, final int weightInKg, final int bmr) {
        this.userId = userId;
        this.weightInKg = weightInKg;
        this.bmr = bmr;

        activities = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public int getWeightInKg() {
        return weightInKg;
    }


    public int getBmr() {
        return bmr;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public int totalActivities() {
        return activities.size();
    }

    public int totalCaloriesBurntIncludingBmr() {

        int totalDurationInMinutes = totalDurationInMinutes();
        int totalCalories = activities.stream()
                .mapToInt(Activity::getCaloriesBurnt)
                .sum();

        // Add BMR proportional to the total duration of activities
        totalCalories += (totalDurationInMinutes * bmr) / 1440; // assuming BMR is per day (1440 minutes)
        return totalCalories;

    }

    public int totalCaloriesBurntExcludingBmr() {

        return activities.stream()
                .mapToInt(Activity::getCaloriesBurnt)
                .sum();

    }

    public int totalDurationInMinutes() {
        return activities.stream()
                .mapToInt(Activity::getDurationInMinutes)
                .sum();
    }
}
