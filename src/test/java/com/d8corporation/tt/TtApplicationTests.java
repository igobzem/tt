package com.d8corporation.tt;

import com.d8corporation.tt.service.Crypt;
import com.d8corporation.tt.service.ICrypt;
import com.d8corporation.tt.service.Verification;
import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class TtApplicationTests {


	@Test
	public void verificationTest() {

		Assert.assertEquals(Verification.verifyCVV(null), false);
		Assert.assertEquals(Verification.verifyCVV("123"), true);
		Assert.assertEquals(Verification.verifyCVV("1q2"), false);
		Assert.assertEquals(Verification.verifyCVV("01"), false);

		Assert.assertEquals(Verification.verifyPan("5484010018947419"), true);
		Assert.assertEquals(Verification.verifyPan("548401001894741"), false);
		Assert.assertEquals(Verification.verifyPan("548401001894741O"), false);

		Assert.assertEquals(Verification.verifyExpireDate("03/19"), true);
		Assert.assertEquals(Verification.verifyPan(null), false);
		Assert.assertEquals(Verification.verifyPan("1111"), false);
		Assert.assertEquals(Verification.verifyPan("00/11"), false);
		Assert.assertEquals(Verification.verifyPan("13/21"), false);
	}

	@Test
    public void cryptTest() throws UnsupportedEncodingException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        ICrypt crypt = new Crypt();
        crypt.setKey("qwwerfwefwe");
        String str = "adasdsafdf";
        String enStr = crypt.encrypt(str);
        System.out.println("encrypted ="+enStr);
        Assert.assertEquals(crypt.decrypt(enStr), str);
    }

}
