package com.kazyonplus.CasesProcuration.model.request.exception;

import java.text.MessageFormat;

public class SessionIsAlreadyAssignedException extends RuntimeException{
    public SessionIsAlreadyAssignedException(final Long IdSession, final Long IdCase){
        super(MessageFormat.format("Session {0} is already assigned to Case {1}",IdSession,IdCase));
    }
}
