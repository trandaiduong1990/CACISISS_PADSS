package org.transinfo.cacis.util;


import java.security.KeyException;
import java.security.Security;

import xjava.security.Cipher;
import cryptix.provider.key.RawSecretKey;
import cryptix.util.core.Hex;

/**
 *
 * This is a utility class is used for encryption. Cryptix package is
 * used for encryption, with Triple-DES algorithm for all encryptions.
 */
public class EncryptUtility extends Object {

    // The key for encryption of encryption password only
    private static String strLocalKey = new String("1A812A419C63AA771AD9F61345CC0CE633812AA19C630022");
    public static String ENCRYPTION_KEY = strLocalKey;


    /** Adds the cryptix provider dynamically */
    static {
        Security.addProvider(new cryptix.provider.Cryptix());
    }


    /**
     * @param strClearText is the text which is to be encrypted
     * @return String, the 3DES encrypted string.
     * @exception Exception when the key is corrupted or not available & when data could
     *  not be encrypted
     */
    public static String encrypt(String strClearText, String strKey) throws Exception{

        try{
            RawSecretKey rskKey = new RawSecretKey("DES-EDE3",Hex.fromString(strKey));
            //Use Triple -DES , cryptix package
            Cipher ch3Des=Cipher.getInstance("DES-EDE3/ECB/PKCS#5","Cryptix");
            ch3Des.initEncrypt(rskKey);
            byte[] bytClear = strClearText.getBytes();
            byte[] bytCipherText = ch3Des.crypt(bytClear);

            return Hex.toString(bytCipherText);

        }catch(KeyException keyEx){
            throw new Exception( " Error :" + keyEx.getMessage());
        }catch(Exception ex){
            throw new Exception( " Error :" + ex.getMessage());
        }
    } //end encrypt

    /**
     * @param strEncrText is the text which is to be decrypted
     * @return String, the decrypted string.
     * @exception Exception when the key is corrupted or not available & when data could
     *  not be decrypted
     */
    public static String decrypt(String strEncrText, String strKey) throws Exception{
        try{

            RawSecretKey rskKey = new RawSecretKey("DES-EDE3",Hex.fromString(strKey));
            Cipher ch3Des=Cipher.getInstance("DES-EDE3/ECB/PKCS#5","Cryptix");
            ch3Des.initDecrypt(rskKey);
            byte[] bytDecrypted = ch3Des.crypt(Hex.fromString(strEncrText));

            return new String(bytDecrypted);

        }catch(KeyException keyEx){
            throw new Exception(" Error :" + keyEx.getMessage());
        }catch(Exception ex){
            throw new Exception(" Error :" + ex.getMessage());
        }
    } //end decrypt


    /**
     * @param strClearPwd is the password that is to be decrypted
     * @return String, the encrypted password string.
     * @exception Exception when the key is corrupted or not available & when data could
     *  not be decrypted
     */
    public static String encrPassword(String strClearPwd) throws Exception{
        try{
            String strEncrPwd = encrypt(strClearPwd,strLocalKey);
            return strEncrPwd;
        }catch(Exception SMEx){
            throw SMEx;
        }
    }

    /**
     * @param strEncrPwd is the password which is to be decrypted
     * @return String, the decrypted password string.
     * @exception Exception when the key is corrupted or not available & when data could
     *  not be decrypted
     */
    public static String decrPassword(String strEncrPwd) throws Exception {
        try{
            String strDecrPwd = decrypt(strEncrPwd,strLocalKey);
            return strDecrPwd;
        }catch(Exception SMEx){
            throw SMEx;
        }
    }

    public static void main(String args[]) {
        try{
            System.out.println("NORMAL PASSWORD :: "+args[0]);
            String enPass = EncryptUtility.encrPassword(args[0]);
            String dePass = EncryptUtility.decrPassword(enPass);
            System.out.println("ENCYPTED PASSWORD :: "+enPass);
            System.out.println("DECRYPTED PASSWORD :: "+dePass);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

} //end class