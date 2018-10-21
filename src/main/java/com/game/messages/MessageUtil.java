package com.game.messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageUtil {
    private static final Properties messages;
    static{
        messages = new Properties();
        try(InputStream input =  MessageUtil.class.getClassLoader().getResourceAsStream("messages.properties") ) {
            messages.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static String getMessage(String key, Object... args){
        String message = messages.getProperty(key);
        if(message!=null) {
            return String.format(message, args);
        }
        else{
            return null;
        }
    }
}