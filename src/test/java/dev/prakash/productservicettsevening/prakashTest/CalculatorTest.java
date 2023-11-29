package dev.prakash.productservicettsevening.prakashTest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Simple multiplication should work")
    void testMultiply() {
        assertEquals(20, calculator.multiply(4, 5),
                "Regular multiplication should work");
    }

    @RepeatedTest(5)
    @DisplayName("Ensure correct handling of zero")
    void testMultiplyWithZero() {
        assertEquals(0, calculator.multiply(0, 5), "Multiple with zero should be zero");
        assertEquals(0, calculator.multiply(5, 0), "Multiple with zero should be zero");
    }

    @Test
    void exceptionTesting() {
        // set up version

        Throwable exception = assertThrows(IllegalArgumentException.class, () ->  calculator.setVersion(1));
        assertEquals("Age must be an Integer.", exception.getMessage());
    }
    @Test
    @Disabled("Not implemented yet")
    void groupedAssertions() {
//        Address address = new Address();
//        assertAll("address name",
//                () -> assertEquals("John", address.getFirstName()),
//                () -> assertEquals("User", address.getLastName())
//        );
    }
}