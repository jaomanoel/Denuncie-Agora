package com.denuncieagora.denuncie.domain.exceptions;

public class StateInvalidException extends RuntimeException{

    public StateInvalidException(String message) {
        super(message);
    }
}
