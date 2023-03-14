import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    Car car;
    @BeforeEach
    public void createCar() {
        car = new Car("Skoda", "AVC-234", 2019, "Tom Ford");
    }
    @Test
//    @Execution(ExecutionMode.SAME_THREAD)// последовательное выполнение тестов
//    @Execution(ExecutionMode.CONCURRENT)// параллельное выполнение тестов
    void getManufacturer() {
        assertEquals("Skoda", car.getManufacturer());
    }

    @Test
//    @Execution(ExecutionMode.CONCURRENT)// параллельное выполнение тестов
    void getNumber() {
        assertEquals("AVC-234", car.getNumber());
    }

    @Test
//    @Execution(ExecutionMode.CONCURRENT)// параллельное выполнение тестов
    void setNumber() {
        car.setNumber("RU-123");
        assertEquals("RU-123", car.getNumber());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABC-123", "DEF-456", "DFG-434"})
    @NullSource
    @EmptySource
//    @Execution(ExecutionMode.CONCURRENT)// параллельное выполнение тестов
    void testSetNumberMultipleValues(String number) {
        car.setNumber(number);
        assertEquals(number, car.getNumber());
    }

    @ParameterizedTest
//    @Execution(ExecutionMode.CONCURRENT)// параллельное выполнение тестов
    @CsvSource({"1, 2", "0, 0", "-1, -1", "1, -9", "-2, 8"})
    void testSetNumberMultipleValuesAndParam(int a, int b) {
        assertEquals(a + b, car.testMethod(a,b));
    }

    @Test
//    @Execution(ExecutionMode.CONCURRENT)// параллельное выполнение тестов
    void getYear() {
        assertEquals(2019, car.getYear());
    }

    @Test
//    @Execution(ExecutionMode.CONCURRENT)// параллельное выполнение тестов
    void getOwner() {
        assertEquals("Tom Ford", car.getOwner());
    }

    @Test
//    @Execution(ExecutionMode.CONCURRENT)// параллельное выполнение тестов
    void setOwner() {
        car.setOwner("Ben Gur");
        assertEquals("Ben Gur", car.getOwner());
    }

    @Test
//    @Execution(ExecutionMode.CONCURRENT)// параллельное выполнение тестов
    void getOwners() {
        assertArrayEquals(new String[]{"Tom Ford"}, car.getOwners().toArray());
    }

    @Test
//    @Execution(ExecutionMode.CONCURRENT)// параллельное выполнение тестов
    void getListOfTwoOwners() {
        car.setOwner("Ben Gur");
        assertArrayEquals(new String[]{"Tom Ford", "Ben Gur"}, car.getOwners().toArray());
    }

    @Test
//    @Execution(ExecutionMode.CONCURRENT)// параллельное выполнение тестов
    public void testPrivateMethod() {
        try {

            Method method = Car.class.getDeclaredMethod("testMethod", null);
            method.setAccessible(true);
            assertEquals("abc", method.invoke(car).toString());

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
//    @Execution(ExecutionMode.CONCURRENT)// параллельное выполнение тестов
    public void testPrivateMethodWithArgument() {
        try {

            Method method = Car.class.getDeclaredMethod("testMethod", String.class);
            method.setAccessible(true);
            assertEquals("adf", method.invoke(car, "adf").toString());

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
//    @Execution(ExecutionMode.CONCURRENT)// параллельное выполнение тестов
    void printListOwners() {
        String consoleOutput = null;
        PrintStream original = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
        PrintStream capture = new PrintStream(outputStream);
        System.setOut(capture);

        List<String> owners = new ArrayList<>();
        String ownerCar = "Tom Red";
        owners.add(ownerCar);
        car.printListOwners(owners);
        capture.flush();

        consoleOutput = outputStream.toString();
        System.setOut(original);

        assertEquals("[" + ownerCar + "]\n", consoleOutput);
    }

    @ParameterizedTest
//    @Execution(ExecutionMode.CONCURRENT)// параллельное выполнение тестов
    @DisplayName("Test demonstrates how test data could be loaded from file")
    @CsvFileSource(resources = "test-data.csv", delimiter = '|', numLinesToSkip = 1)
    public void testNumbers(String input, String expected) {
        car.setNumber(input);
        assertEquals(expected, car.getNumber());
    }

    @Test
//    @Execution(ExecutionMode.CONCURRENT)// параллельное выполнение тестов
    @EnabledOnOs(OS.MAC) // выбор тестируемой операционной системы
    @DisabledOnOs(OS.WINDOWS) // выбор для какой операционной системы этот тест не запускать
    @EnabledOnJre(JRE.JAVA_17) // выбор для какой версии java этот тест
    @DisabledOnJre(JRE.JAVA_8) // выбор для какой версии java этот тест не запускать
    public void getCarYear() {
        assertEquals(2019, car.getYear());
    }

    @Test
    void getDataFromRemoteServer() {
        Exception exception = assertThrows(Exception.class, () -> car.getDataFromRemoteServer());
        assertEquals("error !!!", exception.getMessage());
    }
}