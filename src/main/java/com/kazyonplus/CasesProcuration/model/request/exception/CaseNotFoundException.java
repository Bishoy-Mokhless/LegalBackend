package com.kazyonplus.CasesProcuration.model.request.exception;

import java.text.MessageFormat;

public class CaseNotFoundException extends RuntimeException {

    public CaseNotFoundException(final Long id){
        super(MessageFormat.format("Could not find Case with id: {0}", id));
    }
}
