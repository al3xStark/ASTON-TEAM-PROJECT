package com.aston.sorting.io;

import com.aston.sorting.io.input.ManualInputHandler;
import com.aston.sorting.model.Car;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

class ManualInputHandlerTest {
    @Test
    void shouldCreateCarFromValidInput() {
            String input = "99\nLada\n2010\n";
            InputStream testStream = new ByteArrayInputStream(input.getBytes());
            ManualInputHandler handler = new ManualInputHandler(testStream);

            List<Car> cars = handler.read(1);
            Car car = cars.getFirst();

            assertEquals(1, cars.size());
            assertEquals(99, car.getHorsePower());
            assertEquals("Lada", car.getModel());
            assertEquals(2010, car.getYear());
    }

    @Test
    void shouldRetryOnInvalidHorsePower() {
        String input = "0\n100\nLada\n2010\n";
        InputStream testStream = new ByteArrayInputStream(input.getBytes());
        ManualInputHandler handler = new ManualInputHandler(testStream);

        List<Car> cars = handler.read(1);
        Car car = cars.getFirst();

        assertEquals(1, cars.size());
        assertEquals(100, car.getHorsePower());
        assertEquals("Lada", car.getModel());
        assertEquals(2010, car.getYear());
    }

    @Test
    void shouldRetryOnInvalidYear() {
        String input = "99\nLada\n010\n2010\n";
        InputStream testStream = new ByteArrayInputStream(input.getBytes());
        ManualInputHandler handler = new ManualInputHandler(testStream);

        List<Car> cars = handler.read(1);
        Car car = cars.getFirst();

        assertEquals(1, cars.size());
        assertEquals(99, car.getHorsePower());
        assertEquals("Lada", car.getModel());
        assertEquals(2010, car.getYear());
    }

    @Test
    void shouldRetryOnEmptyModel() {
        String input = "100\n\nLada\n2010\n";
        InputStream testStream = new ByteArrayInputStream(input.getBytes());
        ManualInputHandler handler = new ManualInputHandler(testStream);

        List<Car> cars = handler.read(1);
        Car car = cars.getFirst();

        assertEquals(1, cars.size());
        assertEquals(100, car.getHorsePower());
        assertEquals("Lada", car.getModel());
        assertEquals(2010, car.getYear());
    }

    @Test
    void shouldCreateMultipleCarsFromValidInput() {
        String input = "100\nLada\n2010\n120\nHonda\n2026\n";
        InputStream testStream = new ByteArrayInputStream(input.getBytes());
        ManualInputHandler handler = new ManualInputHandler(testStream);

        List<Car> cars = handler.read(2);
        Car car1 = cars.getFirst();
        Car car2 = cars.get(1);

        assertEquals(2, cars.size());
        assertEquals(100, car1.getHorsePower());
        assertEquals("Lada", car1.getModel());
        assertEquals(2010, car1.getYear());

        assertEquals(120, car2.getHorsePower());
        assertEquals("Honda", car2.getModel());
        assertEquals(2026, car2.getYear());
    }
}
