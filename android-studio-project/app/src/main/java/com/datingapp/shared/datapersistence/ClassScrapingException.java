package com.datingapp.shared.datapersistence;
/**
 * This class is a subclass of RuntimeException used for errors when "scraping" classes.
 *
 * @author Jonathan Cooper
 * @version sep-24-2018
 */

public class ClassScrapingException extends RuntimeException {
    /**
     * Constructs a generic ClassScrapingException with no message.
     */
    public ClassScrapingException() {
        super();
    }

    /**
     * Constructs a generic ClassScrapingException with a message.
     * @param _message Message for exception.
     */
    public ClassScrapingException(String _message) {
        super(_message);
    }

    /**
     * Constructs a generic ClassScrapingException with a message and a stack trace.
     * @param _message Message for the exception.
     * @param _parent Exception to inherit a stack trace from.
     */
    public ClassScrapingException(String _message, Exception _parent) {
        super(_message, _parent);
    }
}
