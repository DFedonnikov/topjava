package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> meals = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        System.out.println(meals);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        if (mealList == null) {
            return Collections.emptyList();
        }

        List<UserMealWithExceed> userMealWithExceeds = new ArrayList<>();
        Map<LocalDate, Integer> dailyCalories = new HashMap<>();
        for (UserMeal meal : mealList) {
            LocalDate currentDay = meal.getDateTime().toLocalDate();
            dailyCalories.computeIfPresent(currentDay, (localDate, sumCalories) -> sumCalories + meal.getCalories());
            dailyCalories.computeIfAbsent(currentDay, localDate -> meal.getCalories());
        }

        for (UserMeal meal : mealList) {
            LocalTime currentTime = meal.getDateTime().toLocalTime();
            if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
                boolean isCurrentDayExceeded = dailyCalories.get(meal.getDateTime().toLocalDate()) > caloriesPerDay;
                userMealWithExceeds.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), isCurrentDayExceeded));
            }
        }
        return userMealWithExceeds;
    }
}
