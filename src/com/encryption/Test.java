package com.encryption;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		try {
			/*
			 * 生成DES密钥
			 */
			SecretKey key =  KeyGenerator.getInstance("DES").generateKey();
			System.out.println("原始DES密钥："+ new String(key.getEncoded()));
			/*
			 * 利用RSA公钥加密DES密钥
			 */
			RSAEncrypter encrypt = new RSAEncrypter();
			/*KeyPair keyPair = encrypt.generateKey();   
	        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   
	        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();*/
	        String publicKeyPath = "D:/hdfs/29/publicKey";
			     RSAPublicKey pubKey = (RSAPublicKey) encrypt.loadKey(publicKeyPath, 1);
	        byte[] encryptDESKey = encrypt.encrypt(pubKey, key.getEncoded());
	        System.out.println("加密后的DES密钥："+ new String(encryptDESKey));
	        
			/*
			 * 利用RSA私钥解密DES密钥
			 */
	        RSAEncrypter encrypt1 = new RSAEncrypter();
	        String privateKeyPath = "D:/hdfs/29/privateKey";
		     RSAPrivateKey priKey = (RSAPrivateKey) encrypt1.loadKey(privateKeyPath, 0);
	        byte[] originDESKey = encrypt1.decrypt(priKey, encryptDESKey);
	        System.out.println("解密后的DES密钥："+ new String(originDESKey));
	        
	        
		     
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		

	}

}
