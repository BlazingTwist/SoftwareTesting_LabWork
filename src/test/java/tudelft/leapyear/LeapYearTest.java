package tudelft.leapyear;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeapYearTest {
    // divisable by four, not divisable by 100
    @Test
    public void leapYearsThatAreNonCenturialYears() {
        LeapYear ly = new LeapYear();
        boolean result = ly.isLeapYear(2016);
        Assertions.assertTrue(result);
    }

    // divisable by 4, 100, and 400
    @Test
    public void leapCenturialYears() {
        LeapYear ly = new LeapYear();
        Assertions.assertTrue(ly.isLeapYear(2000));
    }

    // divisable by 4 and 100, not divisiable by 400
    @Test
    public void nonLeapCenturialYears() {
        LeapYear ly = new LeapYear();
        Assertions.assertFalse(ly.isLeapYear(1500));
    }

    // not divisable by 4
    @Test
    public void nonLeapYears() {
        LeapYear ly = new LeapYear();
        Assertions.assertFalse(ly.isLeapYear(2017));
    }
}
