package com.d8corporation.tt.Data;

public class DecryptRequest {

    private String encryptedPan;
    private String encryptionKey;

    public String getEncryptedPan() {
        return encryptedPan;
    }

    public void setEncryptedPan(String encryptedPan) {
        this.encryptedPan = encryptedPan;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    @Override
    public String toString() {
        return "DecryptRequest{" +
                "encryptedPan='" + encryptedPan + '\'' +
                ", encryptionKey='" + encryptionKey + '\'' +
                '}';
    }
}
