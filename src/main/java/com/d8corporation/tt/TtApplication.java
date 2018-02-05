package com.d8corporation.tt;

import com.d8corporation.tt.Data.EncryptRequest;
import com.d8corporation.tt.service.Crypt;
import com.d8corporation.tt.service.ICrypt;
import com.d8corporation.tt.service.IVerification;
import com.d8corporation.tt.service.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TtApplication {


    public static void main(String[] args) {
        SpringApplication.run(TtApplication.class, args);
    }


    @Bean
    public IVerification<EncryptRequest> verification() {
        return new Verification();
    }

    @Bean
    public ICrypt crypt() {
        return new Crypt();
    }
}
