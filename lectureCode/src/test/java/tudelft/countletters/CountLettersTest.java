package tudelft.countletters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;

public class CountLettersTest {

    @Test
    public void multipleMatchingWords() {
        int words = new CountLetters().count("cats|dogs");
        Assertions.assertEquals(2, words);
    }

    @Test
    public void lastWordDoesNotMatch() {
        int words = new CountLetters().count("catr|dog");
        Assertions.assertEquals(1, words);
    }

    /**
     * This test is just for complete condition coverage.
     * REMOVE FOR LECTURE, IF USING THIS QUESTION IN EXAM!!
     */
/*    @Test
    public void firstWordIsNoWord() {
        int words = new CountLetters().count("cat|dogr");
        Assertions.assertEquals(1, words);
    }*/

}