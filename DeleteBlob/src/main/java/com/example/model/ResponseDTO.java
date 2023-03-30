package com.example.model;

public class ResponseDTO {
    private String response;

    public ResponseDTO() {

    }
    public ResponseDTO(String response) {
        this.response = response;
    }

    public String getResult() {
        return response;
    }

    public void setResult(String response) {
        this.response = response;
    }
}