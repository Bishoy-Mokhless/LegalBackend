package com.kazyonplus.CasesProcuration.model.request.exception;

import java.text.MessageFormat;

public class CaseByNameNotFoundException extends RuntimeException {

    public CaseByNameNotFoundException(final String name) {
        super(MessageFormat.format("Could not find Case with Name : {0}", name));
    }

}
