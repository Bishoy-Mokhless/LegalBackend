package com.kazyonplus.CasesProcuration.model.request.exception;

import java.text.MessageFormat;

public class SessionNotFoundException extends RuntimeException {

    public SessionNotFoundException(final Long id){
        super(MessageFormat.format("Could not find Session with id: {0}", id));
    }
}
