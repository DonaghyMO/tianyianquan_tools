package com.tianyianquan.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AesUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String CHARSET = "UTF-8";

    public String key = "aaa&&sangfo22ds"; // 16字节的AES密钥
    public String iv = "1234567890123456";  // 16字节的初始向量

    public String getKey() {
        return key;
    }

    public String getIv() {
        return iv;
    }

    public static String encrypt(String key, String iv, String data) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(CHARSET), ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(CHARSET));
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(CHARSET));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String key, String iv, String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(CHARSET), ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(CHARSET));
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, CHARSET);
    }

    public static void main(String[] args) {
        try {
            String key = "1234567890123456"; // 16字节的AES密钥
            String iv = "1234567890123456";  // 16字节的初始向量

            String originalText = "bGTT47Y^pwvlHykC#JRx";
            System.out.println("Original Text: " + originalText);

            String encryptedText = encrypt(key, iv, originalText);
            System.out.println("Encrypted Text: " + encryptedText);

            String decryptedText = decrypt(key, iv, encryptedText);
            System.out.println("Decrypted Text: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
