package ru.eremin.tm.client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import ru.eremin.tm.server.endpoint.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Unit test for simple Application.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test() throws IncorrectDataException_Exception, AccessForbiddenException_Exception, DatatypeConfigurationException, ParseException {
    }
}
