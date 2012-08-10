package com.encryption;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * 利用DES加密算法实现一个加密类DesEncrypter
 * @author lmq
 *
 */
 
public class DesEncrypter {
    Cipher ecipher;	//此对象为加密和解密提供密码功能
    Cipher dcipher;
 
    public DesEncrypter(SecretKey key) {
        // Create an 8-byte initialization vector
    	//创建8位的初始向量
        byte[] iv = new byte[]{
            (byte)0x8E, 0x12, 0x39, (byte)0x9C,
            0x07, 0x72, 0x6F, 0x5A
        };
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        try {
            ecipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
 
            // CBC requires an initialization vector
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (java.security.InvalidAlgorithmParameterException e) {
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }
 
    // Buffer used to transport the bytes from one stream to another
    //用来传输字节流的缓冲区
    byte[] buf = new byte[1024];
 
     /**
     * 加密输入流in的数据到输出流out
     * @param in 原始输入位流
     * @param out	 加密后的输出位流
     */
    public void encrypt(InputStream in, OutputStream out) {
        try {
            // Bytes written to out will be encrypted
            // 写入输出流的字节将会被加密
        	out = new CipherOutputStream(out, ecipher);
 
            // Read in the cleartext bytes and write to out to encrypt
            int numRead = 0;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (java.io.IOException e) {
        }
    }
 
    /**
     * 从加密的输入流in解密到输出流out
     * @param in	加密的输入流
     * @param out	解密后的输出流
     */
    public void decrypt(InputStream in, OutputStream out) {
        try {
            // Bytes read from in will be decrypted
        	//解密从in读入的字节
            in = new CipherInputStream(in, dcipher);
 
            // Read in the decrypted bytes and write the cleartext to out
            int numRead = 0;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (java.io.IOException e) {
        }
    }
}

