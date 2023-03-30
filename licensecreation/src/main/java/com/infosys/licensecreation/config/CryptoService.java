package com.infosys.licensecreation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

@Component
public class CryptoService {
    @Autowired
    private PrivateKey privateKey;
    @Value("${secret_key}")
    private String encryptedSecretKey;
    private static byte[] key;
    @Bean
    public SecretKeySpec setKey() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        Cipher decryptRSA= Cipher.getInstance("RSA");
        decryptRSA.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] encryptedKeyBytes=Base64.getDecoder().decode(encryptedSecretKey);
        byte[] decryptedKeyBytes=decryptRSA.doFinal(encryptedKeyBytes);
        String secretKey= Base64.getEncoder().encodeToString(decryptedKeyBytes);
        key=secretKey.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha=MessageDigest.getInstance("SHA-256");
        key=sha.digest(key);
        key= Arrays.copyOf(key,16);
        return new SecretKeySpec(key,"AES");
    }
}
