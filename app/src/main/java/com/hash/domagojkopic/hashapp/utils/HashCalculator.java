package com.hash.domagojkopic.hashapp.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by domagojkopic on 09/09/2017.
 */

public class HashCalculator {
    private static final String SHA1 = "SHA-1";
    private static final String ISO = "iso-8859-1";

    public static byte[] calculateSHA1(String text) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(SHA1);
        byte[] textBytes = text.getBytes(ISO);
        md.update(textBytes, 0, textBytes.length);
        return md.digest();
    }

    public static String getHexString(byte[] sha1hash) {
        String hexStr = "";

        for (byte aSha1hash : sha1hash) {
            hexStr += Integer.toString((aSha1hash & 0xff) + 0x100, 16).substring(1);
        }

        return hexStr;
    }

    public static boolean isFirstByteEven(byte[] hash) {
        int first = hash[0];
        return first % 2 == 0;
    }
}
