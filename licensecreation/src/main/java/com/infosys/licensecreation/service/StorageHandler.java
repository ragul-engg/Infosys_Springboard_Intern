package com.infosys.licensecreation.service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

@Service
public class StorageHandler {
    private static long fileNameCtr=1;
    @Autowired
    private Environment environment;

    public void uploadFile(String encryptedString) throws URISyntaxException, InvalidKeyException, StorageException, IOException {
        CloudStorageAccount cloudStorageAccount=CloudStorageAccount.parse(environment.getProperty("azure.storage.ConnectionString"));
        CloudBlobClient cloudBlobClient=cloudStorageAccount.createCloudBlobClient();
        CloudBlobContainer cloudBlobContainer=cloudBlobClient.getContainerReference(environment.getProperty("azure.storage.ContainerName"));

        CloudBlockBlob blob = cloudBlobContainer.getBlockBlobReference(String.format("license%d.txt",fileNameCtr++));
        InputStream inputStream=new ByteArrayInputStream(encryptedString.getBytes());
        blob.upload(inputStream,encryptedString.getBytes().length);
    }
}
