package com.d8corporation.tt.service;

import com.d8corporation.tt.Data.EncryptRequest;
import com.d8corporation.tt.service.IVerification;
import org.apache.commons.validator.routines.CreditCardValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Verification implements IVerification<EncryptRequest> {

    private static final Pattern PAN_PATTERN = Pattern.compile("[1-9]\\d{15}");
    private static final Pattern EXPIRE_DATE_PATTERN = Pattern.compile("(?:0[1-9]|1[0-2])/\\d{2}");
    private static final Pattern CVV_PATTERN = Pattern.compile("\\d{3}");

    public static boolean verifyPan(String pan) {
        if (pan == null) return false;
        //CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.MASTERCARD);
        //return  ccv.isValid(pan);
        return PAN_PATTERN.matcher(pan).matches();
    }

    public static boolean verifyCVV(String cvv) {
        return cvv != null && CVV_PATTERN.matcher(cvv).matches();
    }

    public static boolean verifyExpireDate(String expireDate) {
        return expireDate != null && EXPIRE_DATE_PATTERN.matcher(expireDate).matches();
    }

    @Override
    public void verify(EncryptRequest encryptRequest) {
        if (encryptRequest == null) throw new VerificationException("there is no request for verification");
        List<String> list = new ArrayList<>();
        if (!verifyCVV(encryptRequest.getCvv())) {
            list.add("wrong cvv");
        }
        if (!verifyPan(encryptRequest.getPan())) {
            list.add("wrong pan");
        }
        if (!verifyExpireDate(encryptRequest.getExpiryDate())) {
            list.add("wrong expire date");
        }
        if (encryptRequest.getEncryptionKey() == null || encryptRequest.getEncryptionKey().isEmpty()) {
            list.add("there is no encryption key");
        }
        if (!list.isEmpty()) {
            VerificationException verificationException = new VerificationException("verification errors:");
            for (String error : list) {
                verificationException.getList().add(error);
            }
            throw verificationException;
        }
    }
}
