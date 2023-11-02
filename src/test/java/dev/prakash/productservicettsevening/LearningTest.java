package dev.prakash.productservicettsevening;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class LearningTest {
    // Initialize the logger
    private static final Logger log = LoggerFactory.getLogger(ProductTest.class);

    @BeforeAll
    static void setup() {
        log.info("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
        log.info("@BeforeEach - executes before each test method in this class");
    }
    @DisplayName("Single test successful")
    @Test
    void testSingleSuccessTest() {
        log.info("Success");
    }

    @Test
    @Disabled("Not implemented yet")
    void testShowSomething() {
    }

    @AfterEach
    void tearDown() {
        log.info("@AfterEach - executed after each test method.");
    }

    @AfterAll
    static void done() {
        log.info("@AfterAll - executed after all test methods.");
    }
    @Test
    void lambdaExpressions() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        assertTrue(numbers.stream()
                .mapToInt(Integer::intValue)
                .sum() > 5, "Sum should be greater than 5");
    }

    @Test
    void groupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};
        assertAll("numbers",
                () -> assertEquals(numbers[0], 0),
                () -> assertEquals(numbers[3], 3),
                () -> assertEquals(numbers[4], 4)
        );
    }
    @Test
    void trueAssumption() {
        assumeTrue(5 > 1);
        assertEquals(5 + 2, 7);
    }

    @Test
    void falseAssumption() {
        assumeFalse(5 < 1);
        assertEquals(5 + 2, 7);
    }

    @Test
    void assumptionThat() {
        String someString = "Just a string";
        assumingThat(
                someString.equals("Just a string"),
                () -> assertEquals(2 + 2, 4)
        );
    }
    @Test
    void shouldThrowException() {
        Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException("Not supported");
        });
        assertEquals("Not supported", exception.getMessage());
    }

    @Test
    void assertThrowsException() {
        String str = null;
        assertThrows(IllegalArgumentException.class, () -> {
            Integer.valueOf(str);
        });
    }
//    @Suite
//    @SelectPackages("com.baeldung")
//    @ExcludePackages("com.baeldung.suites")
//    public class AllUnitTest {}
//    @Suite
//    @SelectClasses({AssertionTest.class, AssumptionTest.class, ExceptionTest.class})
//    public class AllUnitTest {}

//    @TestFactory
//    Stream<DynamicTest> translateDynamicTestsFromStream() {
//        return in.stream()
//                .map(word ->
//                        DynamicTest.dynamicTest("Test translate " + word, () -> {
//                            int id = in.indexOf(word);
//                            assertEquals(out.get(id), translate(word));
//                        })
//                );
//    }
}
