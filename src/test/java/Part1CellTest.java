import static org.junit.jupiter.api.Assertions.*;

class Part1CellTest {

    @org.junit.jupiter.api.Test
    void isNumber() {
        Part1Cell part1Cell1 = new Part1Cell("4");
        Part1Cell part1Cell2 = new Part1Cell("12");
        Part1Cell part1Cell3 = new Part1Cell("-5");
        Part1Cell part1Cell4 = new Part1Cell("3.14");
        Part1Cell part1Cell5 = new Part1Cell("-2.718");
        Part1Cell part1Cell6 = new Part1Cell("");
        Part1Cell part1Cell7 = new Part1Cell(null);
        Part1Cell part1Cell8 = new Part1Cell("-.");
        Part1Cell part1Cell9 = new Part1Cell("abc");
        Part1Cell part1Cell10 = new Part1Cell("123abc");
        Part1Cell part1Cell11 = new Part1Cell("3.14.15");
        Part1Cell part1Cell12 = new Part1Cell(".");
        Part1Cell part1Cell13 = new Part1Cell("5.");
        // Valid cases
        assertTrue(part1Cell1.isNumber("4"), "\"4\" should be recognized as a number.");
        assertTrue(part1Cell2.isNumber("12"), "\"12\" should be recognized as a number.");
        assertTrue(part1Cell3.isNumber("-5"), "\"-5\" should be recognized as a number.");
        assertTrue(part1Cell4.isNumber("3.14"), "\"3.14\" should be recognized as a number.");
        assertTrue(part1Cell5.isNumber("-2.718"), "\"-2.718\" should be recognized as a number.");

        // Invalid cases
        assertFalse(part1Cell6.isNumber(""), "Empty string should not be recognized as a number.");
        assertFalse(part1Cell7.isNumber(null), "Null should not be recognized as a number.");
        assertFalse(part1Cell8.isNumber("-."), "\"-.\" should not be recognized as a number.");
        assertFalse(part1Cell9.isNumber("abc"), "\"abc\" should not be recognized as a number.");
        assertFalse(part1Cell10.isNumber("123abc"), "\"123abc\" should not be recognized as a number.");
        assertFalse(part1Cell11.isNumber("3.14.15"), "\"3.14.15\" should not be recognized as a number.");
        assertFalse(part1Cell12.isNumber("."), "\".\" should not be recognized as a number.");
        assertFalse(part1Cell13.isNumber("5."), "\"5.\" (ending with a dot) should not be recognized as a number.");
    }

    @org.junit.jupiter.api.Test
    void isText() {
        Part1Cell part1Cell = new Part1Cell("Hello");
        Part1Cell part1Cell1 = new Part1Cell("world");
        Part1Cell part1Cell2 = new Part1Cell("Sample123");
        Part1Cell part1Cell3 = new Part1Cell("test_case");
        Part1Cell part1Cell4 = new Part1Cell("Text with spaces");
        Part1Cell part1Cell5 = new Part1Cell("123");
        Part1Cell part1Cell6 = new Part1Cell("=123");
        Part1Cell part1Cell8 = new Part1Cell("=123+A5");
        Part1Cell part1Cell9 = new Part1Cell("48484");


        // Valid text cases
        assertTrue(part1Cell.isText("Hello"), "\"Hello\" should be recognized as valid text.");
        assertTrue(part1Cell1.isText("world"), "\"world\" should be recognized as valid text.");
        assertTrue(part1Cell2.isText("Sample123"), "\"Sample123\" should be recognized as valid text.");
        assertTrue(part1Cell3.isText("test_case"), "\"test_case\" should be recognized as valid text.");
        assertTrue(part1Cell4.isText("Text with spaces"), "\"Text with spaces\" should be recognized as valid text.");

        // Invalid text cases
        assertFalse(part1Cell5.isText("123"), "\"123\" should not be recognized as valid text (it is a number).");
        assertFalse(part1Cell6.isText("=123"), "\"123\" should not be recognized as valid text (it is a number).");
        assertFalse(part1Cell8.isText("=123+A5"), "\"123\" should not be recognized as valid text (it is a number).");
        assertFalse(part1Cell9.isText("48484"), "\"123\" should not be recognized as valid text (it is a number).");


    }

        @org.junit.jupiter.api.Test
    void isForm() {
            Part1Cell part1Cell = new Part1Cell("=1+1");
            Part1Cell part1Cell1 = new Part1Cell("=A4+5");
            Part1Cell part1Cell2 = new Part1Cell("=9*9");
            Part1Cell part1Cell3 = new Part1Cell("=4");
            Part1Cell part1Cell4 = new Part1Cell("Text with spaces");
            Part1Cell part1Cell5 = new Part1Cell("123");
            Part1Cell part1Cell6 = new Part1Cell("sdsad");
            Part1Cell part1Cell8 = new Part1Cell("123+A5");
            Part1Cell part1Cell9 = new Part1Cell("48484");

            assertTrue(part1Cell.isForm("=1+1"));
            assertTrue(part1Cell1.isForm("=A4+5"));
            assertTrue(part1Cell2.isForm("=9*9"));
            assertTrue(part1Cell3.isForm("=4"));


            assertFalse(part1Cell4.isForm("Text with spaces"));
            assertFalse(part1Cell5.isForm("123"));
            assertFalse(part1Cell6.isForm("sdsad"));
            assertFalse(part1Cell8.isForm("123+A5"));
            assertFalse(part1Cell9.isForm("48484"));

    }

    @org.junit.jupiter.api.Test
    void computeForm() {

        Part1Cell part1Cell = new Part1Cell("=1+1");
        Part1Cell part1Cell1 = new Part1Cell("=(6+9)+5");
        Part1Cell part1Cell2 = new Part1Cell("=(9+1)*9");
        Part1Cell part1Cell3 = new Part1Cell("=4");
        Part1Cell part1Cell4 = new Part1Cell("=4+(4+(4+(4+(4))))");

        assertEquals(2.0,part1Cell.computeForm("=1+1"));
        assertEquals(20.0,part1Cell1.computeForm("=(6+9)+5"));
        assertEquals(90.0,part1Cell2.computeForm("=(9+1)*9"));
        assertEquals(4.0,part1Cell3.computeForm("=4"));
        assertEquals(20.0,part1Cell4.computeForm("=4+(4+(4+(4+(4))))"));
    }
}