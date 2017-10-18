package ua.nure.ivanenko.Practice3;

/* Create method hash(String input, String algorithm) 
 * The output must represent a string of hexadecimal digits: 
 * each byte of corresponds to two hexadecimal digits
 */

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.regex.*;

public class Part4 {

	public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder();
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		try {
			digest.update(input.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
		}
		byte[] hash = digest.digest();

		Pattern pattern = Pattern.compile("(\\d{1,8})$");

		for (byte b : hash) {
			Matcher matcher = pattern.matcher(Integer.toBinaryString(b));
			while (matcher.find()) {
				if (matcher.group().length() < 5) {
					sb.append(0);
				}
				sb.append(Integer.toHexString(Integer.parseInt(matcher.group(), 2)).toUpperCase());
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(hash("password", "SHA-256"));
		System.out.println(hash("passwort", "SHA-256"));
	}
}
