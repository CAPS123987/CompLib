package me.caps123987.user;

import me.caps123987.entities.User;

public class UserFactory {
    public static User createUser(String name, String password, String role) {
        User user = new User();
        user.setName(name);

        PasswordHasher.SaltedHash saltedHash = PasswordHasher.encrypt(password);
        user.setPassword(saltedHash.getHash());
        user.setSalt(saltedHash.getSalt());

        user.setRole(role);
        return user;
    }
}
