package com.infosys.licensecreation.service;

import com.infosys.licensecreation.model.Constraint;
import com.infosys.licensecreation.model.License;
import com.infosys.licensecreation.model.SoftwareModule;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface HelperFunctions {
    boolean validateLicense(License license);
    boolean validateSoftwareModules(List<SoftwareModule> softwareModuleList);
    String encryptString(String strVal) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException;
    String decryptString(String strVal) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
    boolean validateSoftwareName(String strVal);
    boolean validateConstraints(List<Constraint> constraintList);
}
