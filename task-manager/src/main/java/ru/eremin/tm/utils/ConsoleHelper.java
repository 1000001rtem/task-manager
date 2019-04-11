package ru.eremin.tm.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @autor av.eremin on 11.04.2019.
 */

public class ConsoleHelper {

    @NotNull
    private final Scanner scanner;

    public ConsoleHelper(@NotNull final Scanner scanner) {
        this.scanner = scanner;
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
    public Date getDateFieldFromConsole(@Nullable final String field) {
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
    private Date getDateFromString(@NotNull final String dateString) {
        @NotNull final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
