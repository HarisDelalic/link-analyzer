package com.haris.linkanalyzer.exception;

public class CannotFindAnyTagOnLinkException extends RuntimeException {

    public CannotFindAnyTagOnLinkException() {
        super("Cannot find any tag on provided link");
    }
}
