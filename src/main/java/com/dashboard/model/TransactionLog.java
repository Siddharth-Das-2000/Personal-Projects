package com.dashboard.model;

public class TransactionLog {

    private Long id;
    private String payload;
    private String timestamp;
    private boolean error;

    public TransactionLog() {
    }

    public TransactionLog(Long id,
                          String payload,
                          String timestamp,
                          boolean error) {

        this.id = id;
        this.payload = payload;
        this.timestamp = timestamp;
        this.error = error;
    }

    public Long getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isError() {
        return error;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}