package com.springboot.star_wars_character_popularity.app.exception;

import java.io.IOException;

public class FileNotSavedException extends Exception{

    public FileNotSavedException(String message, IOException exception) {
        super(message, exception);
    }
}
