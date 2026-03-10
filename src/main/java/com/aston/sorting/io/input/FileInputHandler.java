package com.aston.sorting.io.input;

import com.aston.sorting.model.Car;

import java.util.List;

public class FileInputHandler implements InputHandler {

    private final String filePath;

    public FileInputHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Car> read(int size) {
        // TODO: читать файл через Files.lines(), парсить строки,
        //       пропускать невалидные с предупреждением в консоль
        return null;
    }
}
