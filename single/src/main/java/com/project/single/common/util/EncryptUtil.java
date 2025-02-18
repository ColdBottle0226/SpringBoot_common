package com.project.single.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 암복호화 Util
 */
@Component
@Slf4j
public class EncryptUtil {

    public static String alg = "AES/CBC/PKCS5Padding";

    //@Value("${enc.key}")
    private static String key = "암호화키";
    public static byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };


    /**
     *
     * AES 양방향 암호화
     * @param text
     * @return
     * @throws Exception
     */

    public static String encrypt(String text) throws Exception {
        try{

            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);

        }catch(NullPointerException e){
            return null;
        }
    }

    /**
     * AES 양방향 복호화
     * @param cipherText
     * @return
     * @throws Exception
     */

    public static String decrypt(String cipherText) throws Exception {
        try{
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");

        }catch(NullPointerException e){
            return null;
        }
    }

    /**
     * SHA256 단방향 암호화
     * @param text
     * @return
     */
    public static String getHashEncryptSHA256(String text) {
        try {
            // SHA-256 해시를 계산
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = sha256.digest(text.getBytes());

            // Base64로 인코딩
            String base64Encoded = Base64.getEncoder().encodeToString(hashBytes);

            return base64Encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }



    // iv 적용한 decrypt (현재는 사용하지 않음, 잘못 적용된 데이터 원상복귀를 위해 남겨둠)
    public static String old_decrypt(String cipherText) throws Exception {
        try{
            String iv = key.substring(0, 16);

            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");

        }catch(NullPointerException e){
            return null;
        }
    }
}

