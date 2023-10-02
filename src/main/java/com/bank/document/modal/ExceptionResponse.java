package com.bank.document.modal;

import lombok.Data;

@Data
public class ExceptionResponse {

    private String errorMessage;
    private String requestedURI;

}
