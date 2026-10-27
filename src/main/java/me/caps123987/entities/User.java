package me.caps123987.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.name=:name")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "salt", nullable = false, length = 64)
    private String salt;

    @Column(name = "role", length = 32)
    private String role;

    @OneToMany(mappedBy = "owner")
    private Set<Comment> comments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "owner")
    private Set<LComponent> lcomponents = new LinkedHashSet<>();

    @OneToMany(mappedBy = "owner")
    private Set<Like> likes = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}