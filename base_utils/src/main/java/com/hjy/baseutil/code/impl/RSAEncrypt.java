package com.hjy.baseutil.code.impl;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Author: zhangqingyou
 * Date: 2020/5/13 10:42
 * Des:
 */
public class RSAEncrypt {

    /**
     * 加密算法RSA
     */
    private static final String KEY_ALGORITHM = "RSA";


    /**
     * 加密填充方式
     */
    private static final String ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";

    /**
     * 获取公钥的key
     */
    public static final String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJoSTxp3DN2fMKRZrPaUro4pFCkCoQpUv0fKqc0/dyn3tkZLA53g8hdyi7DSwhoeq53SAeD2sNbgfTtuFrjdsRsCAwEAAQ==";


    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 53;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 64;


    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    public static Map<Integer, String> genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(512, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encode(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encode((privateKey.getEncoded())));

        Map<Integer, String> stringMap = new LinkedHashMap<>();
        // 将公钥和私钥保存到Map
        stringMap.put(0, publicKeyString);  //0表示公钥
        stringMap.put(1, privateKeyString);  //1表示私钥

        return stringMap;
    }

    /**
     * 使用默认公钥 加密
     *
     * @param str 加密字符串
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str) {
        return encrypt(str, PUBLIC_KEY);
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) {
        //base64编码的公钥
        String encrypted = null;
        try {
            byte[] decoded = Base64.decode(publicKey.toCharArray());
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(decoded);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicK = keyFactory.generatePublic(x509KeySpec);

            //RSA加密
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, publicK);

            byte[] strBytes = str.getBytes("UTF-8");

            int inputLen = strBytes.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(strBytes, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(strBytes, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();
            encrypted = new String(Base64.encode(encryptedData));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return encrypted;

    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) {
        //64位解码加密后的字符串
        byte[] decryptedData = new byte[0];
        try {
            byte[] inputByte = Base64.decode(str.toCharArray());


            //base64编码的私钥
            byte[] decoded = Base64.decode(privateKey.toCharArray());
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(decoded));
            //RSA解密
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, priKey);

            int inputLen = inputByte.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(inputByte, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(inputByte, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            decryptedData = out.toByteArray();
            out.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }


        return new String(decryptedData);
    }


    /**
     * RSA公钥解密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decryptpublicKey(String str, String publicKey) {
        //64位解码加密后的字符串
        byte[] decryptedData = new byte[0];
        try {
            byte[] inputByte = Base64.decode(str.toCharArray());

            //base64编码的私钥
            byte[] decoded = Base64.decode(publicKey.toCharArray());

            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(decoded);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicK = keyFactory.generatePublic(x509KeySpec);


            //RSA解密
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, publicK);

            int inputLen = inputByte.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(inputByte, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(inputByte, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            decryptedData = out.toByteArray();
            out.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }


        return new String(decryptedData);
    }


}
