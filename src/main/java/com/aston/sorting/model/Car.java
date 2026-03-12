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

        private static final int MIN_YEAR = 1886;
        private static final int MAX_YEAR = java.time.Year.now().getValue();

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
            if (model == null || model.isBlank()) {
                throw new IllegalStateException("Model must not be blank");
            }
            if (horsePower <= 0) {
                throw new IllegalStateException("Horse power must be positive");
            }
            if (year < MIN_YEAR || year > MAX_YEAR) {
                throw new IllegalStateException("Year must be between " + MIN_YEAR + " and " + MAX_YEAR);
            }
            return new Car(this);
        }
    }
}
