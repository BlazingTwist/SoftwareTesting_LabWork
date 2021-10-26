package tudelft.chocolate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChocolateBagsLectureTest {

    @Test
    public void smallAndBigBars() {
        ChocolateBags bags = new ChocolateBags();
        int result = bags.calculate(2,3,17);
        Assertions.assertEquals(2, result);
    }

    @Test
    public void tooFewBars() {
        int result = new ChocolateBags().calculate(1,1,10);
        Assertions.assertEquals(-1, result);
    }

    @Test
    public void onlyBigBars() {
        int result = new ChocolateBags().calculate(4,3,10);
        Assertions.assertEquals(0, result);
    }

    @Test
    public void invalidInput() {
        int result = new ChocolateBags().calculate(-1,3,-1);
        Assertions.assertEquals(-1, result);
    }
}
