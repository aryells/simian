package com.mercadolivre.simian.domain.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.stream.Collectors;

public class HumanDTO implements Serializable {

	private static final long serialVersionUID = -3417195992840583698L;

	private List<String> dna;
    private Boolean isSimian;

	public List<String> getDna() {
		return dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	}

	public Boolean getIsSimian() {
		return isSimian;
	}

	public void setIsSimian(Boolean isSimian) {
		this.isSimian = isSimian;
	}

    public static String getMD5Hash(HumanDTO humanDTO) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        final String dnsStr = humanDTO.getDna().stream().collect(Collectors.joining(","));
        messageDigest.update(dnsStr.getBytes(), 0, dnsStr.length());
        byte[] digest = messageDigest.digest();
        return new BigInteger(1, digest).toString(64);
    }
}
