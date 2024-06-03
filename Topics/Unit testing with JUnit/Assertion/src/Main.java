class CalculatorTest {
    Calculator calculator = new Calculator();
    void testAddition() {
        // implement a test case
        Assertions.assertEquals(11, calculator.add(5, 6));
    }
}
