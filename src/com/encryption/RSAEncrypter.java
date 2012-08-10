package com.encryption;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
  
public class RSAEncrypter {   
  
    /**
     * 生成rsa密钥对
     * @return
     * @throws NoSuchAlgorithmException
     */
    public KeyPair generateKey() throws NoSuchAlgorithmException {   
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");   
        keyPairGen.initialize(1024, new SecureRandom());   
  
        KeyPair keyPair = keyPairGen.generateKeyPair();   
        return keyPair;   
    }   
  
    /**
     * 分别保存公钥和私钥到不同的文件
     * @param keyPair
     * @param publicKeyFile	指定保存公钥的路径
     * @param privateKeyFile	指定保存私钥的路径
     * @throws IOException
     */
    public void saveKey(KeyPair keyPair, String publicKeyFile,   
            String privateKeyFile) throws IOException  {   
        PublicKey pubkey = keyPair.getPublic();   
        PrivateKey prikey = keyPair.getPrivate();   
  
        // save public key   
        FileOutputStream     f1=new FileOutputStream(publicKeyFile);
        ObjectOutputStream b1=new ObjectOutputStream(f1);
         b1.writeObject(pubkey);
         
         // save private key
         FileOutputStream     f2=new FileOutputStream(privateKeyFile);
         ObjectOutputStream b2=new  ObjectOutputStream(f2);
         b2.writeObject(prikey);
    }   
  
    /**  
     * @param filename  加载文件的路径
     * @param type：  加载文件的类型
     *            1-public 0-private  
     * @return  
     */  
    public Key loadKey(String filename, int type)    {
        if (type == 0) {   
            // privateKey   
        	PrivateKey privateKey = null;
			try {
				ObjectInputStream oos = new ObjectInputStream(new FileInputStream(filename));
				privateKey = (RSAPrivateKey) oos.readObject();
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
				}
			return privateKey; 
  
        } else {   
            // publicKey
        	PublicKey publicKey = null;
			try {
				ObjectInputStream oos = new ObjectInputStream(new FileInputStream(filename));
				publicKey = (RSAPublicKey) oos.readObject();
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
				}
			return publicKey;
        }   
    }   
  
    /**  
     * Encrypt String.  
     *   
     * @return byte[]  
     */  
    public byte[] encrypt(RSAPublicKey publicKey, byte[] data) {   
        if (publicKey != null) {   
            try {   
                Cipher cipher = Cipher.getInstance("RSA",   
                        new BouncyCastleProvider());   
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);   
                return cipher.doFinal(data);   
            } catch (Exception e) {   
                e.printStackTrace();   
            }   
        }   
        return null;   
    }   
  
    /**  
     * Basic decrypt method  
     *   
     * @return byte[]  
     */  
    public byte[] decrypt(RSAPrivateKey privateKey, byte[] raw) {   
        if (privateKey != null) {   
            try {   
                Cipher cipher = Cipher.getInstance("RSA",   
                        new BouncyCastleProvider());   
                cipher.init(Cipher.DECRYPT_MODE, privateKey);   
                return cipher.doFinal(raw);   
            } catch (Exception e) {   
                e.printStackTrace();   
            }   
        }   
  
        return null;   
    }   
}