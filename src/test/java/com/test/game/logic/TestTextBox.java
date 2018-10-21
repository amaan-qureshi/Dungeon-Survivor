package com.test.game.logic;

import com.game.components.input.util.TextBox;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class TestTextBox {

    @Test
    public void testPlayerMovementOnMaps() {
        String test = "Test Text";
        String testTitle = "Test Title";
        ByteArrayInputStream in = new ByteArrayInputStream(test.getBytes());
        System.setIn(in);
        System.setIn(System.in);
        TextBox textBox = new TextBox(testTitle);
        assert(textBox.getValue().equals(test));
    }

}
