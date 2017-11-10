package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {
    public static final Logger logger = getLogger(MealsServlet.class);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Forward to meals");
        request.setAttribute("meals", MealsUtil.getTestData());
        request.setAttribute("dateFormat", DATE_TIME_FORMATTER);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
