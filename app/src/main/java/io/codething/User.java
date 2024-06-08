package io.codething;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class User {
    private final String userId;
    private final int weightInKg;
    private final int bmr;

    private final List<Activity> activities;

    public User(final String userId, final int weightInKg, final int bmr) {

        if(userId == null || weightInKg <= 0  || bmr <= 0){
            throw new IllegalArgumentException("please use valid values");
        }

        this.userId = userId.toLowerCase();
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
        return Collections.unmodifiableList(activities);
    }

    public int totalActivities() {
        return activities.size();
    }

    public int totalCaloriesBurntIncludingBmr() {

        int totalDurationInMinutes = totalDurationInMinutes();
        int totalCalories = totalCaloriesBurntExcludingBmr();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return weightInKg == user.weightInKg && bmr == user.bmr && Objects.equals(userId, user.userId) && Objects.equals(activities, user.activities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, weightInKg, bmr, activities);
    }
}
