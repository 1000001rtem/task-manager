package ru.eremin.tm;


import org.jetbrains.annotations.NotNull;

public class Application {
    public static void main(String[] args) {
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}
