package io.rjdev.booster.util.string;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe para gerar hashs de strings encryptadas em SHA-256
 *
 * @since 23/09/2018 21:10
 * @author renatopeduardojr
 */
public class SHAString implements Serializable{
    private static final long serialVersionUID = 1L;

    public static String hash(String data, Type type) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(type.getValue());
            md.update(data.getBytes());
            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes)
            result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(
                    1));
        return result.toString();
    }

    public enum Type {
        SHA_256("SHA-256"),
        SHA_512("SHA-512");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}

