package com.aston.sorting.comparator;

import com.aston.sorting.model.Car;

import java.util.Comparator;

/**
 * Утилитный класс, содержащий стандартные компараторы для {@link Car}.
 *
 * <p>Все компараторы — stateless-синглтоны; используйте их напрямую,
 * не создавайте новые экземпляры при каждом вызове.</p>
 *
 * <p>Пример использования:
 * <pre>{@code
 *   context.executeSort(cars, CarComparators.BY_MODEL);
 * }</pre>
 * </p>
 */
public final class CarComparators {

    // Утилитный класс — создание экземпляров запрещено.
    private CarComparators() {
        throw new UnsupportedOperationException("CarComparators — утилитный класс");
    }

    /**
     * Сортирует автомобили по мощности двигателя в порядке возрастания (от меньшего к большему).
     * Используется {@link Integer#compare}, чтобы избежать переполнения при вычитании.
     */
    public static final Comparator<Car> BY_HORSE_POWER =
            (a, b) -> Integer.compare(a.getHorsePower(), b.getHorsePower());

    /**
     * Сортирует автомобили по названию модели в лексикографическом порядке возрастания,
     * без учёта регистра («bmw» и «BMW» считаются равными).
     */
    public static final Comparator<Car> BY_MODEL =
            (a, b) -> a.getModel().compareToIgnoreCase(b.getModel());

    /**
     * Сортирует автомобили по году выпуска в порядке возрастания (от старых к новым).
     * Используется {@link Integer#compare}, чтобы избежать переполнения при вычитании.
     */
    public static final Comparator<Car> BY_YEAR =
            (a, b) -> Integer.compare(a.getYear(), b.getYear());
}
