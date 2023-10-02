package com.bank.document.exception;

public class NotSaveException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    public NotSaveException() {
        super();
    }

    public NotSaveException(final String message) {
        super(message);
    }
}
