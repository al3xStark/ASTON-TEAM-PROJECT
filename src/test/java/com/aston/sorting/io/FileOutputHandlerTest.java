package com.aston.sorting.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

class FileOutputHandlerTest {

    // Используй @TempDir для создания временных файлов — JUnit 5 сам создаёт
    // и удаляет временную директорию после каждого теста.
    //
    // Пример получения пути к временному файлу:
    //   Path file = tempDir.resolve("output.txt");
    //   FileOutputHandler handler = new FileOutputHandler();
    //   handler.write(list, file.toString());
    //   String content = Files.readString(file);

    @Test
    void writeListShouldAppendToFile(@TempDir Path tempDir) {
        // TODO: вызвать write() дважды с разными списками,
        //       прочитать файл и проверить что содержимое обоих вызовов присутствует
    }

    @Test
    void writeCountShouldAppendToFile(@TempDir Path tempDir) {
        // TODO: вызвать writeCount() дважды,
        //       прочитать файл и проверить что содержимое обоих вызовов присутствует
    }


}
