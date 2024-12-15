package com.project.utils;

import com.project.models.Car;

import java.util.Random;

public class CarFactory {
    private static final String[] names = new String[] {"Thunderbolt", "Velocity", "Crimson Racer", "Nitro King", "Phantom Strike", "Blaze Fury", "Turbo Hawk", "Lightning Viper", "Shadow Storm", "Apex Predator"};

    public Car getCar(){
        Random rand = new Random();
        return new Car(names[rand.nextInt(0,names.length)] + " " +
                rand.nextInt(1,100),rand.nextInt(40,84));
    }
}
