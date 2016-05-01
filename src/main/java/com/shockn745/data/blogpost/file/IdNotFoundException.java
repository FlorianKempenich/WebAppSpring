package com.shockn745.data.blogpost.file;

/**
 * @author Kempenich Florian
 */
class IdNotFoundException extends Exception {
    IdNotFoundException() {
    }

    public IdNotFoundException(String message) {
        super(message);
    }

    public IdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdNotFoundException(Throwable cause) {
        super(cause);
    }
}
