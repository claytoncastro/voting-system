package br.com.project.mscooperativa.exception.details;

import lombok.Builder;

public class ResourceAlreadyExistDetails extends ErrorDetails {

    @Builder
    public ResourceAlreadyExistDetails(String title, int status, String detail, long timestamp, String developerMessage) {
        super(title, status, detail, timestamp, developerMessage);
    }

}
