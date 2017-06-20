package com.sonego.interviewQuestions.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptData {

	public static String encryptPassword(String pwd) throws Exception {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(pwd.getBytes());
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String hashGenerated = bigInt.toString(16);
			return hashGenerated.toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			throw e;
		}
	}

}
