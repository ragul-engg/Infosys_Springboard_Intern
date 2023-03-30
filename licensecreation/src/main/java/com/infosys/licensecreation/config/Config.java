package com.infosys.licensecreation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class Config {
    private final Environment environment;

    public Config(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public PublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKey=environment.getProperty("public_key");
        byte[] publicKeyBytes=Base64.getDecoder().decode(publicKey);
        EncodedKeySpec x509EncodedKeySpec=new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }
    @Bean
    public PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKey=environment.getProperty("private_key");
        byte[] privateKeyBytes=Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec x509EncodedKeySpec=new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(x509EncodedKeySpec);
    }
}
