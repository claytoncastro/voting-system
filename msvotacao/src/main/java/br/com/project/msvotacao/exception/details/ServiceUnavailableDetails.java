package br.com.project.msvotacao.exception.details;

import lombok.Builder;

public class ServiceUnavailableDetails extends ErrorDetails {

    @Builder
    public ServiceUnavailableDetails(String title, int status, String detail, long timestamp, String developerMessage) {
        super(title, status, detail, timestamp, developerMessage);
    }

}
