package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int ADMIN_MEAL_1_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_2_ID = START_SEQ + 3;
    public static final int ADMIN_MEAL_3_ID = START_SEQ + 4;
    public static final int USER_MEAL_1_ID = START_SEQ + 5;
    public static final int USER_MEAL_2_ID = START_SEQ + 6;
    public static final int USER_MEAL_3_ID = START_SEQ + 7;

    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static final Meal ADMIN_MEAL_1 = new Meal(ADMIN_MEAL_1_ID, LocalDateTime.parse("22/11/2017 10:22", dateFormat), "Admin meal_1", 500);
    public static final Meal ADMIN_MEAL_2 = new Meal(ADMIN_MEAL_2_ID, LocalDateTime.parse("23/11/2017 18:26", dateFormat), "Admin meal_2", 500);
    public static final Meal ADMIN_MEAL_3 = new Meal(ADMIN_MEAL_3_ID, LocalDateTime.parse("23/11/2017 20:26", dateFormat), "Admin meal_3", 500);
    public static final Meal USER_MEAL_1 = new Meal(USER_MEAL_1_ID, LocalDateTime.parse("22/11/2017 10:45", dateFormat), "User meal_1", 500);
    public static final Meal USER_MEAL_2 = new Meal(USER_MEAL_2_ID, LocalDateTime.parse("23/11/2017 18:35", dateFormat), "User meal_2", 500);
    public static final Meal USER_MEAL_3 = new Meal(USER_MEAL_3_ID, LocalDateTime.parse("23/11/2017 20:45", dateFormat), "User meal_3", 500);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }

}
