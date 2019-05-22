package com.sap.ariba.cts.response;

public class BaseResponse {
    public enum Status{OK, ERROR}
    
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    
}
