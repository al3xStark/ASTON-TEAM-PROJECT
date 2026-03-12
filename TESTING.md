# Тесты

JUnit 5 уже подключён в `pom.xml` — ничего дополнительно устанавливать не нужно.

---

## Где находятся тесты

Тесты живут в `src/test/java` и повторяют структуру пакетов основного кода:

```
src/
├── main/java/com/aston/sorting/
│   ├── model/Car.java
│   └── comparator/CarComparators.java
│
└── test/java/com/aston/sorting/
    ├── model/CarTest.java
    └── comparator/CarComparatorsTest.java
```

Тестовый класс находится в том же пакете что и основной, но в другой папке. Это позволяет тестам иметь доступ к package-private методам.

Файлы с заглушками и TODO уже созданы — открой свой тестовый файл и начни заполнять его по примерам ниже.

---

## Анатомия тестового класса

```java
package com.aston.sorting.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Car model tests")
class CarTest {

    @Test
    @DisplayName("Builder: успешная сборка — геттеры возвращают переданные значения")
    void builder_validValues_createsCar() {
        // тест
    }
}
```

Минимум для старта:
- импорт `@Test` и других аннотаций из JUnit 5
- импорт нужных assertions через `static`
- класс без модификатора `public` — в JUnit 5 это норма
- каждый тестовый метод помечен `@Test`
- `@DisplayName` на классе и методах — читаемые названия в отчёте

---

## Паттерн AAA

Каждый тест строится по трём шагам — **Arrange, Act, Assert**:

```java
@Test
@DisplayName("Builder: успешная сборка — геттеры возвращают переданные значения")
void builder_validValues_createsCar() {
    // Arrange — подготовка данных
    int expectedHorsePower = 200;

    // Act — вызов тестируемого кода
    Car car = new Car.Builder()
            .horsePower(expectedHorsePower)
            .model("BMW M3")
            .year(2022)
            .build();

    // Assert — проверка результата
    assertEquals(expectedHorsePower, car.getHorsePower());
}
```

Разделяй секции пустой строкой — это делает тест читаемым.

---

## Как называть тесты

Плохо:
```java
void test1() { ... }
void testModel() { ... }
```

Хорошо — формат `метод_условие_ожидаемыйРезультат`:
```java
void builder_validValues_createsCar() { ... }
void builder_invalidHorsePower_throws() { ... }
void byHorsePower_sortList() { ... }
```

По названию теста должно быть понятно что он проверяет — без чтения тела.

---

## Основные аннотации

| Аннотация | Когда использовать |
|---|---|
| `@Test` | Пометить метод как тест |
| `@BeforeEach` | Выполнить код перед каждым тестом — удобно для подготовки общих данных |
| `@DisplayName` | Дать тесту читаемое название — отображается в отчёте |
| `@ParameterizedTest` | Запустить один тест с разными входными данными |
| `@ValueSource` | Источник данных для `@ParameterizedTest` |

---

## Основные assertions

| Метод | Что проверяет |
|---|---|
| `assertEquals(expected, actual)` | Два значения равны |
| `assertNotEquals(expected, actual)` | Два значения не равны |
| `assertTrue(condition)` | Условие истинно |
| `assertFalse(condition)` | Условие ложно |
| `assertNotNull(object)` | Объект не null |
| `assertNull(object)` | Объект null |
| `assertThrows(Exception.class, () -> ...)` | Код бросает ожидаемое исключение |
| `assertAll(() -> ..., () -> ...)` | Проверяет все условия за раз, не останавливаясь на первом упавшем |
| `assertDoesNotThrow(() -> ...)` | Код не бросает исключений |
| `assertIterableEquals(expected, actual)` | Два итерируемых объекта содержат одинаковые элементы в одинаковом порядке |

---

## @BeforeEach

Если несколько тестов используют один и тот же объект — не создавай его в каждом методе заново. Вынеси создание в `@BeforeEach`, он запускается перед каждым тестом автоматически:

```java
class CarComparatorsTest {

    private Car car;

    @BeforeEach
    void setUp() {
        car = car(200, "BMW M3", 2022);
    }

    @Test
    @DisplayName("BY_HORSE_POWER: меньшая мощность — первой")
    void byHorsePower_lowerFirst() {
        Car weak = car(90, "Lada", 2000);
        assertTrue(CarComparators.BY_HORSE_POWER.compare(weak, car) < 0);
    }
}
```

Важный принцип — **изоляция тестов**. Каждый тест должен быть полностью независим от других: не важно в каком порядке они запускаются и что делали предыдущие. `@BeforeEach` это гарантирует — перед каждым тестом создаётся новый объект с нуля, а не переиспользуется тот что остался после предыдущего.

Если этого не делать и хранить общий объект как `static` или изменять его внутри тестов — один тест может незаметно сломать другой. Такие баги очень трудно искать.

---

## assertAll

Обычные assertions останавливаются на первом упавшем. Это неудобно когда нужно проверить сразу несколько полей — приходится запускать тест несколько раз чтобы увидеть все проблемы.

`assertAll` запускает все проверки и показывает все упавшие сразу:

```java
@Test
@DisplayName("Builder: успешная сборка — геттеры возвращают переданные значения")
void builder_validValues_createsCar() {
    Car car = new Car.Builder()
            .horsePower(200)
            .model("BMW M3")
            .year(2022)
            .build();

    assertAll(
            () -> assertEquals(200, car.getHorsePower()),
            () -> assertEquals("BMW M3", car.getModel()),
            () -> assertEquals(2022, car.getYear())
    );
}
```

