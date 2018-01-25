package com.d8corporation.tt.service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static org.apache.tomcat.util.codec.binary.Base64.*;

public class Crypt implements ICrypt {

    private SecretKeySpec secretKey;
    private byte[] key;

    @Override
    public void setKey(String encryptionKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        key = encryptionKey.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 32);
        secretKey = new SecretKeySpec(key, "AES");
    }

    @Override
    public String encrypt(String value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return encodeBase64String(cipher.doFinal(value.getBytes("UTF-8")));
    }

    @Override
    public String decrypt(String value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(decodeBase64(value)));
    }

 }
