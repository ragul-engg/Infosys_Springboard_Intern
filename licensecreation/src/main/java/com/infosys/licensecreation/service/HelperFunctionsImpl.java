package com.infosys.licensecreation.service;

import com.infosys.licensecreation.model.Constraint;
import com.infosys.licensecreation.model.License;
import com.infosys.licensecreation.model.SoftwareModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
public class HelperFunctionsImpl implements HelperFunctions {

    @Autowired
    private  SecretKeySpec secretKey;
    @Value("${application.softwareNames}")
    private List<String> softwareNames;
    
    @Override
    public boolean validateLicense(License license) {
        boolean returnState=true;
        returnState=returnState &
                license.getSoftwareVersion()!=null &
                license.getVendorName()!=null &
                license.getVendorUrl()!=null &
                license.getGrantedTo()!=null &
                license.getLicensedTo()!=null &
                license.getPurpose()!=null;
        return returnState && validateSoftwareName(license.getSoftwareName()) &&
                validateSoftwareModules(license.getSoftwareModules());
    }

    @Override
    public boolean validateSoftwareName(String strVal) {
        return softwareNames.contains(strVal);
    }

    @Override
    public boolean validateConstraints(List<Constraint> constraintList) {
        if(constraintList.size()<2) {
            return false;
        }
        Constraint dateFrom=constraintList.get(0);
        Constraint dateTo=constraintList.get(1);
        if(dateFrom.getOperator().equals("DateFromOrAfter") && dateTo.getOperator().equals("DateUpto")) {
            LocalDateTime fromTime=LocalDateTime.parse(dateFrom.getValue());
            LocalDateTime toTime=LocalDateTime.parse(dateTo.getValue());
            return fromTime.isBefore(toTime);
        }
        return false;
    }

    @Override
    public boolean validateSoftwareModules(List<SoftwareModule> softwareModuleList) {
        boolean returnState=true;
        for(SoftwareModule softwareModule:softwareModuleList) {
            returnState=returnState &
                        softwareModule.getModuleName()!=null &
                            softwareModule.getModuleVersion()!=null &
                                validateConstraints(softwareModule.getConstraints());
        }
        return returnState;
    }

    @Override
    public String encryptString(String strToEncrypt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String decryptString(String secretMsg) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(secretMsg)));
    }
}
