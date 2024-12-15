package com.project;

import com.project.models.RaceCarRunnable;
import com.project.utils.CarFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

public class Race {

    public static AtomicLong startRaceTime;

    static void startRace(List<Thread> cars) {
        Thread countDown = new Thread(new Runnable() { // в задании просили через анонимный класс
            @Override
            public void run() {
                for (int i = 3; i > 0; i--) {
                    System.out.println(i + "...");
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        countDown.start();
        try {
            countDown.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("GO!!!");
        startRaceTime = new AtomicLong(System.currentTimeMillis());
        cars.forEach(Thread::start);
    }

    public static void main(String[] args) {
        final int CARS_COUNT = 5;
        CountDownLatch latch = new CountDownLatch(CARS_COUNT);
        CarFactory factory = new CarFactory();
        int distance = 500;

        List<RaceCarRunnable> cars = Stream.generate(() ->
                        new RaceCarRunnable(factory.getCar(), distance, latch))
                .limit(CARS_COUNT).toList();
        List<Thread> threads = new ArrayList<>();
        cars.forEach(x -> threads.add(new Thread(x)));

        startRace(threads);
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\nThe winner is " + cars.stream().min(Comparator.
                comparingLong(RaceCarRunnable::getFinishTime)).get().getName() + "!!");
    }
}