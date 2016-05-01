package com.shockn745.data.blogpost.file;

/**
 * @author Kempenich Florian
 */
class TwoPostWithSameIdException extends RuntimeException {
    TwoPostWithSameIdException() {
    }

    public TwoPostWithSameIdException(String message) {
        super(message);
    }

    public TwoPostWithSameIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public TwoPostWithSameIdException(Throwable cause) {
        super(cause);
    }
}
