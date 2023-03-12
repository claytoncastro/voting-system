package br.com.project.mscooperativa.exception.details;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ValidationErrorDetails extends ErrorDetails {

    private final String field;
    private final String fieldMessage;

    @Builder
    public ValidationErrorDetails(String title, int status, String detail, long timestamp,
                                  String developerMessage, String field, String fieldMessage) {
        super(title, status, detail, timestamp, developerMessage);
        this.field = field;
        this.fieldMessage = fieldMessage;
    }

}
