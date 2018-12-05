/*
 * Copyright (C) 2018 SEAT, S.A - All Rights Reserved
 *
 * This file is part of MySeat App.
 *
 * Unauthorized reproduction, copying, or modification, of this file, via
 * any medium is strictly prohibited.
 *
 * This code is Proprietary and Confidential.
 *
 * All the 3rd parties libraries included in the project are regulated by
 * their own licenses.
 */

package com.example.security;

import android.util.Base64;

import java.nio.charset.Charset;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by jzaragoza on 6/04/17.
 */

public class CryptoUtils {

    private static final String v4 = "d4eXpBQkNE";
    private static final String v8 = "EyMzQ1Njc4";
    private static final String v3 = "cHFyc3R1dn";
    private static final String v5 = "RUZHSElKS0";

    private static final String alphabet = new String(Base64.decode(Constants.v1 + Constants.v2 + v3 + v4
            + v5 + Constants.v6 + Constants.v7 + v8 + Constants.v9, Base64.DEFAULT), Charset.defaultCharset());
    private final String algoritm = new String(Base64.decode("QUVTL0NCQy9QS0NTNVBhZGRpbmc=", Base64.DEFAULT), Charset.defaultCharset());
    private final String keyspec = new String(Base64.decode("QUVT", Base64.DEFAULT), Charset.defaultCharset());

    private static CryptoUtils instance = null;

    public static CryptoUtils getInstance() {
        if (instance == null) {
            instance = new CryptoUtils();
        }
        return instance;
    }

    public String encrypt(String text) {
        try {
            byte[] keyBytes = getSecret().getBytes("UTF-8");
            byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            SecretKeySpec newKey = new SecretKeySpec(keyBytes, keyspec);
            Cipher cipher = Cipher.getInstance(algoritm);
            cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
            return Base64.encodeToString(cipher.doFinal(text.getBytes()), Base64.DEFAULT);

        } catch (Exception e) {
            // No exception, no trace
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(String text) {
        if (text != null) {
            try {
                byte[] keyBytes = getSecret().getBytes("UTF-8");
                byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

                AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
                SecretKeySpec newKey = new SecretKeySpec(keyBytes, keyspec);
                Cipher cipher = Cipher.getInstance(algoritm);
                cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
                byte[] bytes = cipher.doFinal(Base64.decode(text, Base64.DEFAULT));

                return new String(bytes, Charset.defaultCharset());
            } catch (Exception e) {
                //No exception no trace
                e.printStackTrace();
            }
        }
        return null;
    }


    private String getSecret() {
        return String.valueOf(alphabet.charAt(0)) +
                alphabet.charAt(0)+
                alphabet.charAt(12)+
                alphabet.charAt(3)+
                alphabet.charAt(5)+
                alphabet.charAt(7)+
                alphabet.charAt(50)+
                alphabet.charAt(39)+
                alphabet.charAt(1)+
                alphabet.charAt(1)+
                alphabet.charAt(3)+
                alphabet.charAt(4)+
                alphabet.charAt(5)+
                alphabet.charAt(6)+
                alphabet.charAt(11)+
                alphabet.charAt(36)+
                alphabet.charAt(0)+
                alphabet.charAt(0)+
                alphabet.charAt(12)+
                alphabet.charAt(3)+
                alphabet.charAt(54)+
                alphabet.charAt(7)+
                alphabet.charAt(50)+
                alphabet.charAt(39)+
                alphabet.charAt(1)+
                alphabet.charAt(1)+
                alphabet.charAt(3)+
                alphabet.charAt(4)+
                alphabet.charAt(43)+
                alphabet.charAt(46)+
                alphabet.charAt(12)+
                alphabet.charAt(51);
    }
}
