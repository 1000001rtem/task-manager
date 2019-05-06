package ru.eremin.tm.client.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.client.util.DateUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * @autor av.eremin on 11.04.2019.
 */

@NoArgsConstructor
public class ConsoleService {

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    public String getNextLine() {
        return scanner.nextLine();
    }

    @NotNull
    public String getStringFieldFromConsole(@Nullable final String field) {
        if (field == null || field.isEmpty()) throw new NullPointerException("wrong method attribute");
        String name;
        boolean flag;
        do {
            System.out.println("*** Please write " + field + " ***");
            flag = true;
            name = scanner.nextLine();
            if (name == null || name.isEmpty()) {
                System.out.println("*** " + field + " can't be empty ***");
                flag = false;
            }
        } while (!flag);
        return name;
    }

    @Nullable
    public XMLGregorianCalendar getDateFieldFromConsole(@Nullable final String field) {
        if (field == null || field.isEmpty()) throw new NullPointerException("wrong method attribute");
        String deadline;
        boolean flag;
        do {
            flag = true;
            System.out.println("*** Please write " + field + " in format dd.mm.yyyy ***");
            deadline = scanner.nextLine();
            if (!deadline.matches(DateUtils.DATE_REGEX)) {
                System.out.println("*** Wrong format ***");
                flag = false;
            }
        } while (!flag);
        return getDateFromString(deadline);
    }

    @Nullable
    private XMLGregorianCalendar getDateFromString(@NotNull final String dateString) {
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        @NotNull final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        XMLGregorianCalendar date = null;
        try {
            gregorianCalendar.setTime(simpleDateFormat.parse(dateString));
            date = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException | ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
