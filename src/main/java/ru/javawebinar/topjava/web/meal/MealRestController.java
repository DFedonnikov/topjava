package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(MealRestController.class);

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealWithExceed> getAll() {
        log.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealWithExceed> getAllFiltered(String startDate, String endDate, String startTime, String endTime) {
        log.info("getALl with filtering by time");
        LocalDate dateStart = startDate == null || startDate.isEmpty() ? LocalDate.MIN : LocalDate.parse(startDate);
        LocalDate dateEnd = endDate == null || endDate.isEmpty() ? LocalDate.MAX : LocalDate.parse(endDate);
        LocalTime timeStart = startTime == null || startTime.isEmpty()? LocalTime.MIN: LocalTime.parse(startTime);
        LocalTime timeEnd = endTime == null || endTime.isEmpty() ? LocalTime.MAX : LocalTime.parse(endTime);
        return MealsUtil.getWithExceeded(service.getAllFiltered(dateStart, dateEnd, timeStart, timeEnd), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        meal.setUserId(AuthorizedUser.id());
        return service.create(meal);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(meal);
    }





}