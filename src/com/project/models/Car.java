package com.project.models;

import java.util.Random;

public class Car {
    private final String name;
    private final int maxSpeed; // in m/sec

    public Car(String name, int maxSpeed) {
        this.name = name;
        this.maxSpeed = maxSpeed;
    }

    public int getRandomSpeed(){
        Random rand = new Random();
        return rand.nextInt(maxSpeed/2,maxSpeed+1);
    }

    public String getName() {
        return name;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }
}
