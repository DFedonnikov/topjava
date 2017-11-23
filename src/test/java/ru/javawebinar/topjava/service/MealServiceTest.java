package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration("classpath:spring/spring-app.xml")
@RunWith(SpringRunner.class)
public class MealServiceTest {

    @Autowired
    public MealService service;

    @Autowired
    public InMemoryMealRepositoryImpl repository;

    @Before
    public void setUp() {
        repository.init();
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(ADMIN_MEAL_1_ID, ADMIN_ID);
        assertMatch(meal, ADMIN_MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        Meal meal = service.get(ADMIN_MEAL_2_ID, USER_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(ADMIN_MEAL_1_ID, ADMIN_ID);
        assertMatch(service.getAll(ADMIN_ID), ADMIN_MEAL_3, ADMIN_MEAL_2);
    }

    @Test(expected = NotFoundException.class)
    public void deleteWrongUser() throws Exception {
        service.delete(USER_MEAL_2_ID, ADMIN_ID);
    }

    @Test
    public void getBetweenDates() throws Exception {
        List<Meal> meals = service.getBetweenDates(dateStart, dateEnd, ADMIN_ID);
        assertMatch(meals, ADMIN_MEAL_3, ADMIN_MEAL_2);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        List<Meal> meals = service.getBetweenDateTimes(dateTimeStart, dateTimeEnd, USER_ID);
        assertMatch(meals, USER_MEAL_2, USER_MEAL_1);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, USER_MEAL_3, USER_MEAL_2, USER_MEAL_1);
    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal(USER_MEAL_3);
        updated.setDescription("User meal_3 updated");
        updated.setCalories(999);
        service.update(updated, USER_ID);
        assertMatch(service.get(USER_MEAL_3_ID, USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateWrongUser() throws Exception {
        Meal updated = new Meal(ADMIN_MEAL_3);
        updated.setDescription("HA-HA-HA");
        updated.setCalories(1000000);
        service.update(updated, USER_ID);
    }

    @Test
    public void create() throws Exception {
        Meal newUserMeal = new Meal(LocalDateTime.parse("23/11/2017 23:59", dateFormat), "New user meal", 1500);
        Meal created = service.create(newUserMeal, USER_ID);
        newUserMeal.setId(created.getId());
        assertMatch(service.getAll(USER_ID), newUserMeal, USER_MEAL_3, USER_MEAL_2, USER_MEAL_1);
    }

}