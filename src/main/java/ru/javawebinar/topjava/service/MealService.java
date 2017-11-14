package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {
    List<Meal> getAll();

    Meal get(int id);

    Meal create(Meal user);

    void delete(int id);

    void update(Meal user);
}