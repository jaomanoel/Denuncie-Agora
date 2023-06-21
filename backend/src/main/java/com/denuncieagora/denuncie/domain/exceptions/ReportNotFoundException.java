package com.denuncieagora.denuncie.domain.exceptions;

public class ReportNotFoundException extends RuntimeException {

    public ReportNotFoundException(String msg) {
        super(msg);
    }
}
