package com.d8corporation.tt.service;

import java.util.ArrayList;
import java.util.List;

public class VerificationException extends RuntimeException{

    private List<String> list = new ArrayList<>();

    VerificationException(String message) {
        super(message);
    }

    public VerificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerificationException(Throwable cause) {
        super(cause);
    }

    public List<String> getList() {
        return list;
    }

}
