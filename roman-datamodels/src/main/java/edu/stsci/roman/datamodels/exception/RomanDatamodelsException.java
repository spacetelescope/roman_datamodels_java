package edu.stsci.roman.datamodels.exception;

/**
 * Exception thrown when this library cannot handle the content
 * of an ASDF file.
 */
public class RomanDatamodelsException extends RuntimeException {
    public RomanDatamodelsException(final String message) {
        super(message);
    }
}
