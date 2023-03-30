package com.infosys.licensecreation.service;

import com.infosys.licensecreation.model.License;
import com.infosys.licensecreation.model.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CreateLicense implements Function<License,ResponseDTO> {
    @Autowired
    private HelperFunctionsImpl helperFunctions;
    @Autowired
    private StorageHandler storageHandler;
    @Value("${secret_key}")
    private String secretKey;

    public ResponseDTO apply(License license) {
        if(helperFunctions.validateLicense(license)) {
            try {
                String encryptedString=helperFunctions.encryptString(license.toString());
                encryptedString=encryptedString+"."+secretKey;
                storageHandler.uploadFile(encryptedString);
                return new ResponseDTO(encryptedString);
            }catch(Exception e) {
                return new ResponseDTO(e.getLocalizedMessage()+"\n"+e.getMessage() +"\nInternal Server Error");
            }
        }
        return new ResponseDTO("Error while validating the License ");
    }
}
