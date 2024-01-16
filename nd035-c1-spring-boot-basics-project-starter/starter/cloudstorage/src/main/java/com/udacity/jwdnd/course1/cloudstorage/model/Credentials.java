package com.udacity.jwdnd.course1.cloudstorage.model;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Credentials {
    private Integer credentialId;
    private String url;
    private String username;
    private String key;



    private String deccodingpassword;

    private String password;
    private int userid;


    
    public Credentials(Integer credentialId, String url , String key , String username, String password, Integer userid, String deccodingpassword) {
        this.credentialId = credentialId;
        this.url = url;
        this.key = key;
        this.username = username;
        this.password = password;
        this.userid = userid;
        this.deccodingpassword = deccodingpassword;
    }

    public Credentials() {
    }

    public void decodePassword() {
        this.password = decryptValue(password,key);
    }

    public String decryptValue(String data, String key) {
        byte[] decryptedValue = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptedValue = cipher.doFinal(Base64.getDecoder().decode(data));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                 | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
        }

        return new String(decryptedValue);
    }

    public void setDeccodingpassword(String deccodingpassword) {
        this.deccodingpassword = deccodingpassword;
    }

    public int getcredentialId() {
        return credentialId;
    }

    public void setcredentialId(int credentialId) {
        this.credentialId = credentialId;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getDeccodingpassword() {
        return decryptValue(password,key);
    }
}
