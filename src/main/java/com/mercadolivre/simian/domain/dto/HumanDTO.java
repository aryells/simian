package com.mercadolivre.simian.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Getter
@Setter
public class HumanDTO implements Serializable {

    private static final long serialVersionUID = -3417195992840583698L;

    private List<String> dna;
    private Boolean isSimian;

    public static String getMD5Hash(HumanDTO humanDTO) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        final String dnsStr = String.join(",", humanDTO.getDna());
        messageDigest.update(dnsStr.getBytes(), 0, dnsStr.length());
        byte[] digest = messageDigest.digest();
        return new BigInteger(1, digest).toString(64);
    }
}
