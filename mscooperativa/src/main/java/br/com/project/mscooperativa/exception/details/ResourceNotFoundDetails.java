package br.com.project.mscooperativa.exception.details;

import lombok.Builder;

public class ResourceNotFoundDetails extends ErrorDetails {

    @Builder
    public ResourceNotFoundDetails(String title, int status, String detail, long timestamp, String developerMessage) {
        super(title, status, detail, timestamp, developerMessage);
    }

}
