package ru.eremin.tm.backend.model.dto.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @autor av.eremin on 18.04.2019.
 */

public class DateAdapter extends XmlAdapter<String, Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public Date unmarshal(final String date) throws Exception {
        return dateFormat.parse(date);
    }

    @Override
    public String marshal(final Date date) throws Exception {
        return dateFormat.format(date);
    }

}
