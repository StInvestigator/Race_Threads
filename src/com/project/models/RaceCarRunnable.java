package com.project.models;

import com.project.Race;
import com.project.utils.DateFormatter;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

public class RaceCarRunnable extends Car implements Runnable {
    private int passed = 0;
    private int distance;
    private boolean isFinished = false;
    private final CountDownLatch latch;
    private long finishTime;

    public RaceCarRunnable(String name, int maxSpeed, int distance,
                           CountDownLatch latch) {
        super(name, maxSpeed);
        this.distance = distance;
        this.latch = latch;
    }

    public RaceCarRunnable(Car car, int distance, CountDownLatch latch) {
        this(car.getName(), car.getMaxSpeed(), distance, latch);
    }

    public long getFinishTime() {
        return finishTime;
    }

    public int getPassed() {
        return passed;
    }

    private void setPassed(int passed) {
        this.passed = passed;
        if (passed >= distance) {
            latch.countDown();
            isFinished = true;
            finishTime = System.currentTimeMillis() - Race.startRaceTime.get();

            System.out.println(getName() + " FINISHED IN " +
                    DateFormatter.convertToTime(finishTime) + '!');
        }
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
        passed = 0;
        isFinished = false;
    }

    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void run() {
        while (!isFinished) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int currentSpeed = getRandomSpeed();
            setPassed(passed + currentSpeed);
            if (!isFinished)
                System.out.println(getName() + " => speed: " + currentSpeed +
                        "m/s; progress: " + Math.min(passed, distance) + "/" +
                        distance);
        }
    }
}
