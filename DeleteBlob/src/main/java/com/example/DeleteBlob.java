package com.example;

import com.example.model.*;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class DeleteBlob implements Function<Mono<RequestDTO>,Mono<ResponseDTO>> {
    @Autowired
    private Environment environment;
    public Mono<ResponseDTO> apply(Mono<RequestDTO> blob) {
        return blob.map(reqBody -> {
            boolean isDeleted=false;
            try {
                CloudStorageAccount cloudStorageAccount = CloudStorageAccount.parse(environment.getProperty("azure.storage.ConnectionString"));
                CloudBlobClient cloudBlobClient = cloudStorageAccount.createCloudBlobClient();
                CloudBlobContainer container = cloudBlobClient.getContainerReference(reqBody.getContainerName());
                CloudBlockBlob blobToBeDeleted = container.getBlockBlobReference(reqBody.getBlobName());
                isDeleted=blobToBeDeleted.deleteIfExists();
            }catch (Exception e) {
                e.printStackTrace();
            }
            if(isDeleted) {
                return new ResponseDTO("Deleted the blob object");
            }
            return new ResponseDTO("File not found");
        });
    }
}
