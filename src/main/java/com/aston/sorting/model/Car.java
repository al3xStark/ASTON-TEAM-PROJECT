package com.aston.sorting.model;

import java.util.Objects;

public class Car {

    private final int horsePower;
    private final String model;
    private final int year;

    private Car(Builder builder) {
        this.horsePower = builder.horsePower;
        this.model = builder.model;
        this.year = builder.year;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Car{horsePower=" + horsePower + ", model='" + model + "', year=" + year + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return horsePower == car.horsePower
                && year == car.year
                && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horsePower, model, year);
    }

    public static class Builder {

        private int horsePower;
        private String model;
        private int year;

        public Builder horsePower(int horsePower) {
            this.horsePower = horsePower;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
