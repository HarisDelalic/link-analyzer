package com.haris.linkanalyzer.exception;

public class LinkNotFoundException extends RuntimeException {

    public LinkNotFoundException() {
        super("Link not found");
    }
}
