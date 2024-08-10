package edu.school21.classes;

public class Car {
    private String brand;
    private String model;
    private int maxSpeed;

    public Car() {
        this.brand = "default brand";
        this.model = "default model";
        this.maxSpeed = 0;
    }

    public Car(String brand, String model, int maxSpeed) {
        this.brand = brand;
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public int upgrade(int value) {
        this.maxSpeed += value;
        return this.maxSpeed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", max speed=" + maxSpeed +
                '}';
    }
}
