package com.aston.sorting.io.input;

import com.aston.sorting.model.Car;

import java.util.List;

public interface InputHandler {

    List<Car> read(int size);
}
