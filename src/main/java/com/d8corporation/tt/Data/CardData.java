package com.d8corporation.tt.Data;

import org.json.JSONException;
import org.json.JSONObject;
// cd
public class CardData {

    private String displayPan;
    private String encryptedPan;
    private String encryptedExpireDate;
    private String encryptedVcc;

    public CardData() {
    }

    public String getDisplayPan() {
        return displayPan;
    }

    public void setDisplayPan(String displayPan) {
        this.displayPan = displayPan;
    }

    public String getEncryptedPan() {
        return encryptedPan;
    }

    public void setEncryptedPan(String encryptedPan) {
        this.encryptedPan = encryptedPan;
    }

    public String getEncryptedExpireDate() {
        return encryptedExpireDate;
    }

    public void setEncryptedExpireDate(String encryptedExpireDate) {
        this.encryptedExpireDate = encryptedExpireDate;
    }

    public String getEncryptedVcc() {
        return encryptedVcc;
    }

    public void setEncryptedVcc(String encryptedVcc) {
        this.encryptedVcc = encryptedVcc;
    }

    public JSONObject getJSON()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("displayPan", displayPan);
            jsonObject.put("encryptedPan", encryptedPan);
            jsonObject.put("encryptedExpireDaten", encryptedExpireDate);
            jsonObject.put("encryptedVcc", encryptedVcc);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
