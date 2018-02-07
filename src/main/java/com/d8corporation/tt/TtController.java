package com.d8corporation.tt;

import com.d8corporation.tt.Data.CardData;
import com.d8corporation.tt.Data.DecryptRequest;
import com.d8corporation.tt.Data.EncryptRequest;
import com.d8corporation.tt.service.CryptException;
import com.d8corporation.tt.service.ICrypt;
import com.d8corporation.tt.service.IVerification;
import com.d8corporation.tt.service.VerificationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TtController {

    private List<CardData> list = new ArrayList<>();
    @Autowired
    IVerification<EncryptRequest> verification;
    @Autowired
    ICrypt crypt;

    private static String getDisplayPan(String pan) {
        return pan.replace(pan.subSequence(6, 12), "-");
    }

    @RequestMapping(value = "/encrypt", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public String encrypt(@RequestBody EncryptRequest request) throws CryptException {
        verification.verify(request);
        CardData cardData = new CardData();
        try {
            crypt.setKey(request.getEncryptionKey());
            cardData.setDisplayPan(getDisplayPan(request.getPan()));
            cardData.setEncryptedExpireDate(crypt.encrypt(request.getExpiryDate()));
            cardData.setEncryptedPan(crypt.encrypt(request.getPan()));
            cardData.setEncryptedVcc(crypt.encrypt(request.getCvv()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new CryptException("encryption error", e);
        }
        list.add(cardData);
        return cardData.getJSON().toString();
    }

    @RequestMapping(value = "/decrypt", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public String decrypt(@RequestBody DecryptRequest request) throws CryptException {
        JSONObject jsonObject = new JSONObject();
        try {
            crypt.setKey(request.getEncryptionKey());
            String decryptedPan = crypt.decrypt(request.getEncryptedPan());
            jsonObject.put("pan", decryptedPan);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CryptException("decryption error", e);
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        JSONArray jsonArray = new JSONArray();
        for (CardData cardData : list) {
            jsonArray.put(cardData.getJSON());
        }
        return jsonArray.toString();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({VerificationException.class})
    public String VerificationError(VerificationException verificationException) {
        return verificationException.getMessage() + verificationException.getList();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({VerificationException.class})
    public String CryptError(CryptException exception) {
        return exception.toString();
    }
}
