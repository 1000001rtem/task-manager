package ru.eremin.tm;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import ru.eremin.tm.utilities.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Unit test for simple App.
 */

public class AppTest {

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void regexTest(){
        final String [] rightDates = {
                "01.01.1900",
                "01.12.1000",
                "31.01.1900",
                "09.09.1900"
        };

        final String [] wrongDates = {
                "00.01.1900",
                "01.00.1900",
                "32.01.1900",
                "01.13.1900"
        };

        for (final String wrongDate : wrongDates) {
            assertFalse(wrongDate.matches(DateUtils.DATE_REGEX));
        }

        for (final String rightDate : rightDates) {
            assertTrue(rightDate.matches(DateUtils.DATE_REGEX));
        }
    }
    
}
