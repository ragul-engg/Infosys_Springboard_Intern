package com.example.model;

public class RequestDTO {
    private String containerName;
    private String blobName;
    public RequestDTO() {

    }
    public RequestDTO(String containerName, String blobName) {
        this.containerName = containerName;
        this.blobName = blobName;
    }
    public String getBlobName() {
        return blobName;
    }

    public void setBlobName(String blobName) {
        this.blobName = blobName;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    @Override
    public  String toString() {
        return getContainerName();
    }
}
