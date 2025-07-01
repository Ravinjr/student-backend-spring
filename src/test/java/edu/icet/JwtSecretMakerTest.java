package edu.icet;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class JwtSecretMakerTest {
    @Test
    public void generateJwtSecret() {
        SecretKey secretKey = Jwts.SIG.HS512.key().build();
        String encodedKey = DatatypeConverter.printBase64Binary(secretKey.getEncoded());
        System.out.printf("\nKey = [%s]\n",encodedKey);
    }
}
