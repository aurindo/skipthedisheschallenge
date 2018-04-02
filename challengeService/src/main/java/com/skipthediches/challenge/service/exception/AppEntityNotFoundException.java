package com.skipthediches.challenge.service.exception;

public class AppEntityNotFoundException extends Exception {

    public AppEntityNotFoundException(Class clazz, Long id) {
        super(
                new StringBuilder("It is not possible found ").
                        append(clazz.getSimpleName()).
                        append(", id: ").
                        append(id).toString());
    }

}
