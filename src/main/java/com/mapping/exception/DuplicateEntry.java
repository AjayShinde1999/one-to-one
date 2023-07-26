package com.mapping.exception;

public class DuplicateEntry extends RuntimeException{

    public DuplicateEntry(String message){
        super(message);
    }
}
