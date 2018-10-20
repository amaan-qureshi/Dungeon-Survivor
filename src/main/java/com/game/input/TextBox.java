package com.game.input;

import com.game.input.ConsoleReader;

public class TextBox{

    private final ConsoleReader reader;

    private final String title;

    public TextBox(String title) {
        this.reader = new ConsoleReader();
        this.title = title;
    }

    public void showTitle() {
        System.out.println(title);
    }

    public String getValue() {
        return reader.readString();
    }
}
