
package com.server.sf.server_user.tool;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {

	// ?????��??�?
	public static byte[] publicEncrypt(PublicKey key, String str) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(str.getBytes("UTF8"));
	}

	public static String publicEncrypt(String key, String str) throws Exception {
		PublicKey publicKey = getPublicKey(key);
		byte[] data = publicEncrypt(publicKey, str);
		return Base64.encode(data);
	}

	// ?????�解�?
	public static byte[] publicDECRYPT(PublicKey key, byte[] data) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(data);
	}

//	public static String publicDECRYPT(String key, String str) throws Exception {
//		PublicKey publicKey = getPublicKey(key);
//		byte[] data = Base64.decode(str);
//		byte[] bs = publicDECRYPT(publicKey, data);
//		return Base64.encode(bs);
//	}

	// �???��??�?
	public static byte[] privateEncrypt(PrivateKey key, String str) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(str.getBytes("UTF8"));
	}

	// �???�解�?
	public static byte[] privateDECRYPT(PrivateKey key, byte[] data) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	public static String privateDECRYPT(String key, String str) throws Exception {
		PrivateKey privateKey = getPrivateKey(key);
		byte[] data = Base64.decode(str);
		byte[] rs = privateDECRYPT(privateKey, data);
		return new String(rs,"UTF-8");
	}

	public static PrivateKey getPrivateKey(String key) throws Exception {

		byte[] keyBytes;

		keyBytes = Base64.decode(key);

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

		return privateKey;
	}

	public static PublicKey getPublicKey(String key) throws Exception {

		byte[] keyBytes;

		keyBytes = Base64.decode(key);

		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		PublicKey publicKey = keyFactory.generatePublic(keySpec);

		return publicKey;
	}

}
