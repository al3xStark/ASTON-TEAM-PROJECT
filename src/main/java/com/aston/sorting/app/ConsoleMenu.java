package com.aston.sorting.app;

import com.aston.sorting.collection.CustomList;
import com.aston.sorting.comparator.CarComparators;
import com.aston.sorting.concurrent.ConcurrentCounter;
import com.aston.sorting.io.input.FileInputHandler;
import com.aston.sorting.io.input.ManualInputHandler;
import com.aston.sorting.io.input.RandomInputHandler;
import com.aston.sorting.io.output.FileOutputHandler;
import com.aston.sorting.model.Car;
import com.aston.sorting.strategy.EvenOddSortStrategy;
import com.aston.sorting.strategy.InsertionSortStrategy;
import com.aston.sorting.strategy.MergeSortStrategy;
import com.aston.sorting.strategy.SortContext;
import com.aston.sorting.strategy.SortStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private static final String DEFAULT_INPUT_FILE = "data/input/data.csv";
    private static final String DEFAULT_OUTPUT_FILE = "data/output/output.txt";
    
    private List<Car> cars;
    private String lastSortLabel = "Unknown";

    private final Scanner scanner = new Scanner(System.in);
    private final FileOutputHandler outputHandler = new FileOutputHandler();
    private final ConcurrentCounter counter = new ConcurrentCounter();

    private record SortOperation(
        SortStrategy<Car> strategy,
        Comparator<Car> comparator,
        String label
    ) {}

    public static void main(String[] args) {
        new ConsoleMenu().run();
    }

    public void run() {
        String choice;
        do {
            printMainMenu();
            choice = readLine();
            switch (choice) {
                case "1" -> handleFill();
                case "2" -> handleSort();
                case "3" -> handlePrint();
                case "4" -> handleWriteToFile();
                case "5" -> handleCountOccurrences();
                case "0" -> System.out.println("Выходим...");
                default  -> System.out.println("Неизвестная команда. Попробуйте ещё раз.");
            }
        } while (!"0".equals(choice));
    }

    // --- Главное меню --------------------------------------------------------

    private void printMainMenu() {
        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.printf( "  Коллекция: %-31s%n", collectionStatus());
        System.out.println("---------------------------------------------");
        System.out.println("  [1] Заполнить коллекцию                    ");
        System.out.println("  [2] Сортировать                            ");
        System.out.println("  [3] Показать коллекцию                     ");
        System.out.println("  [4] Записать в файл                        ");
        System.out.println("  [5] Подсчёт вхождений элемента             ");
        System.out.println("  [0] Выход                                  ");
        System.out.println("---------------------------------------------");
        System.out.print("Выбор: ");
    }

    private String collectionStatus() {
        if (cars == null) return "не загружена";
        return cars.size() + " эл.";
    }

    // --- [1] Заполнение ------------------------------------------------------

    private void handleFill() {
        System.out.println();
        System.out.println("=== Заполнение коллекции ===");
        System.out.println("[1] Случайная генерация");
        System.out.println("[2] Ручной ввод");
        System.out.println("[3] Из файла");
        System.out.println("[0] Назад");
        System.out.print("Выбор: ");

        String choice = readLine();
        if ("0".equals(choice)) return;

        int size = 0;
        size = readPositiveInt("Введите количество элементов: ");
        if (size <= 0) return;

        List<Car> loaded = switch (choice) {
            case "1" -> new RandomInputHandler().read(size);
            case "2" -> new ManualInputHandler().read(size);
            case "3" -> {
                System.out.print("Путь к файлу (Enter = " + DEFAULT_INPUT_FILE + "): ");
                String path = readLine();
                if (path.isBlank()) {
                    path = DEFAULT_INPUT_FILE;
                }
                yield new FileInputHandler(path).read(size);
            }
            default -> {
                System.out.println("Неизвестная команда.");
                yield null;
            }
        };

        if (loaded == null) return;

        if (loaded.isEmpty()) {
            System.out.println("ПРЕДУПРЕЖДЕНИЕ: коллекция пустая — данные не загружены.");
            return;
        }

        // Переносим в CustomList — кастомная коллекция как требует доп. задание 3*
        CustomList<Car> customList = new CustomList<>();
        customList.addAll(loaded);
        cars = customList;

        System.out.println("Загружено элементов: " + cars.size());
    }

    // --- [2] Сортировка ------------------------------------------------------

    private void handleSort() {
        if (!requireNonEmpty()) return;
    
        SortOperation operation = chooseSortOperation();
        if (operation == null) return;
    
        SortContext<Car> context = new SortContext<>(operation.strategy());
        context.executeSort(cars, operation.comparator());

        lastSortLabel = operation.label();
    }

    private SortOperation chooseSortOperation() {
        while (true) {
            System.out.println();
            System.out.println("=== Режим сортировки ===");
            System.out.println("[1] Обычная сортировка");
            System.out.println("[2] По чётным значениям horsePower");
            System.out.println("[0] Назад");
            System.out.print("Выбор: ");
    
            String choice = readLine();
    
            switch (choice) {
                case "1":
                    return chooseRegularSortOperation();
                case "2":
                    return chooseEvenOddSortOperation();
                case "0":
                    return null;
                default:
                    System.out.println("Неизвестная команда. Попробуйте ещё раз.");
            }
        }
    }

    private SortOperation chooseRegularSortOperation() {
        SortStrategy<Car> strategy = chooseBaseStrategy("=== Алгоритм сортировки ===");
        if (strategy == null) return null;
    
        Comparator<Car> comparator = chooseComparator();
        if (comparator == null) return null;

        String strategyName = strategy instanceof InsertionSortStrategy
        ? "InsertionSort"
        : "MergeSort";

        String fieldName;
        if (comparator == CarComparators.BY_HORSE_POWER) {
            fieldName = "horsePower";
        } else if (comparator == CarComparators.BY_MODEL) {
            fieldName = "model";
        } else {
            fieldName = "year";
        }
    
        return new SortOperation(strategy, comparator, strategyName + " by " + fieldName);
    }
    
    private SortOperation chooseEvenOddSortOperation() {
        SortStrategy<Car> innerStrategy = chooseBaseStrategy("=== Алгоритм для чётных ===");
        if (innerStrategy == null) return null;
    
        return new SortOperation(
                new EvenOddSortStrategy(innerStrategy),
                CarComparators.BY_HORSE_POWER,
                "EvenOddSort by horsePower"
        );
    }
    
    private SortStrategy<Car> chooseBaseStrategy(String title) {
        while (true) {
            System.out.println();
            System.out.println(title);
            System.out.println("[1] Сортировка вставками");
            System.out.println("[2] Сортировка слиянием");
            System.out.println("[0] Назад");
            System.out.print("Выбор: ");
    
            String choice = readLine();
    
            switch (choice) {
                case "1":
                    return new InsertionSortStrategy();
                case "2":
                    return new MergeSortStrategy();
                case "0":
                    return null;
                default:
                    System.out.println("Неизвестная команда. Попробуйте ещё раз.");
            }
        }
    }
    
    private Comparator<Car> chooseComparator() {
        while (true) {
            System.out.println();
            System.out.println("=== Поле сортировки ===");
            System.out.println("[1] Мощность (horsePower)");
            System.out.println("[2] Модель (model)");
            System.out.println("[3] Год (year)");
            System.out.println("[0] Назад");
            System.out.print("Выбор: ");
    
            String choice = readLine();
    
            switch (choice) {
                case "1":
                    return CarComparators.BY_HORSE_POWER;
                case "2":
                    return CarComparators.BY_MODEL;
                case "3":
                    return CarComparators.BY_YEAR;
                case "0":
                    return null;
                default:
                    System.out.println("Неизвестная команда. Попробуйте ещё раз.");
            }
        }
    }

    // --- [3] Вывод коллекции -------------------------------------------------

    private void handlePrint() {
        if (!requireNonEmpty()) return;

        System.out.println();
        System.out.println("=== Коллекция (" + cars.size() + " эл.) ===");
        for (int i = 0; i < cars.size(); i++) {
            System.out.printf("  %d. %s%n", i + 1, cars.get(i));
        }
    }

    // --- [4] Запись в файл ---------------------------------------------------

    private void handleWriteToFile() {
        if (!requireNonEmpty()) return;

        System.out.print("Путь к файлу для записи (Enter = " + DEFAULT_OUTPUT_FILE + "): ");
        String path = readLine();
        if (path.isBlank()) {
            path = DEFAULT_OUTPUT_FILE;
        }

        outputHandler.writeList(cars, path, lastSortLabel);
        System.out.println("Коллекция записана в файл: " + path);
    }

    // --- [5] Подсчёт вхождений -----------------------------------------------

    private void handleCountOccurrences() {
        if (!requireNonEmpty()) return;

        System.out.println();
        System.out.println("=== Подсчёт вхождений ===");
        System.out.println("Введите параметры искомого автомобиля:");

        ManualInputHandler handler = new ManualInputHandler();
        
        Car target = handler.readOne();
        if (target == null) return;

        int count = counter.countOccurrences(cars, target);
        System.out.println("Результат: найдено " + count + " вхождений для " + target);

        System.out.print("Записать результат в файл? (y/n): ");
        if ("y".equalsIgnoreCase(readLine())) {
            System.out.print("Путь к файлу (Enter = " + DEFAULT_OUTPUT_FILE + "): ");
            String path = readLine();
            if (path.isBlank()) {
                path = DEFAULT_OUTPUT_FILE;
            }

            outputHandler.writeCount(count, target, path, lastSortLabel);
            System.out.println("Результат записан в файл: " + path);
        }
    }

    // --- Вспомогательные методы ввода ----------------------------------------


    private String readLine() {
        return scanner.hasNextLine() ? scanner.nextLine().trim() : "";
    }

    private int readPositiveInt(String prompt) {
        for (int attempt = 0; attempt < 3; attempt++) {
            System.out.print(prompt);
            String line = readLine();
            try {
                int value = Integer.parseInt(line);
                if (value > 0) return value;
                System.out.println("Значение должно быть больше 0.");
            } catch (NumberFormatException e) {
                System.out.println("Ожидается целое число.");
            }
        }
        System.out.println("Слишком много ошибок ввода. Операция отменена.");
        return -1;
    }

    private boolean requireNonEmpty() {
        if (cars == null || cars.isEmpty()) {
            System.out.println("Коллекция не загружена. Сначала выберите пункт [1].");
            return false;
        }
        return true;
    }
}