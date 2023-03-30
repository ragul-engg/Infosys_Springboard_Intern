package com.example;

import com.example.model.*;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import java.util.Optional;

public class DeleteBlobHandler extends FunctionInvoker<RequestDTO,String> {
    @FunctionName("deleteBlob")
    public HttpResponseMessage delete(
            @HttpTrigger(name="request",methods = {HttpMethod.DELETE},authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<RequestDTO>> request,
            ExecutionContext context
    ) {
        RequestDTO RequestDTO = request.getBody()
                .filter((r -> r.getContainerName() != null))
                .orElseGet(() -> new RequestDTO(
                        request.getQueryParameters()
                                .getOrDefault("containerName", "files"),
                        request.getQueryParameters()
                                .getOrDefault("blobName","img.png")));
        context.getLogger().info("Deleting Blob");
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(RequestDTO,context))
                .header("Content-Type","application/json")
                .build();
    }
}
