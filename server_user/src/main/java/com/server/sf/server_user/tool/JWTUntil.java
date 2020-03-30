package com.server.sf.server_user.tool;



import java.security.Key;

public class JWTUntil {

//	private static final Key key = new AesKey("thisis16bbsecret".getBytes());

	public  String generateToken(String userId) throws Exception {
		String token = null;
//		JsonWebEncryption jwe = new JsonWebEncryption();
//		jwe.setPayload(userId);
//		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
//		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
//		jwe.setKey(key);
//		token = jwe.getCompactSerialization();
		// System.out.println("Serialized Encrypted JWE: " + serializedJwe);
		// jwe = new JsonWebEncryption();
		// jwe.setKey(key);
		// jwe.setCompactSerialization(serializedJwe);
		// System.out.println("Payload: " + jwe.getPayload());
		return userId;
	}

}
