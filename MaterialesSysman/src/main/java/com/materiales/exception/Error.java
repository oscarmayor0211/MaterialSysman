package com.materiales.exception;

import lombok.Data;

import java.util.Date;
@Data
public class Error {
    private String status;
    private Date timestamp;
    private String message;
    private String details;

    public Error(String status, Date timestamp, String message, String details) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Error() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