Используй `assertAll` когда проверяешь несколько полей одного объекта.

---

## assertThrows

```java
@Test
@DisplayName("Builder: horsePower <= 0 → IllegalStateException")
void builder_invalidHorsePower_throws() {
    Car.Builder builder = new Car.Builder().horsePower(0).model("Test").year(2000);
    assertThrows(IllegalStateException.class, builder::build);
}
```

Обрати внимание: сам Builder создаётся до `assertThrows`, а внутрь передаётся только вызов `build()` — именно он должен бросить исключение.

---

## Несколько тестов на один метод — граничные значения

Один метод стоит проверять с разными входными данными. Особенно важны граничные значения — ноль, отрицательные числа, минимально и максимально допустимые значения. Именно там чаще всего прячутся баги.

```java
@Test
@DisplayName("Builder: year = MIN_YEAR (1886) — граничное допустимое значение")
void builder_yearAtMinBound_ok() {
    Car car = new Car.Builder().horsePower(10).model("Benz Patent").year(1886).build();
    assertEquals(1886, car.getYear());
}

@Test
@DisplayName("Builder: year < 1886 → IllegalStateException")
void builder_yearBelowMin_throws() {
    Car.Builder builder = new Car.Builder().horsePower(100).model("Test").year(1885);
    assertThrows(IllegalStateException.class, builder::build);
}
```

---

## Вспомогательный метод

Если в тестах много раз создаётся один и тот же объект с разными параметрами — вынеси создание в приватный метод. Это убирает дублирование и делает тесты короче:

```java
/** Краткая обёртка над Builder'ом: создаёт Car(мощность, модель, год). */
private static Car car(int hp, String model, int year) {
    return new Car.Builder()
            .horsePower(hp)
            .model(model)
            .year(year)
            .build();
}
```

После этого в тестах вместо полного Builder'а достаточно:

```java
Car weak = car(90,  "Lada",   2000);
Car fast = car(300, "BMW",    2020);
```

---

## @ParameterizedTest

Если нужно прогнать один и тот же тест с несколькими значениями — не пиши отдельный метод на каждое. Используй `@ParameterizedTest` с `@ValueSource`:

```java
@ParameterizedTest(name = "horsePower={0} должен выбросить исключение")
@ValueSource(ints = {0, -1, -100})
@DisplayName("Builder: horsePower <= 0 → IllegalStateException")
void builder_invalidHorsePower_throws(int hp) {
    Car.Builder builder = new Car.Builder().horsePower(hp).model("Test").year(2000);
    assertThrows(IllegalStateException.class, builder::build);
}
```

`@ValueSource` подставляет каждое значение по очереди в параметр метода. В отчёте каждый запуск отображается отдельно — видно на каком конкретно значении упало.

`@ValueSource` поддерживает: `ints`, `longs`, `doubles`, `strings`, `booleans`.

Если нужно передавать несколько значений сразу — например, невалидную комбинацию `horsePower` и `year` — используй `@CsvSource`. Каждая строка это один набор аргументов:

```java
@ParameterizedTest(name = "hp={0}, year={1} должны выбросить исключение")
@CsvSource({
        "0, 2020",
        "-1, 2020",
        "100, 1885",
        "100, -1"
})
void builder_invalidCombinations_throws(int hp, int year) {
    Car.Builder builder = new Car.Builder().horsePower(hp).model("Test").year(year);
    assertThrows(IllegalStateException.class, builder::build);
}
```

---

## @Nested — если хочешь пойти дальше

Когда тестовый класс разрастается — Builder, equals, toString, каждый с несколькими методами — удобно сгруппировать тесты по смыслу с помощью `@Nested`:

```java
@DisplayName("Car model tests")
class CarTest {

    @Nested
    @DisplayName("Builder")
    class BuilderTests {
        @Test
        void builder_validValues_createsCar() { ... }
    }

    @Nested
    @DisplayName("equals")
    class EqualsTests {
        @Test
        void equals_sameFields_true() { ... }
    }
}
```

В отчёте тесты будут сгруппированы по вложенным классам — проще читать при большом количестве методов.

---

## Как запускать тесты

**Все тесты проекта — через терминал:**
```
mvn test
```

**Один тестовый класс — через IDE:**
Открой файл с тестами, нажми на зелёную стрелку рядом с названием класса.

**Один тестовый метод — через IDE:**
Нажми на зелёную стрелку рядом с конкретным методом.

Все три способа равнозначны — используй тот что удобнее в данный момент.

---

## Если тест упал

Не паникуй. Упавший тест — это информация, не катастрофа.

В выводе увидишь примерно следующее:

```
expected: <150> but was: <0>
org.opentest4j.AssertionFailedError
    at CarTest.builder_validValues_createsCar(CarTest.java:25)
```

Читай так:
- `expected` — что ты ожидал
- `but was` — что вернул код
- строка ниже — в каком тесте и на какой строке упало

Чаще всего причина либо в тесте (неверные ожидания), либо в коде (баг). Разберись что именно не так прежде чем что-то менять.

Если не можешь понять почему тест падает — пиши тимлиду в чат.

---

## Сложные случаи — IO и многопоточность

Тестирование файлового ввода (`FileInputHandler`) и многопоточного кода (`ConcurrentCounter`) требует дополнительных инструментов — например, `@TempDir` для работы с временными файлами в тестах. Эти темы выходят за рамки данного документа.

Когда дойдёшь до этих задач — не пытайся разобраться в одиночку. Пиши тимлиду в чат, разберём вместе.
