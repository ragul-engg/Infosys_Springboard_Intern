package com.infosys.licensecreation.handler;

import com.infosys.licensecreation.model.License;
import com.infosys.licensecreation.model.ResponseDTO;
import com.infosys.licensecreation.service.HelperFunctionsImpl;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import java.util.Optional;

public class LicenseCreationHandler extends FunctionInvoker<License, ResponseDTO> {
    @FunctionName("createLicense")
    public HttpResponseMessage createLicense(
            @HttpTrigger(name="request",methods={HttpMethod.POST},authLevel = AuthorizationLevel.ANONYMOUS)HttpRequestMessage<Optional<License>> request,
            ExecutionContext context
            ) {
        Optional<License> license=request.getBody();
        context.getLogger().info(license.toString());
        return request.createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(license.get(), context))
                .header("Content-Type", "application/json")
                .build();
    }
}
