package me.caps123987.user;

import lombok.Getter;
import me.caps123987.entities.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import static org.mariadb.jdbc.plugin.authentication.standard.ed25519.Utils.bytesToHex;
import static org.mariadb.jdbc.plugin.authentication.standard.ed25519.Utils.hexToBytes;

public class PasswordHasher {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    public static SaltedHash encrypt(String password) {
        byte[] salt = new byte[16];
        SECURE_RANDOM.nextBytes(salt);
        return encrypt(password, salt);
    }

    public static SaltedHash encrypt(String password, String salt) {
        return encrypt(password, hexToBytes(salt));
    }
    public static SaltedHash encrypt(String password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory;

        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            return new SaltedHash(bytesToHex(salt), bytesToHex(hash));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validate(String password, SaltedHash saltedHash) {
        return validate(password, saltedHash.getSalt(), saltedHash.getHash());
    }

    public static boolean validate(String password, String salt, String hash) {
        return encrypt(password,salt).getHash().equals(hash);
    }

    public static boolean validate(String password, User user) {
        return validate(password, user.getSalt(), user.getPassword());
    }

    @Getter
    public static class SaltedHash {
        public String salt;
        public String hash;

        public SaltedHash(String salt, String hash) {
            this.salt = salt;
            this.hash = hash;
        }
    }
}
