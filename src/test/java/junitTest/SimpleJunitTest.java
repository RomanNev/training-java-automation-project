package junitTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleJunitTest {

    @Test
    void simpleTest(){
        assertTrue(3 > 2);

    }
}
