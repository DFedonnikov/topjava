package ru.javawebinar.topjava;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JUnitStopWatch extends Stopwatch {

    private static final List<String> report = new ArrayList<>();

    private static final Logger LOG = LoggerFactory.getLogger(MealTestData.class);

    private static void logInfo(Description description, String status, long nanos) {
        String testName = description.getMethodName();
        String msg = String.format("%s took %d microseconds. Status: %s", testName, TimeUnit.NANOSECONDS.toMicros(nanos), status);
        report.add(msg);
        LOG.info(msg);
    }

    @Override
    protected void succeeded(long nanos, Description description) {
        logInfo(description, "succeeded", nanos);
    }

    @Override
    protected void failed(long nanos, Throwable e, Description description) {
        logInfo(description, "failed", nanos);
    }

    @Override
    protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
        logInfo(description, "skipped", nanos);
        ;
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, "finished", nanos);
    }


    public static List<String> getReport() {
        return report;
    }

    public static Logger getLOG() {
        return LOG;
    }

}
