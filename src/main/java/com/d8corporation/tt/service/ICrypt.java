package com.d8corporation.tt.service;

import com.d8corporation.tt.Data.CardData;
import com.d8corporation.tt.Data.EncryptRequest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface ICrypt {

    void setKey(String encryptionKey) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    String encrypt(String value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException;

    String decrypt(String value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException;
}
