package com.haris.linkanalyzer.exception;

public class FaultyLinkException extends RuntimeException {

    public FaultyLinkException() {
        super("Link that You have provided is faulty");
    }
}
