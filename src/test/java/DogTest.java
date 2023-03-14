
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    static Dog dog;

    @BeforeAll
    static void prepareData() {
        dog = new Dog("Albert", 2);
    }

    @Test
    void testGetNameMethod() {
        assertEquals("Albert", dog.getName());
    }

    @Test
    void testSetNameMethod() {
        dog.setName("Roman");
        assertEquals("Albert", dog.getName());
    }

    @Test
    void testSetNameMethodIfEmpty() {
        Dog tom = new Dog("", 2);
        tom.setName("Roman");
        assertEquals("Roman", tom.getName());
    }
    @Test
    void testSetNameMethodIfNull() {
        Dog tom = new Dog(null, 2);
        tom.setName("Roman");
        assertEquals("Roman", tom.getName());
    }

    @Test
    void getAge() {
    }

    @Test
    void setAge() {
    }
}