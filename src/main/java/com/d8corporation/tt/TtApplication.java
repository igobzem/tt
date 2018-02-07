package com.d8corporation.tt;

import com.d8corporation.tt.Data.EncryptRequest;
import com.d8corporation.tt.service.Crypt;
import com.d8corporation.tt.service.ICrypt;
import com.d8corporation.tt.service.IVerification;
import com.d8corporation.tt.service.Verification;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
