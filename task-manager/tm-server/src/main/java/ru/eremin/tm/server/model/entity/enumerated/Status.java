package ru.eremin.tm.server.model.entity.enumerated;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * @autor av.eremin on 15.04.2019.
 */

@Getter
@XmlEnum
@XmlType(name = "status")
public enum Status {

    PLANNED("planned"),

    DURING("during"),

    DONE("done");

    @NotNull
    private String displayName;

    Status(@NotNull final String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

}
