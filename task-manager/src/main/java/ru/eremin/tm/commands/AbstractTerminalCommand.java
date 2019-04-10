package ru.eremin.tm.commands;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.bootstrap.Bootstrap;
import ru.eremin.tm.utils.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @autor av.eremin on 10.04.2019.
 */

public abstract class AbstractTerminalCommand {

    @NotNull
    protected Bootstrap bootstrap;

    public AbstractTerminalCommand(@NotNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract void execute();

    @NotNull
    protected String getStringFieldFromConsole(@Nullable final String field) {
        if (field == null || field.isEmpty()) throw new NullPointerException("wrong method attribute");
        String name;
        boolean flag;
        do {
            System.out.println("*** Please write " + field + " ***");
            flag = true;
            name = bootstrap.getScanner().nextLine();
            if (name == null || name.isEmpty()) {
                System.out.println("*** " + field + " can't be empty ***");
                flag = false;
            }
        } while (!flag);
        return name;
    }

    @Nullable
    protected Date getDateFieldFromConsole(@Nullable final String field) {
        if (field == null || field.isEmpty()) throw new NullPointerException("wrong method attribute");
        String deadline;
        boolean flag;
        do {
            flag = true;
            System.out.println("*** Please write " + field + " in format dd.mm.yyyy ***");
            deadline = bootstrap.getScanner().nextLine();
            if (!deadline.matches(DateUtils.DATE_REGEX)) {
                System.out.println("*** Wrong format ***");
                flag = false;
            }
        } while (!flag);
        return getDateFromString(deadline);
    }

    @Nullable
    protected Date getDateFromString(@NotNull final String dateString) {
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
