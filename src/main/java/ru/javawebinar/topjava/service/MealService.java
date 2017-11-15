package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {
    List<Meal> getAll();

    List<Meal> getAllFiltered(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime);

    Meal get(int id);

    Meal create(Meal user);

    void delete(int id);

    void update(Meal user);
}